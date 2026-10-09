/**
 * Represents a rescue case involving an endangered species.
 */
public class EndangeredSpeciesRescue extends RescueCase {

    public static final double SPECIALIST_TEAM_FEE = 8000.00;

    private final String classification;
    private final double securityCost;
    private final boolean specialistTeamRequired;

    public EndangeredSpeciesRescue(
            String caseId,
            String animalName,
            String species,
            String location,
            String ranger,
            int days,
            double dailyCost,
            String classification,
            double securityCost,
            boolean specialistTeamRequired) {

        super(
                caseId,
                animalName,
                species,
                location,
                ranger,
                days,
                dailyCost);

        requireText(
                classification,
                "Conservation Classification");

        requirePositive(
                securityCost,
                "Security Cost");

        this.classification = classification.trim();
        this.securityCost = securityCost;
        this.specialistTeamRequired = specialistTeamRequired;
    }

    @Override
    public String getRescueType() {
        return "Endangered Species";
    }

    // Calculates security and specialist team costs.
    @Override
    protected double calculateAdditionalCost() {

        return securityCost
                + (specialistTeamRequired
                ? SPECIALIST_TEAM_FEE
                : 0);
    }

    // Determines priority using classification and specialist requirements.
    @Override
    public RescuePriority determinePriority() {

        if (specialistTeamRequired ||
                classification.equalsIgnoreCase(
                        "Critically Endangered")) {

            return RescuePriority.CRITICAL;
        }

        return RescuePriority.HIGH;
    }

    @Override
    public String getSpecificDetails() {

        return String.format(
                "Classification: %s | Security Cost: %s | "
                + "Specialist Team Required: %s",

                classification,
                formatMoney(securityCost),
                specialistTeamRequired ? "Yes" : "No");
    }

    public String getClassification() {
        return classification;
    }

    public double getSecurityCost() {
        return securityCost;
    }

    public boolean isSpecialistTeamRequired() {
        return specialistTeamRequired;
    }
}