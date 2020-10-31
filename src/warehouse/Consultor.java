package warehouse;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Consultor implements Runnable {


    private final ArrayList<Product> stock;
    private final int i;
    private int myCount;
    Product product;

    public Consultor(ArrayList<Product> stock, int i) {
        this.stock=stock;
        this.i=i;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            //PARA EL PROFESOR: En esta línea que va ahora, me salta un NullPointerException y
            // no consigo dar con el por qué...
            myCount=product.countMyStock(stock, i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
