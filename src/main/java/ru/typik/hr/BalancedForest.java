package ru.typik.hr;

import java.util.Arrays;

public class BalancedForest {

	public static int balancedForest(int[] values, int[][] edges) {
		Integer minAddValue = null;
		for (int i = 0; i < edges.length - 1; ++i) {
			int[] graphIndexes = new int[values.length];
			Arrays.fill(graphIndexes, 1);
			cutEdge(edges, graphIndexes, 2, i);

//			System.out.println(String.format("Graph indexes after cut %s->%s : %s", edges[i][0], edges[i][1],
//					IntStream.of(graphIndexes).boxed().collect(Collectors.toList())));

			long sum1 = getSum(values, graphIndexes, 1);
			long sum2 = getSum(values, graphIndexes, 2);

//			System.out.println(String.format("Sum1 : %s", sum1));
//			System.out.println(String.format("Sum2 : %s", sum2));

			if (sum1 == sum2) {
				minAddValue = getMinValue(minAddValue, (int) sum1);
			} else {
				for (int j = i + 1; j < edges.length; ++j) {
					int graphNumToDivide = sum1 < sum2 ? 2 : 1;
					if (i != j && graphIndexes[edges[j][0] - 1] == graphNumToDivide) {
						cutEdge(edges, graphIndexes, 3, i, j);

//						System.out.println(String.format("Graph indexes after cut %s->%s : %s", edges[j][0],
//								edges[j][1], IntStream.of(graphIndexes).boxed().collect(Collectors.toList())));
						long sumPart1 = graphNumToDivide == 1 ? getSum(values, graphIndexes, 1) : sum1;
						long sumPart2 = graphNumToDivide == 2 ? getSum(values, graphIndexes, 2) : sum2;
						long sumPart3 = getSum(values, graphIndexes, 3);

//						System.out.println(String.format("Sum1 : %s", sumPart1));
//						System.out.println(String.format("Sum2 : %s", sumPart2));
//						System.out.println(String.format("Sum3 : %s", sumPart3));

						minAddValue = getMinValue(minAddValue, getValueToAdd(sumPart1, sumPart2, sumPart3));

						for (int k = 0; k < graphIndexes.length; ++k) {
							if (graphIndexes[k] == 3) {
								graphIndexes[k] = graphNumToDivide;
							}
						}
					}
				}
			}
		}
		return minAddValue == null ? -1 : minAddValue;
	}

	private static Integer getMinValue(Integer minAddValue, Integer sum) {
		return minAddValue == null || sum != null && sum < minAddValue ? sum : minAddValue;
	}

	private static void cutEdge(int[][] edges, int[] graphIndexes, int newGraphNum, int... cutNums) {
		int numEdgeToCut = cutNums[cutNums.length - 1];
		int[] edgeToCut = edges[numEdgeToCut];
		graphIndexes[edgeToCut[1] - 1] = newGraphNum;
		boolean isSomethingChanged = true;
		while (isSomethingChanged) {
			isSomethingChanged = false;
			for (int i = 0; i < edges.length; ++i) {
				if (cutNums[0] != i && cutNums[cutNums.length - 1] != i) {
					int[] edge = edges[i];
					if (graphIndexes[edge[0] - 1] == newGraphNum && graphIndexes[edge[1] - 1] != newGraphNum) {
						graphIndexes[edge[1] - 1] = newGraphNum;
						isSomethingChanged = true;
					} else if (graphIndexes[edge[0] - 1] != newGraphNum && graphIndexes[edge[1] - 1] == newGraphNum) {
						graphIndexes[edge[0] - 1] = newGraphNum;
						isSomethingChanged = true;
					}
				}
			}
		}
	}

	private static Integer getValueToAdd(long sum1, long sum2, long sum3) {
		if (sum1 == sum2 && sum3 < sum1) {
			return (int) (sum1 - sum3);
		} else if (sum2 == sum3 && sum1 < sum2) {
			return (int) (sum2 - sum1);
		} else if (sum1 == sum3 && sum2 < sum1) {
			return (int) (sum1 - sum2);
		} else if (sum1 + sum2 == sum3) {
			return (int) sum3;
		} else if (sum2 + sum3 == sum1) {
			return (int) sum1;
		} else if (sum1 + sum3 == sum2) {
			return (int) sum2;
		} else {
			return null;
		}
	}

	private static long getSum(int[] values, int[] graphIndex, int graphNum) {
		long sum = 0;
		for (int i = 0; i < graphIndex.length; ++i) {
			if (graphIndex[i] == graphNum) {
				sum += values[i];
			}
		}
		return sum;

	}
}
