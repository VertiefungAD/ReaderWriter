/**
 * Created by doetken on 16.11.2016.
 */
public class Database {
    private int readers = 0;
    private int writers = 0;
    private int readWant = 0;


    public void read(int number) {
        System.out.println("Reader " + number + " möchte lesen!");
        readWant++;
        synchronized (this) {
            while (writers > 0)
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            this.readers++;
            System.out.println("Reader " + number + " beginnt lesen.");
        }

        try {
            Thread.sleep((int) (Math.random() * 5000));
        } catch (InterruptedException e) {
            e.getLocalizedMessage();
        }
        synchronized (this) {
            this.readers--;
            if (writers > 0) this.readers = 0;
            if (this.readers == 0) this.notifyAll();
            System.out.println("Reader " + number + " stoppt lesen.");
            readWant--;
        }
    }


    public synchronized void write(int number) {
        System.out.println("Writer " + number + " möchte schreiben!");
        writers++;

        while (readWant != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (this.readers != 0) {
            readWant = 0;
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.getLocalizedMessage();
            }
            System.out.println("Writer " + number + " beginnt schreiben.");

            try {
                Thread.sleep((int) (Math.random() * 5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Writer " + number + " stoppt schreiben.");
            writers--;
            this.notifyAll();
        }

    }
}
