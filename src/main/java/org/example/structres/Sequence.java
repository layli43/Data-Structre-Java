package org.example.structres;

public interface Sequence extends Vector, List {
    public Position atRank(int rank);
    public int rankOf(Position position);
}
