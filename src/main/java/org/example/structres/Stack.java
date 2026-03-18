package org.example.structres;

public interface Stack<T> {
	// accessor methods
	public int size(); 
	public boolean isEmpty();
	public T top();

	// update methods
	public void push (T element);
	public T pop();
}
