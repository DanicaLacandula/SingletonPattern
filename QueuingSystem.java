import java.util.ArrayList;
import java.util.List;

public class QueuingSystem implements Subject {
    private int currentNumber;
    private int nextNumber;
    private boolean isOnline;
    private List<Observer> onlineMonitors;
    
    public QueuingSystem() {
        this.currentNumber = 0;
        this.nextNumber = 1;
        this.isOnline = true;
        this.onlineMonitors = new ArrayList<>();
    }
    
    public synchronized int getNextNumber() {
        int number = nextNumber;
        nextNumber++;
        return number;
    }
    
    public synchronized void resetQueue(int number) {
        if (number >= 0) {
            this.nextNumber = number;
            this.currentNumber = number - 1;
            System.out.println("Queue reset to number: " + number);
            notifyObservers();
        }
    }
    
    public int getCurrentNumber() {
        return currentNumber;
    }
    
    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
        notifyObservers();
    }
    
    public boolean isOnline() {
        return isOnline;
    }
    
    public void setOnline(boolean online) {
        isOnline = online;
        notifyObservers();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        onlineMonitors.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer) {
        onlineMonitors.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : onlineMonitors) {
            observer.update(currentNumber);
        }
    }
}