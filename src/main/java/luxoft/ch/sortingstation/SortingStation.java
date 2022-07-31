package luxoft.ch.sortingstation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
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

	public Queue<Integer> solve(Integer... cars) {
		distribute(cars);
		var solution = collect();
		clear();
		return solution;
	}

	private void distribute(Integer... cars) {
		Queue<Integer> carQueue = new LinkedList<>(Arrays.asList(cars));
		Integer car;
		while ((car = carQueue.poll()) != null) {
			Integer trackNo = getSuitableTrack(car);
			tracks.get(trackNo).addLast(car);
		}
	}

	private Integer getSuitableTrack(Integer car) {
		Optional<Integer> suitableOccupiedTrack = getSuitableOccupiedTrack(car);
		if (suitableOccupiedTrack.isPresent()) {
			return suitableOccupiedTrack.get();
		}
		Optional<Integer> suitableFreeTrack = getSuitableFreeTrack();
		if (suitableFreeTrack.isPresent()) {
			return suitableFreeTrack.get();
		}
		throw new SortingFailureException("no suitable track found for car %d".formatted(car));
	}

	private Optional<Integer> getSuitableFreeTrack() {
		return tracks.entrySet().stream().filter(this::isEmptyTrack).map(Entry::getKey).min(Comparator.naturalOrder());
	}

	private boolean isEmptyTrack(Entry<Integer, Deque<Integer>> entry) {
		return entry.getValue().isEmpty();
	}

	private Optional<Integer> getSuitableOccupiedTrack(Integer car) {
		return tracks.entrySet().stream().filter(entry -> isSuitableOccupiedTrack(entry, car))
				.max(Comparator.comparing(this::getLastCar)).map(Entry::getKey);
	}

	private Integer getLastCar(Entry<Integer, Deque<Integer>> entry) {
		return entry.getValue().peekLast();
	}

	private boolean isSuitableOccupiedTrack(Entry<Integer, Deque<Integer>> entry, Integer car) {
		Integer lastCar = getLastCar(entry);
		if (lastCar == null)
			return false;
		if (entry.getValue().size() >= trackCapacity)
			return false;
		return lastCar.compareTo(car) < 0;
	}

	private Queue<Integer> collect() {
		var queue = new LinkedList<Integer>();
		do {
			Integer minTrackNo = null;
			Integer minCarNo = Integer.MAX_VALUE;
			for (var trackNo = 0; trackNo < trackNumber; trackNo++) {
				var track = tracks.get(trackNo);
				if (!track.isEmpty() && minCarNo.compareTo(track.peekFirst()) > 0) {
					minCarNo = track.peekFirst();
					minTrackNo = trackNo;
				}
			}
			if (minTrackNo == null)
				break;
			queue.addLast(minCarNo);
			tracks.get(minTrackNo).removeFirst();
		} while (true);
		return queue;
	}

	private void clear() {
		for (var entry : tracks.entrySet()) {
			entry.getValue().clear();
		}
	}

}
