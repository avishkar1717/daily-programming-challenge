import java.util.*;
public class ZeroSumSubarrays {
    public static List<int[]> findZeroSumSubarrays(int[] arr) {
        List<int[]> result = new ArrayList<>();
        Map<Integer, List<Integer>> sumMap = new HashMap<>();
        int prefixSum = 0;

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            if (prefixSum == 0) {
                result.add(new int[]{0, i});
            }
            if (sumMap.containsKey(prefixSum)) {
                for (int startIndex : sumMap.get(prefixSum)) {
                    result.add(new int[]{startIndex + 1, i});
                }
            }
            sumMap.computeIfAbsent(prefixSum, k -> new ArrayList<>()).add(i);
        }

        return result;
    }
    private static String format(List<int[]> result) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < result.size(); i++) {
            int[] pair = result.get(i);
            sb.append("(").append(pair[0]).append(",").append(pair[1]).append(")");
            if (i < result.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    public static void main(String[] args) {
        int[] arr1 = {1, 2, -3, 3, -1, 2};
        int[] arr2 = {4, -1, -3, 1, 2, -1};
        int[] arr3 = {1, 2, 3, 4};
        int[] arr4 = {0, 0, 0};
        int[] arr5 = {-3, 1, 2, -3, 4, 0};

        System.out.println("Input: [1, 2, -3, 3, -1, 2]");
        System.out.println("Output: " + format(findZeroSumSubarrays(arr1)));
        System.out.println();

        System.out.println("Input: [4, -1, -3, 1, 2, -1]");
        System.out.println("Output: " + format(findZeroSumSubarrays(arr2)));
        System.out.println();

        System.out.println("Input: [1, 2, 3, 4]");
        System.out.println("Output: " + format(findZeroSumSubarrays(arr3)));
        System.out.println();

        System.out.println("Input: [0, 0, 0]");
        System.out.println("Output: " + format(findZeroSumSubarrays(arr4)));
        System.out.println();

        System.out.println("Input: [-3, 1, 2, -3, 4, 0]");
        System.out.println("Output: " + format(findZeroSumSubarrays(arr5)));
    }
}
