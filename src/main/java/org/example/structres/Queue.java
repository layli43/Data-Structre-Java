package org.example.structres;

public interface Queue<T> {
	// accessor methods
	public int size(); 
	public boolean isEmpty();
	public T front();

	// update methods
	public void enqueue (T element);
	public T dequeue();
}
