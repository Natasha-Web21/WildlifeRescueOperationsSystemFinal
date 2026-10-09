/**
 * Represents a rescue case involving an orphaned animal.
 */
public class OrphanedAnimalRescue extends RescueCase {

    public static final double FOSTER_CARE_FEE = 2500.00;

    private final int ageInMonths;
    private final double feedingCost;
    private final boolean fosterCareRequired;

    public OrphanedAnimalRescue(
            String caseId,
            String animalName,
            String species,
            String location,
            String ranger,
            int days,
            double dailyCost,
            int ageInMonths,
            double feedingCost,
            boolean fosterCareRequired) {

        super(
                caseId,
                animalName,
                species,
                location,
                ranger,
                days,
                dailyCost);

        requirePositive(
                ageInMonths,
                "Estimated Age (Months)");

        requirePositive(
                feedingCost,
                "Feeding Cost");

        this.ageInMonths = ageInMonths;
        this.feedingCost = feedingCost;
        this.fosterCareRequired = fosterCareRequired;
    }

    @Override
    public String getRescueType() {
        return "Orphaned Animal";
    }

    // Calculates feeding and foster care costs.
    @Override
    protected double calculateAdditionalCost() {

        return feedingCost
                + (fosterCareRequired ? FOSTER_CARE_FEE : 0);
    }

    // Younger animals receive a higher priority.
    @Override
    public RescuePriority determinePriority() {

        if (ageInMonths <= 3) {
            return RescuePriority.HIGH;
        }

        if (ageInMonths <= 12) {
            return RescuePriority.MEDIUM;
        }

        return RescuePriority.LOW;
    }

    @Override
    public String getSpecificDetails() {

        return String.format(
                "Age: %d months | Feeding Cost: %s | "
                + "Foster Care Required: %s",

                ageInMonths,
                formatMoney(feedingCost),
                fosterCareRequired ? "Yes" : "No");
    }

    public int getAgeInMonths() {
        return ageInMonths;
    }

    public double getFeedingCost() {
        return feedingCost;
    }

    public boolean isFosterCareRequired() {
        return fosterCareRequired;
    }
}