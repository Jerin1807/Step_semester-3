public static class WarehouseResult {
    public final long totalItems;
    public final int[] maxCoordinate;

    public WarehouseResult(long totalItems, int[] maxCoordinate) {
        this.totalItems = totalItems;
        this.maxCoordinate = maxCoordinate;
    }
}

public static WarehouseResult warehouseSummary(int[][] grid) {
    long total = 0;
    int maxValue = -1;
    int maxRow = -1;
    int maxCol = -1;

    for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid[row].length; col++) {
            int value = grid[row][col];
            total += value;

            if (value > maxValue) {
                maxValue = value;
                maxRow = row;
                maxCol = col;
            }
        }
    }

    return new WarehouseResult(total, new int[] {maxRow, maxCol});
}