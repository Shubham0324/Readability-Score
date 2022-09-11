package readability;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class PrintService {

    private final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public void println(String text){
        try {
            out.write(text + "\n");
        } catch (Exception e) {
            System.out.println("Not able to write to buffer");
        }
        flushToConsole();
    }

    public void printf(String text, long data) {
        try {
            System.out.printf(text, data);
        } catch (Exception e) {
            System.out.println("Not able to write to console");
        }
    }

    public void printf(String text, String data, double data2, String data3) {
        try {
            System.out.printf(text, data, data2, data3);
        } catch (Exception e) {
            System.out.println("Not able to write to console");
        }
    }
    private void flushToConsole() {
        try {
            out.flush();
        } catch (Exception e) {
            System.out.println("Cannot write or flush to console");
        }
    }
}
