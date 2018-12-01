import Validations.ValidateInput;
import Validations.ValidatePlate;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;

public class InsuranceApp {
    public static void main(String[] args) throws FileNotFoundException {

        // Date format


        LocalDate today = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Create DataArrays
        // First create InsuranceInfo

        ArrayList<InsuranceInfo> allInsurances = new ArrayList<InsuranceInfo>();
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\manfr\\IdeaProjects\\InsuranceProject\\InsuranceInfo.csv")));
        String line;
        try {
            while ((line = br.readLine()) != null) {

                String[] entries = line.split(";");
                if (today.isBefore(LocalDate.parse(entries[2], pattern))) {
                    InsuranceInfo info = new InsuranceInfo(Integer.parseInt(entries[0]), entries[1], entries[2], Float.parseFloat(entries[3]), true);
                    allInsurances.add(info);
                } else {
                    InsuranceInfo info = new InsuranceInfo(Integer.parseInt(entries[0]), entries[1], entries[2], Float.parseFloat(entries[3]), false);
                    allInsurances.add(info);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Then create all vehicles with their InsuranceInfo and additional information
        ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();
        // Read vehicle info
        br = new BufferedReader(new FileReader(new File("C:\\Users\\manfr\\IdeaProjects\\InsuranceProject\\VehicleInfo.csv")));

        try {
            //Iteration to put properly InsuranceInfo
            int iter = 0;
            while ((line = br.readLine()) != null) {

                String[] entries = line.split(";");
                Vehicle vehicle = new Vehicle(entries[0], entries[1], entries[2], allInsurances.get(iter));
                allVehicles.add(vehicle);
                iter++;
            }
        } catch (IOException e) {
            System.out.println("File error");
        }
        // Print start menu
        while (true) {
            menu();
            // Validate input: 1-5
            ValidateInput validateInput = new ValidateInput();
            int input;
            input = validateInput.ValidateInput();
            if (input == 1) {
                // Validate plate
                System.out.println("Please insert plate:");
                boolean validPlate = false;
                // Initialize string text for later use
                String text = "";
                // First assume input is wrong -> false
                while (!validPlate) {
                    Scanner scan = new Scanner(System.in);
                    text = scan.nextLine();
                    ValidatePlate plate = new ValidatePlate();
                    // isValid returns boolean value, if valid then true
                    validPlate = plate.isValid(text);
                    // if validPlate still false, then read again
                    if (!validPlate) System.out.println("Error: Please insert a valid plate:");
                }

                // Search based on plate
                boolean exists = false;
                for (Vehicle vehicleT : allVehicles) {
                    if (vehicleT.getPlate().equals(text)) {
                        exists = true;
                        if (vehicleT.getInsuranceInfo().isInsured()) System.out.println("Vehicle with plate " + text + " exists and is insured.");
                        else System.out.println("Vehicle with plate " + text + " exists and its license is expired.");
                        break;
                    }
                }
                if (!exists) System.out.println("Error: Vehicle with plate " + text + " does not exist!");

            } else if (input == 2) {
                System.out.println("---Enter export type:\n1) Console\n2) File");
                // Export type input2 == 1 or 2
                Scanner expType = new Scanner(System.in);
                int input2 = expType.nextInt();
                System.out.println("Please insert days:");
                Scanner scan = new Scanner(System.in);
                int days = scan.nextInt();
                LocalDate expiryDate = today.plusDays(days);
                // input 1 console
                if (input2 == 1) {
                    for (Vehicle vehicle : allVehicles) {
                        if (expiryDate.isAfter(LocalDate.parse(vehicle.getInsuranceInfo().getExpiryDate(), pattern)))
                            System.out.println(vehicle.getPlate() + " with expiration date " + vehicle.getInsuranceInfo().getExpiryDate());
                    }
                } else {
                    // input 2 file
                    try {
                        String COMMA_DELIMITER = ",";
                        String NEW_LINE = "\n";
                        String FILE_HEADER = "Plate number";
                        FileWriter fw = new FileWriter("output.csv");
                        fw.append(FILE_HEADER);
                        for (Vehicle vehicle : allVehicles) {
                            if (expiryDate.isAfter(LocalDate.parse(vehicle.getInsuranceInfo().getExpiryDate(), pattern))) {
                                fw.append(NEW_LINE);
                                fw.append(vehicle.getPlate());
                            }
                        }

                        // close file
                        fw.flush();
                        fw.close();
                        System.out.println("File updated");
                    } catch (Exception E) {
                        System.out.println(E.getMessage());
                    }
                }
            } else if (input == 3) {

                sorting(allVehicles);

            } else if (input == 4){
                // insert fine cost for uninsured vehicle
                Scanner scan = new Scanner(System.in);
                double fine = scan.nextDouble();
                System.out.println("Fine for each vehicle is: "+ fine);
                Map<String, Integer> fineMap = new HashMap<>();
                for (Vehicle vehicle : allVehicles) {
                    if(!vehicle.getInsuranceInfo().isInsured()) {
                        if (fineMap.containsKey(vehicle.getInsuranceInfo().getName())) {
                            int count = fineMap.get(vehicle.getInsuranceInfo().getName());
                            fineMap.put(vehicle.getInsuranceInfo().getName(), (count + 1));
                        } else {
                            fineMap.put(vehicle.getInsuranceInfo().getName(), 1);
                        }
                    }
                }
                System.out.println("The following customers have uninsured vehicles: ");
                System.out.println(fineMap);
            } else if (input == 5) {
                // Exit from menu
                System.out.println("Thank you!");
                break;
            }
        }
    }

    public static void menu() {
        System.out.println("Welcome to Insurance App!\n---Please select functionality to perform:");
        System.out.println("1) Vehicle Insurance status\n2) Forecoming Expiries\n" +
                "3) Expire by plate\n4) Fine calculation\n5) Exit menu");
    }

    public static void sorting(ArrayList<Vehicle> allVehicles) {
        Map<String, ArrayList<InsuranceInfo>> groupByPlate = new TreeMap<>();

        for (Vehicle vehicleT : allVehicles) {
            if (!vehicleT.getInsuranceInfo().isInsured()) {

                if (groupByPlate.containsKey(vehicleT.getPlate())) {
                    ArrayList<InsuranceInfo> group = groupByPlate.get(vehicleT.getPlate());
                    group.add(vehicleT.getInsuranceInfo());
                } else {
                    ArrayList<InsuranceInfo> group = new ArrayList<>();
                    group.add(vehicleT.getInsuranceInfo());
                    groupByPlate.put(vehicleT.getPlate(), group);
                }
            }
        }

        groupByPlate.forEach((key, value) -> System.out.println(key));
    }
}


