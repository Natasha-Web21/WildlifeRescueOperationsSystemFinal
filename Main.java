import java.util.Scanner;

/**
 * Main class for the Wildlife Rescue Operations System.
 */
public class Main {

    private static final Scanner in =
            new Scanner(System.in);

    private static final RescueManager manager =
            new RescueManager();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            printMenu();

            switch (readInt(
                    "Enter choice: ",
                    1,
                    9)) {

                case 1 -> createCase();

                case 2 -> searchCase();

                case 3 -> updateStatus();

                case 4 -> displayAll();

                case 5 -> operate("start");

                case 6 -> operate("complete");

                case 7 -> operate("summary");

                case 8 ->
                        System.out.println(
                                manager.generateReport());

                case 9 -> {
                    running = false;
                    System.out.println("Goodbye.");
                }
            }
        }

        in.close();
    }

    // Displays the main menu.
    private static void printMenu() {

        System.out.println(
                "\n===== Wildlife SA Rescue Operations =====");

        System.out.println(
                "1. Create new rescue case");

        System.out.println(
                "2. Search rescue case by ID");

        System.out.println(
                "3. Update rescue status");

        System.out.println(
                "4. Display all rescue cases");

        System.out.println(
                "5. Start rescue operation");

        System.out.println(
                "6. Complete rescue operation");

        System.out.println(
                "7. Generate rescue summary");

        System.out.println(
                "8. Generate rescue report");

        System.out.println(
                "9. Exit");
    }

    // Creates a new rescue case.
    private static void createCase() {

        System.out.println("\nRescue type:");
        System.out.println("1. Injured Animal");
        System.out.println("2. Orphaned Animal");
        System.out.println("3. Endangered Species");

        int type = readInt(
                "Select type: ",
                1,
                3);

        String id;

        // Keep asking until a unique ID is entered.
        while (true) {

            id = readText(
                    "Rescue Case ID: ");

            if (manager.idExists(id)) {

                System.out.println(
                        "Error: that Rescue Case ID already exists.");

            } else {
                break;
            }
        }

        System.out.print(
                "Animal Name (optional): ");

        String name =
                in.nextLine().trim();

        String species =
                readText("Species: ");

        String location =
                readText("Rescue Location: ");

        String ranger =
                readText("Assigned Ranger: ");

        int days =
                readInt(
                        "Number of Rescue Days: ",
                        1,
                        Integer.MAX_VALUE);

        double daily =
                readPositiveDouble(
                        "Daily Care Cost (R): ");

        RescueCase rescueCase;

        switch (type) {

            case 1 -> rescueCase =
                    new InjuredAnimalRescue(
                            id,
                            name,
                            species,
                            location,
                            ranger,
                            days,
                            daily,
                            readText(
                                    "Injury Description: "),
                            readPositiveDouble(
                                    "Veterinary Treatment Cost (R): "),
                            readYesNo(
                                    "Surgery required?"));

            case 2 -> rescueCase =
                    new OrphanedAnimalRescue(
                            id,
                            name,
                            species,
                            location,
                            ranger,
                            days,
                            daily,
                            readInt(
                                    "Estimated Age (months): ",
                                    1,
                                    Integer.MAX_VALUE),
                            readPositiveDouble(
                                    "Feeding Cost (R): "),
                            readYesNo(
                                    "Foster care required?"));

            default -> rescueCase =
                    new EndangeredSpeciesRescue(
                            id,
                            name,
                            species,
                            location,
                            ranger,
                            days,
                            daily,
                            readText(
                                    "Conservation Classification: "),
                            readPositiveDouble(
                                    "Security Cost (R): "),
                            readYesNo(
                                    "Specialist team required?"));
        }

        manager.addCase(rescueCase);

        System.out.println(
                "\nRescue case created successfully.");

        System.out.println(rescueCase);
    }

    // Searches for a case using its ID.
    private static void searchCase() {

        RescueCase rescueCase =
                manager.findById(
                        readText(
                                "Rescue Case ID to search: "));

        if (rescueCase == null) {

            System.out.println(
                    "No rescue case found with that ID.");

        } else {

            System.out.println(rescueCase);
        }
    }

    // Allows the user to manually change a rescue status.
    private static void updateStatus() {

        RescueCase rescueCase =
                manager.findById(
                        readText("Rescue Case ID: "));

        if (rescueCase == null) {

            System.out.println(
                    "No rescue case found with that ID.");

            return;
        }

        System.out.println(
                "Current status: "
                + rescueCase.getStatus());

        System.out.println("1. Reported");
        System.out.println("2. In Progress");
        System.out.println("3. Completed");

        int choice =
                readInt(
                        "New status: ",
                        1,
                        3);

        rescueCase.setStatus(
                RescueStatus.values()[choice - 1]);

        System.out.println(
                "Status updated to: "
                + rescueCase.getStatus());
    }

    // Displays all rescue cases.
    private static void displayAll() {

        if (manager.getCaseCount() == 0) {

            System.out.println(
                    "No rescue cases recorded.");

            return;
        }

        for (RescueCase rescueCase :
                manager.getAllCases()) {

            System.out.println(rescueCase);
            System.out.println();
        }
    }

    // Starts, completes, or displays a summary.
    private static void operate(String action) {

        RescueCase rescueCase =
                manager.findById(
                        readText("Rescue Case ID: "));

        if (rescueCase == null) {

            System.out.println(
                    "No rescue case found with that ID.");

            return;
        }

        try {

            switch (action) {

                case "start" -> {

                    rescueCase.startRescue();

                    System.out.println(
                            "Rescue started. Status: "
                            + rescueCase.getStatus());
                }

                case "complete" -> {

                    rescueCase.completeRescue();

                    System.out.println(
                            "Rescue completed. Status: "
                            + rescueCase.getStatus());
                }

                default ->
                        System.out.println(
                                rescueCase.generateSummary());
            }

        } catch (IllegalStateException e) {

            System.out.println(
                    "Error: " + e.getMessage());
        }
    }

    // Reads text and prevents blank input.
    private static String readText(String prompt) {

        while (true) {

            System.out.print(prompt);

            String input =
                    in.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Error: this field cannot be blank.");
        }
    }

    // Reads an integer and validates its range.
    private static int readInt(
            String prompt,
            int min,
            int max) {

        while (true) {

            System.out.print(prompt);

            try {

                int value =
                        Integer.parseInt(
                                in.nextLine().trim());

                if (value >= min &&
                        value <= max) {

                    return value;
                }

            } catch (NumberFormatException ignored) {
                // Invalid input is handled below.
            }

            if (max == Integer.MAX_VALUE) {

                System.out.println(
                        "Error: enter a whole number "
                        + "of at least " + min + ".");

            } else {

                System.out.println(
                        "Error: enter a number between "
                        + min + " and " + max + ".");
            }
        }
    }

    // Reads a positive decimal number.
    private static double readPositiveDouble(
            String prompt) {

        while (true) {

            System.out.print(prompt);

            try {

                double value =
                        Double.parseDouble(
                                in.nextLine().trim());

                if (value > 0 &&
                        !Double.isNaN(value) &&
                        !Double.isInfinite(value)) {

                    return value;
                }

            } catch (NumberFormatException ignored) {
                // Invalid input is handled below.
            }

            System.out.println(
                    "Error: enter a number greater than zero.");
        }
    }

    // Reads a Yes or No answer.
    private static boolean readYesNo(
            String prompt) {

        while (true) {

            System.out.print(
                    prompt + " (Y/N): ");

            String input =
                    in.nextLine().trim();

            if (input.equalsIgnoreCase("Y")) {
                return true;
            }

            if (input.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println(
                    "Error: enter Y or N.");
        }
    }
}