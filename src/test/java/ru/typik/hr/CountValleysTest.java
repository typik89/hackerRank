package ru.typik.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountValleysTest {

	private int getCountValleys(String path) {
		int sum = 0;
		int countValleys = 0;
		for (char ch : path.toCharArray()) {
			if (ch == 'D') {
				if (sum == 0) {
					countValleys++;
				}
				sum--;
			} else {
				sum++;
			}
		}
		return countValleys;
	}

	@Test
	public void test() {
		assertEquals(1, getCountValleys("UDDDUDUU"));
		assertEquals(1, getCountValleys("DDUUUUDD"));
	}
}
