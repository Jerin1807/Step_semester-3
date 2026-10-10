import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostPopularOrder {
    // Returns {item, count}
    public static Object[] mostPopular(List<String> orders) {
        Map<String, Integer> counts = new HashMap<>();

        for (String item : orders) {
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }

        String bestItem = orders.get(0);
        int bestCount = counts.get(bestItem);

        // Scan in original order; update only for a strictly higher count.
        for (String item : orders) {
            int count = counts.get(item);
            if (count > bestCount) {
                bestItem = item;
                bestCount = count;
            }
        }

        return new Object[] {bestItem, bestCount};
    }
}