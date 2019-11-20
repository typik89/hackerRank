package ru.typik.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class BalancedForestTest {

	@Test
	public void test1() {
		assertEquals(19, BalancedForest.balancedForest(new int[] { 15, 12, 8, 14, 13 },
				new int[][] { new int[] { 1, 2 }, new int[] { 1, 3 }, new int[] { 1, 4 }, new int[] { 4, 5 } }));

		assertEquals(2, BalancedForest.balancedForest(new int[] { 1, 2, 2, 1, 1, },
				new int[][] { new int[] { 1, 2 }, new int[] { 1, 3 }, new int[] { 3, 5 }, new int[] { 1, 4 } }));
		assertEquals(-1, BalancedForest.balancedForest(new int[] { 1, 3, 5 },
				new int[][] { new int[] { 1, 3 }, new int[] { 1, 2 } }));
		assertEquals(4, BalancedForest.balancedForest(new int[] { 12, 10, 8, 12, 14, 12 }, new int[][] {
				new int[] { 1, 2 }, new int[] { 1, 3 }, new int[] { 1, 4 }, new int[] { 2, 5 }, new int[] { 4, 6 } }));
	}

	@Test
	public void testFile1() throws IOException {
		List<Parameters> parametersList = getParametersFromFile("src/test/resources/balancedTree/input01.txt");
		assertEquals(5, parametersList.size());

		assertEquals(-1,
				BalancedForest.balancedForest(parametersList.get(0).getValues(), parametersList.get(0).getEdges()));
		assertEquals(10,
				BalancedForest.balancedForest(parametersList.get(1).getValues(), parametersList.get(1).getEdges()));
		assertEquals(13,
				BalancedForest.balancedForest(parametersList.get(2).getValues(), parametersList.get(2).getEdges()));
		assertEquals(5,
				BalancedForest.balancedForest(parametersList.get(3).getValues(), parametersList.get(3).getEdges()));
		assertEquals(297,
				BalancedForest.balancedForest(parametersList.get(4).getValues(), parametersList.get(4).getEdges()));
	}

	@Test
	public void testFile3() throws IOException {
		List<Parameters> parametersList = getParametersFromFile("src/test/resources/balancedTree/input03.txt");
		

		BalancedForest.balancedForest(parametersList.get(0).values, parametersList.get(0).edges);
//		assertEquals(5, parametersList.size());
//
//		assertEquals(-1,
//				BalancedForest.balancedForest(parametersList.get(0).getValues(), parametersList.get(0).getEdges()));
//		assertEquals(10,
//				BalancedForest.balancedForest(parametersList.get(1).getValues(), parametersList.get(1).getEdges()));
//		assertEquals(13,
//				BalancedForest.balancedForest(parametersList.get(2).getValues(), parametersList.get(2).getEdges()));
//		assertEquals(5,
//				BalancedForest.balancedForest(parametersList.get(3).getValues(), parametersList.get(3).getEdges()));
//		assertEquals(297,
//				BalancedForest.balancedForest(parametersList.get(4).getValues(), parametersList.get(4).getEdges()));
	}

	private List<Parameters> getParametersFromFile(String path) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(path));
		List<Parameters> parametersList = new ArrayList<>();
		int i = 1;
		while (i < lines.size()) {
			int count = Integer.parseInt(lines.get(i));
			Parameters parameters = new Parameters();
			parameters.setValues(Stream.of(lines.get(i + 1).split(" ")).mapToInt(el -> Integer.parseInt(el)).toArray());
			parameters.setEdges(new int[count - 1][]);
			for (int j = 0; j < count - 1; ++j) {
				parameters.getEdges()[j] = getIntArrayFromString(lines.get(i + 2 + j));
			}
			parametersList.add(parameters);
			i += count + 1;
		}
		return parametersList;
	}

	private static int[] getIntArrayFromString(String valueString) {
		return Stream.of(valueString.split(" ")).mapToInt(el -> Integer.parseInt(el)).toArray();
	}

	public static class Parameters {
		private int[] values;
		private int[][] edges;

		public int[][] getEdges() {
			return edges;
		}

		public void setEdges(int[][] edges) {
			this.edges = edges;
		}

		public int[] getValues() {
			return values;
		}

		public void setValues(int[] values) {
			this.values = values;
		}
	}

}
