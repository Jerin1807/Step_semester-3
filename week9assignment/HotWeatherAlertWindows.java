public class HotWeatherAlertWindows {
    public static int countAlerts(int[] readings, int k, int threshold) {
        long windowSum = 0;

        for (int i = 0; i < k; i++) {
            windowSum += readings[i];
        }

        int alerts = windowSum >= (long) k * threshold ? 1 : 0;

        for (int i = k; i < readings.length; i++) {
            windowSum += readings[i] - readings[i - k];

            if (windowSum >= (long) k * threshold) {
                alerts++;
            }
        }

        return alerts;
    }
}