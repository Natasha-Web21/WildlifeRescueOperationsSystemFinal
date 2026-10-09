/**
 * Status values used by rescue cases.
 */
public enum RescueStatus {

    REPORTED("Reported"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private final String label;

    RescueStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}