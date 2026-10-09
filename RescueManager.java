import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages all rescue cases using an ArrayList.
 */
public class RescueManager {

    private final ArrayList<RescueCase> cases =
            new ArrayList<>();

    // Adds a new rescue case to the ArrayList.
    public void addCase(RescueCase rescueCase) {

        if (rescueCase == null) {
            throw new IllegalArgumentException(
                    "Rescue case must not be null.");
        }

        // Rescue Case IDs must be unique.
        if (idExists(rescueCase.getCaseId())) {
            throw new IllegalArgumentException(
                    "Rescue Case ID '"
                    + rescueCase.getCaseId()
                    + "' already exists.");
        }

        cases.add(rescueCase);
    }

    public boolean idExists(String id) {
        return findById(id) != null;
    }

    // Searches for a rescue case using its ID.
    public RescueCase findById(String id) {

        if (id == null) {
            return null;
        }

        for (RescueCase rescueCase : cases) {

            if (rescueCase.getCaseId()
                    .equalsIgnoreCase(id.trim())) {

                return rescueCase;
            }
        }

        return null;
    }

    // Updates the status of a rescue case.
    public boolean updateStatus(
            String id,
            RescueStatus newStatus) {

        RescueCase rescueCase = findById(id);

        if (rescueCase == null || newStatus == null) {
            return false;
        }

        rescueCase.setStatus(newStatus);
        return true;
    }

    // Returns the stored cases without allowing the list itself to be changed.
    public List<RescueCase> getAllCases() {
        return Collections.unmodifiableList(cases);
    }

    public int getCaseCount() {
        return cases.size();
    }

    // Calculates the total estimated cost of all cases.
    public double getTotalEstimatedCost() {

        double total = 0;

        for (RescueCase rescueCase : cases) {
            total += rescueCase.calculateTotalCost();
        }

        return total;
    }

    // Creates the final rescue report.
    public String generateReport() {

        StringBuilder report =
                new StringBuilder();

        String line = "=".repeat(120);

        report.append(line)
                .append(System.lineSeparator());

        report.append(
                "              WILDLIFE SA - RESCUE REPORT")
                .append(System.lineSeparator());

        report.append(line)
                .append(System.lineSeparator());

        if (cases.isEmpty()) {

            report.append(
                    "No rescue cases recorded.")
                    .append(System.lineSeparator());

        } else {

            for (RescueCase rescueCase : cases) {

                report.append(
                        rescueCase.generateSummary())
                        .append(System.lineSeparator());

                report.append(
                        rescueCase.getSpecificDetails())
                        .append(System.lineSeparator());

                report.append("-".repeat(80))
                        .append(System.lineSeparator());
            }
        }

        report.append(
                "Total Rescue Cases         : ")
                .append(getCaseCount())
                .append(System.lineSeparator());

        report.append(
                "Total Estimated Rescue Cost: ")
                .append(
                        RescueCase.formatMoney(
                                getTotalEstimatedCost()))
                .append(System.lineSeparator());

        report.append(line);

        return report.toString();
    }
}