/**
 * Abstract parent class containing information
 * common to all rescue cases.
 */
public abstract class RescueCase implements Rescuable {

    private final String caseId;
    private final String animalName;
    private final String species;
    private final String location;
    private final String ranger;
    private final int rescueDays;
    private final double dailyCareCost;
    private RescueStatus status;

    protected RescueCase(
            String caseId,
            String animalName,
            String species,
            String location,
            String ranger,
            int rescueDays,
            double dailyCareCost) {

        requireText(caseId, "Rescue Case ID");
        requireText(species, "Species");
        requireText(location, "Rescue Location");
        requireText(ranger, "Assigned Ranger");
        requirePositive(rescueDays, "Number of Rescue Days");
        requirePositive(dailyCareCost, "Daily Care Cost");

        this.caseId = caseId.trim();

        if (animalName == null || animalName.isBlank()) {
            this.animalName = "Unnamed";
        } else {
            this.animalName = animalName.trim();
        }

        this.species = species.trim();
        this.location = location.trim();
        this.ranger = ranger.trim();
        this.rescueDays = rescueDays;
        this.dailyCareCost = dailyCareCost;

        // Every new rescue case starts as reported.
        this.status = RescueStatus.REPORTED;
    }

    // Checks that a text field is not blank.
    protected static void requireText(String value, String field) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    field + " must not be blank.");
        }
    }

    // Checks that a number is greater than zero.
    protected static void requirePositive(double value, String field) {

        if (value <= 0 ||
                Double.isNaN(value) ||
                Double.isInfinite(value)) {

            throw new IllegalArgumentException(
                    field + " must be greater than zero.");
        }
    }

    // Checks that an integer is greater than zero.
    protected static void requirePositive(int value, String field) {

        if (value <= 0) {
            throw new IllegalArgumentException(
                    field + " must be greater than zero.");
        }
    }

    // These methods are different for each rescue type.
    public abstract String getRescueType();

    protected abstract double calculateAdditionalCost();

    public abstract RescuePriority determinePriority();

    public abstract String getSpecificDetails();

    /**
     * Calculates the total cost of a rescue case.
     */
    public double calculateTotalCost() {

        return rescueDays * dailyCareCost
                + calculateAdditionalCost();
    }

    // Starts the rescue operation.
    @Override
    public void startRescue() {

        if (status != RescueStatus.REPORTED) {
            throw new IllegalStateException(
                    "Only a reported rescue can be started. "
                    + "Current status: " + status);
        }

        status = RescueStatus.IN_PROGRESS;
    }

    // Completes the rescue operation.
    @Override
    public void completeRescue() {

        if (status != RescueStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Only an in-progress rescue can be completed. "
                    + "Current status: " + status);
        }

        status = RescueStatus.COMPLETED;
    }

    // Creates a summary for the rescue case.
    @Override
    public String generateSummary() {

        return String.format(
                "--- Rescue Summary ---%n" +
                "Rescue Case ID : %s%n" +
                "Rescue Type    : %s%n" +
                "Species        : %s%n" +
                "Assigned Ranger: %s%n" +
                "Priority       : %s%n" +
                "Status         : %s%n" +
                "Total Cost     : %s",

                caseId,
                getRescueType(),
                species,
                ranger,
                determinePriority(),
                status,
                formatMoney(calculateTotalCost()));
    }

    // Formats money values to two decimal places.
    public static String formatMoney(double amount) {

        return String.format(
                java.util.Locale.US,
                "R%,.2f",
                amount);
    }

    // Getters.
    public String getCaseId() {
        return caseId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getSpecies() {
        return species;
    }

    public String getLocation() {
        return location;
    }

    public String getRanger() {
        return ranger;
    }

    public int getRescueDays() {
        return rescueDays;
    }

    public double getDailyCareCost() {
        return dailyCareCost;
    }

    public RescueStatus getStatus() {
        return status;
    }

    // Allows the rescue status to be updated.
    public void setStatus(RescueStatus newStatus) {

        if (newStatus == null) {
            throw new IllegalArgumentException(
                    "Status must not be null.");
        }

        this.status = newStatus;
    }

    // Displays the rescue case information.
    @Override
    public String toString() {

        return String.format(
                "ID: %s | %s | Animal: %s | Species: %s | "
                + "Location: %s | Ranger: %s%n"
                + "Days: %d | Daily Cost: %s | Status: %s | "
                + "Priority: %s | Total: %s%n"
                + "%s",

                caseId,
                getRescueType(),
                animalName,
                species,
                location,
                ranger,
                rescueDays,
                formatMoney(dailyCareCost),
                status,
                determinePriority(),
                formatMoney(calculateTotalCost()),
                getSpecificDetails());
    }
}