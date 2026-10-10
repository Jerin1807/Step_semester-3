import java.util.ArrayList;
import java.util.List;

public class MergeTokenQueues {
    public static List<Integer> mergeTokens(int[] counterA, int[] counterB) {
        List<Integer> merged = new ArrayList<>(
                counterA.length + counterB.length);

        int i = 0;
        int j = 0;

        while (i < counterA.length && j < counterB.length) {
            if (counterA[i] <= counterB[j]) {
                merged.add(counterA[i++]);
            } else {
                merged.add(counterB[j++]);
            }
        }

        while (i < counterA.length) {
            merged.add(counterA[i++]);
        }

        while (j < counterB.length) {
            merged.add(counterB[j++]);
        }

        return merged;
    }
}