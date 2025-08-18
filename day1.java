class Solution {
    private static final double EPSILON = 1e-6; 
    private static final double TARGET = 24.0;

    public boolean judgePoint24(int[] cards) {
        double[] nums = new double[cards.length];
        for (int i = 0; i < cards.length; i++) {
            nums[i] = cards[i];
        }
        return solve(nums);
    }

    private boolean solve(double[] nums) {
        int n = nums.length;
        if (n == 1) {
            return Math.abs(nums[0] - TARGET) < EPSILON;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                double[] next = new double[n - 1];
                int index = 0;
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) {
                        next[index++] = nums[k];
                    }
                }
                for (double val : compute(nums[i], nums[j])) {
                    next[index] = val;
                    if (solve(next)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private double[] compute(double a, double b) {
        return new double[]{
            a + b,
            a - b,
            b - a,
            a * b,
            (Math.abs(b) < EPSILON ? Double.NaN : a / b),
            (Math.abs(a) < EPSILON ? Double.NaN : b / a)
        };
    }
}

