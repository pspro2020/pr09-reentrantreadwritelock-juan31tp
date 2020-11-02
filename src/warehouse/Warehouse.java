package warehouse;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Warehouse {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock(true);
    private final Lock rLock = rwLock.readLock();
    private final Lock wLock = rwLock.writeLock();

    ArrayList<Integer> stock = new ArrayList<>();

    public void addStock(int i) {
        wLock.lock();
        try{
            stock.add(i);
            System.out.printf(LocalTime.now().format(dtf) + " -> " + Thread.currentThread().getName() + " added the product " + i + " to the stock \n");
        } finally {
            wLock.unlock();
        }

    }

    public void countMyStock(int i) {
        int myStock;

        rLock.lock();
        try {
            myStock = countStock(i);
            System.out.printf(LocalTime.now().format(dtf) + " -> " + Thread.currentThread().getName() + " counted his stock and said the amount is " + myStock + "\n");
        } finally {
            rLock.unlock();
        }
    }

    private int countStock(int i) {
        int myStock = 0;
        
        for(int thisProduct : stock){
           if (i==thisProduct){
               myStock++;
           }
        }
        
        return myStock;
    }
}
