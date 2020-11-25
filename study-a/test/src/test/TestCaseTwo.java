package test;

public class TestCaseTwo {
    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
     * 示例:
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public int trap(int[] height) {
        int maxInx = 0, max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                maxInx = i;
                max = height[i];
            }
        }
        return trapLeft(height, maxInx) + trapRight(height, maxInx);
    }

    public int trapLeft(int[] height, int index) {
        int nowMaxInx = index, nowMax = 0, sum = 0;
        for (int i = index - 1; i >= 0; i--) {
            if (height[i] > nowMax) {
                nowMaxInx = i;
                nowMax = height[i];
            }
        }
        if (nowMaxInx != index) {
            sum = nowMax * (index - nowMaxInx - 1);
            if (sum > 0) {
                for (int i = nowMaxInx + 1; i < index; i++) {
                    sum = sum - height[i];
                }
            }

            return sum + trapLeft(height, nowMaxInx);
        }
        return sum;
    }

    public int trapRight(int[] height, int index) {
        int nowMaxInx = index, nowMax = 0, sum = 0;
        for (int i = index + 1; i < height.length; i++) {
            if (height[i] > nowMax) {
                nowMaxInx = i;
                nowMax = height[i];
            }
        }
        if (nowMaxInx != index) {
            sum = nowMax * (nowMaxInx - index - 1);
            if (sum > 0) {
                for (int i = index + 1; i < nowMaxInx; i++) {
                    sum = sum - height[i];
                }
            }
            if (nowMaxInx < height.length - 1) {
                return sum + trapRight(height, nowMaxInx);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        //int[] a = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1, 2,0,2};
        //int[] a = new int[]{4, 2, 0, 3, 2, 5};
        //int[] a = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] a = {5, 8, 9, 4, 9, 6, 1, 4};
        // int[] a={4,2,0,3,2,5};
        new TestCaseTwo().trap(a);
    }

}
