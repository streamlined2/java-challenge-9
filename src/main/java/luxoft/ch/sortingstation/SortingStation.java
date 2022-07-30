package luxoft.ch.sortingstation;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SortingStation {

	private final Map<Integer, Deque<Integer>> tracks;
	private final int trackNumber;
	private final int trackCapacity;

	public SortingStation(int trackNumber, int trackCapacity) {
		this.trackNumber = trackNumber;
		this.trackCapacity = trackCapacity;
		tracks = new HashMap<>();
		for (var k = 0; k < trackNumber; k++) {
			tracks.put(k, new LinkedList<>());
		}
	}

	public void clear() {
		for (var entry : tracks.entrySet()) {
			entry.getValue().clear();
		}
	}

	private void distribute(Integer... cars) {
	}

	private Queue<Integer> collect() {
		return new LinkedList<>();
	}

	public Queue<Integer> solve(Integer... cars) {
		distribute(cars);
		return collect();
	}

}
