public static long maxContainerArea(int[] heights) {
    int left = 0;
    int right = heights.length - 1;
    long maxArea = 0;

    while (left < right) {
        long area = (long) Math.min(heights[left], heights[right])
                * (right - left);
        maxArea = Math.max(maxArea, area);

        if (heights[left] < heights[right]) {
            left++;
        } else {
            right--;
        }
    }

    return maxArea;
}