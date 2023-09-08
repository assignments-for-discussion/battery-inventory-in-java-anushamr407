package bunchbysoh;

public class Main {
    static class CountsBySoH {
        public int healthy = 0;
        public int exchange = 0;
        public int failed = 0;
    }

    static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
        CountsBySoH counts = new CountsBySoH();

        for (int capacity : presentCapacities) {
            double soh = calculateSoH(capacity, 120); // Assuming rated capacity is 120 Ah
            classifyBattery(counts, soh);
        }

        return counts;
    }

    static double calculateSoH(int presentCapacity, int ratedCapacity) {
        return (double) presentCapacity / ratedCapacity * 100.0;
    }

    static void classifyBattery(CountsBySoH counts, double soh) {
        if (soh > 80.0) {
            counts.healthy++;
        } else if (soh >= 65.0) {
            counts.exchange++;
        } else {
            counts.failed++;
        }
    }

    static void testBucketingByHealth() {
        System.out.println("Counting batteries by SoH...\n");
        int[] presentCapacities = {115, 118, 80, 95, 91, 77};
        CountsBySoH counts = countBatteriesByHealth(presentCapacities);
        assert(counts.healthy == 2);
        assert(counts.exchange == 3);
        assert(counts.failed == 1);
        System.out.println("Done counting :)\n");
    }

    public static void main(String[] args) {
        testBucketingByHealth();
    }
}
