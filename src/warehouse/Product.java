package warehouse;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Product {

    private final ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.writeLock();

    public int countMyStock(ArrayList<Product> stock, int i) {
        int totalStock=34;
        readLock.lock();
        try {
            return totalStock;
        }finally {
            readLock.unlock();
        }
    }

}
