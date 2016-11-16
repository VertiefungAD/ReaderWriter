/**
 * Created by doetken on 16.11.2016.
 */
public class Writer extends Thread {
    private static int writers = 0;

    private int number;
    private Database database;

    public Writer(Database database) {
        this.database = database;
        this.number = Writer.writers++;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep((int) (Math.random() * 5000));
            } catch (InterruptedException e) {
            e.getLocalizedMessage();
            }
                this.database.write(this.number);

        }
    }
}
