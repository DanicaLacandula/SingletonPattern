import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
    private QueuingSystem queuingSystem;
    private List<HelpDeskStation> stations;
    private List<OnlineMonitor> monitors;
    
    public MainApplication() {
        this.queuingSystem = new QueuingSystem();
        this.stations = new ArrayList<>();
        this.monitors = new ArrayList<>();
        initializeSystem();
    }
    
    private void initializeSystem() {
        // Initialize help desk stations
        stations.add(new HelpDeskStation(1, "Help Desk Station 1", queuingSystem));
        stations.add(new HelpDeskStation(2, "Help Desk Station 2", queuingSystem));
        stations.add(new HelpDeskStation(3, "Help Desk Station 3", queuingSystem));
        
        // Initialize online monitors
        OnlineMonitor monitor1 = new OnlineMonitor("MON-001");
        OnlineMonitor monitor2 = new OnlineMonitor("MON-002");
        
        monitors.add(monitor1);
        monitors.add(monitor2);
        
        // Register monitors as observers
        queuingSystem.registerObserver(monitor1);
        queuingSystem.registerObserver(monitor2);
        
        System.out.println("Pag-ibig Queuing System Initialized");
        System.out.println("3 Help Desk Stations created");
        System.out.println("2 Online Monitors registered");
    }
    
    public void simulateOperations() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("\n=== Pag-ibig Centralized Queuing System ===");
        
        while (running) {
            System.out.println("\nOptions:");
            System.out.println("1. Get next queue number");
            System.out.println("2. Serve next customer at station");
            System.out.println("3. Reset queue number");
            System.out.println("4. Display current status");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    int nextNumber = queuingSystem.getNextNumber();
                    System.out.println("Next queue number issued: " + nextNumber);
                    break;
                    
                case 2:
                    System.out.println("Select station (1-3): ");
                    int stationChoice = scanner.nextInt();
                    if (stationChoice >= 1 && stationChoice <= 3) {
                        stations.get(stationChoice - 1).serveNext();
                    } else {
                        System.out.println("Invalid station selection");
                    }
                    break;
                    
                case 3:
                    System.out.println("Select station to reset (1-3): ");
                    int resetStation = scanner.nextInt();
                    System.out.print("Enter new queue number: ");
                    int newNumber = scanner.nextInt();
                    if (resetStation >= 1 && resetStation <= 3) {
                        stations.get(resetStation - 1).resetQueueNumber(newNumber);
                    } else {
                        System.out.println("Invalid station selection");
                    }
                    break;
                    
                case 4:
                    displayCurrentStatus();
                    break;
                    
                case 5:
                    running = false;
                    System.out.println("System shutting down...");
                    break;
                    
                default:
                    System.out.println("Invalid option");
            }
        }
        scanner.close();
    }
    
    private void displayCurrentStatus() {
        System.out.println("\n=== Current System Status ===");
        System.out.println("Current Queue Number: " + queuingSystem.getCurrentNumber());
        
        System.out.println("\nHelp Desk Stations:");
        for (HelpDeskStation station : stations) {
            System.out.println(station.getStationName() + " - Serving: " + station.getCurrentServing());
        }
        
        System.out.println("\nOnline Monitors:");
        for (OnlineMonitor monitor : monitors) {
            System.out.println(monitor.getMonitorId() + " - Displaying: " + monitor.getCurrentDisplay());
        }
    }
    
    public static void main(String[] args) {
        MainApplication app = new MainApplication();
        app.simulateOperations();
    }
}