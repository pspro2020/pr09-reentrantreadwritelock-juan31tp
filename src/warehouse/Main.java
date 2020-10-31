package warehouse;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        ArrayList<Product> stock = new ArrayList<>();

        Thread[] consultors = new Thread[3];
        for (int i=0; i<consultors.length;i++){
            consultors[i]=new Thread(new Consultor(stock, i+1));
        }

        Thread provider = new Thread(new Warehouse());

        provider.start();

        //Wait to let it have some stock before consultors count
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0; i<consultors.length; i++){
            consultors[i].start();
        }
    }
}
