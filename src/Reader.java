/**
 * Created by doetken on 16.11.2016.
 */
public class Reader extends Thread{
    private static int readers = 0;

    private int number;
    private Database database;

    public Reader(Database database) {
        this.database = database;
        this.number = Reader.readers++;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep((int) (Math.random() * 5000));
            } catch (InterruptedException e) {
                this.database.read(this.number);
            }
        }
    }


}
