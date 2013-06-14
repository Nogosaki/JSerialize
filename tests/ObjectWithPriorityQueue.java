package tests;

import java.util.PriorityQueue;

public class ObjectWithPriorityQueue {
	PriorityQueue<Integer> prq = new PriorityQueue<Integer>();

	public ObjectWithPriorityQueue() {
		for (int i = 3; i < 10; i++) {
			prq.add(new Integer(i));
		}
	}
}
