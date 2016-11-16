/**
 * Created by doetken on 16.11.2016.
 */
public class Database {
    private int readers;
    private volatile boolean frei = true;

    public Database() {
        this.readers = 10;
    }

    public void read(int number) {
        while (this.frei) {
            synchronized (this) {
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
                if (this.readers == 0) this.notifyAll();
                System.out.println("Reader " + number + " stoppt lesen.");

            }
        }
    }

    public synchronized void write(int number) {
        this.frei = false;
        while (this.readers != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.getLocalizedMessage();
            }
            System.out.println("Writer " + number + " beginnt schreiben.");
            this.notifyAll();

        }
    }
}
