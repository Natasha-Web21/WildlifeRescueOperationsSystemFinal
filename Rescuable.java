/**
 * Operations that every rescue case must support.
 */
public interface Rescuable {

    void startRescue();

    void completeRescue();

    String generateSummary();
}