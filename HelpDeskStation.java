public class HelpDeskStation {
    private int stationId;
    private String stationName;
    private int currentServing;
    private QueuingSystem queuingSystem;
    
    public HelpDeskStation(int stationId, String stationName, QueuingSystem queuingSystem) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.queuingSystem = queuingSystem;
        this.currentServing = 0;
    }
    
    public synchronized void serveNext() {
        int nextNumber = queuingSystem.getNextNumber();
        this.currentServing = nextNumber;
        queuingSystem.setCurrentNumber(nextNumber);
        System.out.println(stationName + " is now serving number: " + nextNumber);
    }
    
    public synchronized void resetQueueNumber(int number) {
        queuingSystem.resetQueue(number);
        this.currentServing = number - 1;
        System.out.println(stationName + " reset queue to number: " + number);
    }
    
    public int getCurrentServing() {
        return currentServing;
    }
    
    public int getStationId() {
        return stationId;
    }
    
    public String getStationName() {
        return stationName;
    }
}