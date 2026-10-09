/**
 * Represents a rescue case involving an injured animal.
 */
public class InjuredAnimalRescue extends RescueCase {

    public static final double SURGERY_FEE = 5000.00;
    public static final double HIGH_VET_COST_THRESHOLD = 10000.00;

    private final String injuryDescription;
    private final double veterinaryCost;
    private final boolean surgeryRequired;

    public InjuredAnimalRescue(
            String caseId,
            String animalName,
            String species,
            String location,
            String ranger,
            int days,
            double dailyCost,
            String injuryDescription,
            double veterinaryCost,
            boolean surgeryRequired) {

        super(
                caseId,
                animalName,
                species,
                location,
                ranger,
                days,
                dailyCost);

        requireText(injuryDescription, "Injury Description");
        requirePositive(
                veterinaryCost,
                "Veterinary Treatment Cost");

        this.injuryDescription = injuryDescription.trim();
        this.veterinaryCost = veterinaryCost;
        this.surgeryRequired = surgeryRequired;
    }

    @Override
    public String getRescueType() {
        return "Injured Animal";
    }

    // Calculates veterinary costs and surgery costs.
    @Override
    protected double calculateAdditionalCost() {

        return veterinaryCost
                + (surgeryRequired ? SURGERY_FEE : 0);
    }

    // Determines the priority of the injured animal.
    @Override
    public RescuePriority determinePriority() {

        if (surgeryRequired) {
            return RescuePriority.CRITICAL;
        }

        if (veterinaryCost >= HIGH_VET_COST_THRESHOLD) {
            return RescuePriority.HIGH;
        }

        return RescuePriority.MEDIUM;
    }

    @Override
    public String getSpecificDetails() {

        return String.format(
                "Injury: %s | Vet Cost: %s | Surgery Required: %s",

                injuryDescription,
                formatMoney(veterinaryCost),
                surgeryRequired ? "Yes" : "No");
    }

    public String getInjuryDescription() {
        return injuryDescription;
    }

    public double getVeterinaryCost() {
        return veterinaryCost;
    }

    public boolean isSurgeryRequired() {
        return surgeryRequired;
    }
}