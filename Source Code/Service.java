package readability;

public class Service {


    private ReaderService readerService = null;
    private TextAnalysis textAnalysis = null;
    private PrintService printService = null;

    private AgeGroupAssign ageGroupAssign = null;

    Service() {
        readerService = new ReaderService();
        textAnalysis = new TextAnalysis();
        printService = new PrintService();
        ageGroupAssign = new AgeGroupAssign();
    }

    protected String getTextFromFile(String pathToFile) {
       return readerService.readFile(pathToFile);
    }

    protected long[] doAnalysis(String text) {
        return textAnalysis.doFileTextAnalysis(text);
    }

    protected void println(String text) {
        printService.println(text);
    }

    protected void printf(String text,long data) {
        printService.printf(text, data);
    }

    protected int ageGroupAssignBasedOnScore(double textScore, String text) {
        return ageGroupAssign.ageGroupAssignBasedOnScore((long) textScore, text);
    }



}



