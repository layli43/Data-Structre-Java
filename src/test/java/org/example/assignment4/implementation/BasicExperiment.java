package org.example.assignment4.implementation;

import org.junit.Test;

import java.util.Random;

public class BasicExperiment {

    static final long SEED = 42;

    private Integer[] randomInts(int n, Random r) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt();
        return a;
    }

    /** 1. Correctness: verify both maps behave as Map. */
    @Test
    public void mapBasicTest() {
        Random r = new Random(SEED);
        int N = 5000;
        Integer[] keys = randomInts(N, r);

        HashMap<Integer,Integer> hashMap = new HashMap<>();
        ChainHashMap<Integer,Integer> chainHashMap = new ChainHashMap<>();

        for (int i = 0; i < N; i++) { hashMap.put(keys[i], i); chainHashMap.put(keys[i], i); }
        int mapHits = 0, chainMapHits = 0;
        for (int i = 0; i < N; i++) {
            if (hashMap.get(keys[i]) != null) mapHits++;
            if (chainHashMap.get(keys[i]) != null) chainMapHits++;
        }
        for (int i = 0; i < N; i++) { hashMap.put(keys[i], -i); chainHashMap.put(keys[i], -i); }
        for (int i = 0; i < N/2; i++) { hashMap.remove(keys[i]); chainHashMap.remove(keys[i]); }

        System.out.printf("HashMap: size=%d hits=%d overwriteOK=%s%n",
                hashMap.size(), mapHits, hashMap.get(keys[N-1]).equals(-(N-1)));
        System.out.printf("ChainHashMap: size=%d hits=%d overwriteOK=%s%n",
                chainHashMap.size(), chainMapHits, chainHashMap.get(keys[N-1]).equals(-(N-1)));
    }

    /** 2. Probes vs load factor — hold capacity comparison, vary fill. */
    @Test
    public void loadFactorTest() {
        System.out.println("impl,target_lf,actual_lf,avg_put,avg_hit,avg_miss,max_chain");

        // AbstractHashMap auto-resizes at n > cap/2, so we over-allocate the
        // initial capacity. n items inserted into a table of capacity allocCap
        // gives an actual load factor of n/allocCap which we report.
        int allocCap = 30011;
        double[] targets = {0.10, 0.25, 0.40, 0.49};  // stay below 0.5 to avoid resize
        Random r = new Random(SEED);
        Integer[] pool = randomInts(allocCap, r);
        Integer[] miss = new Integer[1000];
        for (int i = 0; i < miss.length; i++) miss[i] = -1 - i;

        for (double lf : targets) {
            int n = (int)(lf * allocCap);
            HashMap<Integer,Integer> hashMap = new HashMap<>(allocCap);
            ChainHashMap<Integer,Integer> chainHashMap = new ChainHashMap<>(allocCap);

            for (int i = 0; i < n; i++) { hashMap.put(pool[i], i); chainHashMap.put(pool[i], i); }
            double pPut = hashMap.avgProbes(), cPut = chainHashMap.avgProbes();

            hashMap.resetCounters(); chainHashMap.resetCounters();
            for (int i = 0; i < n; i++) { hashMap.get(pool[i]); chainHashMap.get(pool[i]); }
            double pHit = hashMap.avgProbes(), cHit = chainHashMap.avgProbes();

            hashMap.resetCounters(); chainHashMap.resetCounters();
            for (Integer m : miss) { hashMap.get(m); chainHashMap.get(m); }
            double pMiss = hashMap.avgProbes(), cMiss = chainHashMap.avgProbes();

            double actual = (double) n / hashMap.capacity();
            System.out.printf("hashMap,%.2f,%.3f,%.3f,%.3f,%.3f,-%n",
                    lf, actual, pPut, pHit, pMiss);
            System.out.printf("chainHashMap,%.2f,%.3f,%.3f,%.3f,%.3f,%d%n",
                    lf, actual, cPut, cHit, cMiss, chainHashMap.maxChainLength());
        }
        System.out.println("\nKnuth (uniform hashing):");
        System.out.println("  chainHashMap hit ≈ 1+α/2   chainHashMap miss ≈ α");
        System.out.println("  hashMap hit ≈ 0.5(1+1/(1-α))   hashMap miss ≈ 0.5(1+1/(1-α)²)");
    }

    /** 3. Resize amortization — cost per 1000-insertion window. */
    @Test
    public void resizeTest() {
        System.out.println("window_start,probes_in_window,capacity,event");
        HashMap<Integer,Integer> map = new HashMap<>();
        Random r = new Random(SEED);
        int N = 50000, WINDOW = 1000;
        long lastProbes = 0;
        int  lastResizes = 0;
        for (int i = 0; i < N; i++) {
            map.put(r.nextInt(), i);
            if ((i + 1) % WINDOW == 0) {
                long dp = map.probeCount - lastProbes;
                int  dr = map.resizeCount - lastResizes;
                System.out.printf("%d,%d,%d,%s%n",
                        i + 1 - WINDOW, dp, map.capacity(), dr > 0 ? "RESIZE" : "");
                lastProbes  = map.probeCount;
                lastResizes = map.resizeCount;
            }
        }
        System.out.printf("Total: %d probes / %d ops = %.3f avg; %d resizes%n",
                map.probeCount, map.opCount, map.avgProbes(), map.resizeCount);
    }
}
