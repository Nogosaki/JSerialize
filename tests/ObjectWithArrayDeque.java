package tests;

import java.util.ArrayDeque;

public class ObjectWithArrayDeque {
	ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

	public ObjectWithArrayDeque() {
		deque.add(10);
		deque.add(20);
		deque.add(30);
	}
}
