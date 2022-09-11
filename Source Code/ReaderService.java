package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderService {

    private final ReadServicePrivate callReadingService = new ReadServicePrivate();

    protected String readFile(String pathToFile) {
        return callReadingService.readFile(pathToFile);
    }

    private class ReadServicePrivate {

        private String readFile(String pathToFile){

            StringBuilder text = new StringBuilder("");
            File file = new File(pathToFile);

            try ( Scanner sc = new Scanner(file)){
                while (sc.hasNext()) {
                    text.append(sc.nextLine());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            return text.toString();
        }
    }
}
