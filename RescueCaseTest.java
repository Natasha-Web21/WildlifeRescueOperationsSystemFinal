import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests for the Wildlife Rescue Operations System.
 */
public class RescueCaseTest {

    @Test
    public void testInjuredAnimalCostCalculation() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "WR101",
                        "Jumbo",
                        "African Elephant",
                        "Kruger National Park",
                        "John",
                        5,
                        1000,
                        "Broken leg",
                        2000,
                        true);

        // 5 x R1000 + R2000 + R5000 surgery = R12000
        assertEquals(
                12000.00,
                rescue.calculateTotalCost(),
                0.01);
    }

    @Test
    public void testOrphanedAnimalCostCalculation() {

        OrphanedAnimalRescue rescue =
                new OrphanedAnimalRescue(
                        "WR102",
                        "Hope",
                        "White Rhino",
                        "Limpopo",
                        "Sarah",
                        4,
                        1000,
                        2,
                        1500,
                        true);

        // 4 x R1000 + R1500 + R2500 foster = R8000
        assertEquals(
                8000.00,
                rescue.calculateTotalCost(),
                0.01);
    }

    @Test
    public void testEndangeredAnimalCostCalculation() {

        EndangeredSpeciesRescue rescue =
                new EndangeredSpeciesRescue(
                        "WR103",
                        "Kito",
                        "Black Rhino",
                        "KwaZulu-Natal",
                        "David",
                        5,
                        1000,
                        "Critically Endangered",
                        3000,
                        true);

        // 5 x R1000 + R3000 + R8000 specialist = R16000
        assertEquals(
                16000.00,
                rescue.calculateTotalCost(),
                0.01);
    }

    @Test
    public void testInjuredAnimalPriority() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "WR104",
                        "Lion",
                        "Lion",
                        "Cape Town",
                        "Ranger A",
                        3,
                        500,
                        "Serious injury",
                        2000,
                        true);

        assertEquals(
                RescuePriority.CRITICAL,
                rescue.determinePriority());
    }

    @Test
    public void testOrphanedAnimalPriority() {

        OrphanedAnimalRescue rescue =
                new OrphanedAnimalRescue(
                        "WR105",
                        "Cub",
                        "Lion",
                        "Limpopo",
                        "Ranger B",
                        3,
                        400,
                        2,
                        1000,
                        true);

        assertEquals(
                RescuePriority.HIGH,
                rescue.determinePriority());
    }

    @Test
    public void testEndangeredAnimalPriority() {

        EndangeredSpeciesRescue rescue =
                new EndangeredSpeciesRescue(
                        "WR106",
                        "Rhino",
                        "Black Rhino",
                        "Limpopo",
                        "Ranger C",
                        5,
                        600,
                        "Critically Endangered",
                        2000,
                        false);

        assertEquals(
                RescuePriority.CRITICAL,
                rescue.determinePriority());
    }

    @Test
    public void testStatusUpdate() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "WR107",
                        "Elephant",
                        "Elephant",
                        "Kruger",
                        "Ranger D",
                        2,
                        500,
                        "Injury",
                        1000,
                        false);

        assertEquals(
                RescueStatus.REPORTED,
                rescue.getStatus());

        rescue.startRescue();

        assertEquals(
                RescueStatus.IN_PROGRESS,
                rescue.getStatus());

        rescue.completeRescue();

        assertEquals(
                RescueStatus.COMPLETED,
                rescue.getStatus());
    }

    @Test
    public void testSearchExistingCase() {

        RescueManager manager =
                new RescueManager();

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "WR108",
                        "Leopard",
                        "Leopard",
                        "Mpumalanga",
                        "Ranger E",
                        2,
                        500,
                        "Leg injury",
                        1000,
                        false);

        manager.addCase(rescue);

        assertEquals(
                rescue,
                manager.findById("wr108"));
    }

    @Test
    public void testSearchCaseNotFound() {

        RescueManager manager =
                new RescueManager();

        assertTrue(
                manager.findById("UNKNOWN") == null);
    }

    @Test
    public void testDuplicateCaseIdRejected() {

        RescueManager manager =
                new RescueManager();

        InjuredAnimalRescue first =
                new InjuredAnimalRescue(
                        "WR109",
                        "Animal 1",
                        "Lion",
                        "Location 1",
                        "Ranger 1",
                        2,
                        500,
                        "Injury",
                        1000,
                        false);

        InjuredAnimalRescue second =
                new InjuredAnimalRescue(
                        "WR109",
                        "Animal 2",
                        "Lion",
                        "Location 2",
                        "Ranger 2",
                        2,
                        500,
                        "Injury",
                        1000,
                        false);

        manager.addCase(first);

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.addCase(second));
    }
}