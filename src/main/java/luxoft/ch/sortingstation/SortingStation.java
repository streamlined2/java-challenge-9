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
		if (trackNumber <= 1)
			throw new IllegalArgumentException("number of tracks should be greater than 1");
		if (trackCapacity < 1)
			throw new IllegalArgumentException("track capacity should be greater than 0");
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

	private static final int NO_TRACK_NUMBER = -1;

	private Queue<Integer> collect() {
		var queue = new LinkedList<Integer>();
		do {
			int minTrackNo = NO_TRACK_NUMBER;
			int minCarNo = Integer.MAX_VALUE;
			for (var trackNo = 0; trackNo < trackNumber; trackNo++) {
				var track = tracks.get(trackNo);
				if (!track.isEmpty() && minCarNo < track.peekFirst()) {
					minCarNo = track.peekFirst();
					minTrackNo = trackNo;
				}
			}
			if (minTrackNo == NO_TRACK_NUMBER) break; 
			queue.addLast(minCarNo);
			tracks.get(minTrackNo).removeFirst();
		} while (true);
		return queue;
	}

	public Queue<Integer> solve(Integer... cars) {
		distribute(cars);
		return collect();
	}

}
