import java.util.HashMap;
import java.util.Map;

public class BatteryClassification {
    public static void main(String[] args) {
        double ratedCapacity = 120.0; // Rated capacity of all batteries (in Ah)
        double[] presentCapacities = {105.0, 95.0, 60.0, 110.0, 75.0, 100.0};

        Map<String, Integer> classificationCounts = countBatteryClassification(presentCapacities, ratedCapacity);

        System.out.println("Classification Counts:");
        for (Map.Entry<String, Integer> entry : classificationCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static Map<String, Integer> countBatteryClassification(double[] presentCapacities, double ratedCapacity) {
        Map<String, Integer> classifications = new HashMap<>();
        classifications.put("Healthy", 0);
        classifications.put("Replace", 0);
        classifications.put("Failed", 0);

        for (double presentCapacity : presentCapacities) {
            double soh = calculateSoH(presentCapacity, ratedCapacity);
            String classification = classifyBattery(soh);
            classifications.put(classification, classifications.get(classification) + 1);
        }

        return classifications;
    }

    public static double calculateSoH(double presentCapacity, double ratedCapacity) {
        return (presentCapacity / ratedCapacity) * 100.0;
    }

    public static String classifyBattery(double soh) {
        if (soh > 80.0) {
            return "Healthy";
        } else if (soh >= 65.0) {
            return "Replace";
        } else {
            return "Failed";
        }
    }
}
