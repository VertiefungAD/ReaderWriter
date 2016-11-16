/**
 * Created by doetken on 16.11.2016.
 */
public class Simulator {
    public static void main(String[] args) {

        final int READERS = 10;
        final int WRITERS = 2;
        Database database = new Database();
        for (int i = 0; i < READERS; i++) {
            new Reader(database).start();
        }
        for (int i = 0; i < WRITERS; i++) {
            new Writer(database).start();
        }
    }
}

