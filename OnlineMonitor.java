public class OnlineMonitor implements Observer {
    private String monitorId;
    private int currentDisplay;
    
    public OnlineMonitor(String monitorId) {
        this.monitorId = monitorId;
        this.currentDisplay = 0;
    }
    
    @Override
    public void update(int currentNumber) {
        this.currentDisplay = currentNumber;
        displayCurrentNumber();
    }
    
    private void displayCurrentNumber() {
        System.out.println("Online Monitor " + monitorId + " - Currently serving: " + currentDisplay);
    }
    
    public int getCurrentDisplay() {
        return currentDisplay;
    }
    
    public String getMonitorId() {
        return monitorId;
    }
}