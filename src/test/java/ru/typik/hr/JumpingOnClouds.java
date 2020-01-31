package ru.typik.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JumpingOnClouds {

	@Test
	public void test() {
		assertEquals(4, getMinJumps(new int[] { 0, 0, 1, 0, 0, 1, 0 }));
		assertEquals(3, getMinJumps(new int[] { 0, 0, 0, 1, 0, 0 }));
	}

	private int getMinJumps(int[] clouds) {
		int curIndex = 0;
		int stepsCount = 0;
		while (curIndex != clouds.length - 1) {
			curIndex = curIndex + 2 < clouds.length && clouds[curIndex + 2] == 0 ? curIndex + 2 : curIndex + 1;
			stepsCount++;
		}
		return stepsCount;
	}

}
