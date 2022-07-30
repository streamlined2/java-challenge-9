package luxoft.ch.sortingstation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class SortingStationTest {

	@Test
	void test1() {
		SortingStation station = new SortingStation(3, 1);
		assertEquals(List.of(1, 2, 3), station.solve(3, 2, 1));
	}

	@Test
	void test2() {
		SortingStation station = new SortingStation(2, 1);
		assertThrows(SortingFailureException.class, () -> station.solve(3, 2, 1));
	}

	@Test
	void test3() {
		SortingStation station = new SortingStation(2, 2);
		assertThrows(SortingFailureException.class, () -> station.solve(3, 2, 1));
	}

	@Test
	void test4() {
		SortingStation station = new SortingStation(2, 2);
		assertEquals(List.of(1, 2, 3), station.solve(3, 1, 2));
	}

	@Test
	void test5() {
		SortingStation station = new SortingStation(2, 2);
		assertThrows(SortingFailureException.class, () -> station.solve(2, 3, 1));
	}

	@Test
	void test6() {
		SortingStation station = new SortingStation(2, 2);
		assertEquals(List.of(1, 2, 3), station.solve(2, 1, 3));
	}

	@Test
	void test7() {
		SortingStation station = new SortingStation(2, 2);
		assertEquals(List.of(1, 2, 3, 4), station.solve(2, 3, 1, 4));
	}

	@Test
	void test8() {
		SortingStation station = new SortingStation(2, 2);
		assertThrows(SortingFailureException.class, () -> station.solve(2, 4, 3, 1));
	}

}
