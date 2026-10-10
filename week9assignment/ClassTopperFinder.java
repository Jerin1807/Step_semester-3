public class ClassTopperFinder {
    // Returns {rowIndex, totalMarks}
    public static int[] findTopper(int[][] marks) {
        int bestRow = 0;
        int bestTotal = -1;

        for (int row = 0; row < marks.length; row++) {
            int total = 0;

            for (int mark : marks[row]) {
                total += mark;
            }

            // Strictly greater preserves the earliest row in a tie.
            if (total > bestTotal) {
                bestTotal = total;
                bestRow = row;
            }
        }

        return new int[] {bestRow, bestTotal};
    }
}