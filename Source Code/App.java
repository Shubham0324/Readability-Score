package readability;


import java.util.Scanner;

public class App {

    private Service service = null;


    public void executeApplication(String pathToFile) {
        initializeServices();
        runApplication(pathToFile);
    }

    private void initializeServices() {
        service = new Service();
    }

    private void runApplication(String pathToFile) {

        String text = getTextFromFile(pathToFile);
        printText(text);
        long[] textCharacteristics = service.doAnalysis(text);
        printCharacteristics(textCharacteristics[0], textCharacteristics[1], textCharacteristics[2],
                                textCharacteristics[3], textCharacteristics[4]);

        int choice = showChoices();

        takeActionBasedOnChoice(choice, textCharacteristics[0], textCharacteristics[1],
                            textCharacteristics[2], textCharacteristics[3], textCharacteristics[4]);
    }
    private void printText(String text) {
        service.println("The text is:\n" +text);
    }

    private String getTextFromFile(String pathToFile) {
        return service.getTextFromFile(pathToFile);
    }
    
    private void printCharacteristics(long sentences, long words, long characters,
                                      long  numberOfSingleSyllables, long numberOfPollySyllables) {
        service.printf("\nWords: %d\n", words);
        service.printf("Sentences: %d\n", sentences);
        service.printf("Characters: %d\n", characters);
        service.printf("Syllables: %d\n", numberOfSingleSyllables);
        service.printf("Polysyllables: %d\n", numberOfPollySyllables);
    }

    private int printAnalysis(double textScore, String text) {
        return service.ageGroupAssignBasedOnScore(textScore, text);
    }

    private int showChoices() {
        String userChoice = null;
        service.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");

        try(Scanner in = new Scanner(System.in);){
            userChoice = in.next().trim();
        } catch (Exception e) {
            service.println("I/O Exception. Try again later");
        }

        switch (userChoice) {
            case "ARI" -> {return 1;}
            case "FK" -> {return 2;}
            case "SMOG" -> {return 3;}
            case "CL"-> {return 4;}
            case "all" -> {return 5;}
            default -> service.println("Wrong Choice.");
        }

        return 0;
    }

    private void takeActionBasedOnChoice(int choice, long sentences, long words, long characters, long  numberOfSingleSyllables, long numberOfPollySyllables) {
        long age = -1;
        double score = -1;
        switch (choice) {
            case 1 -> {
                score = ScoreText.computeARIScore(sentences, words, characters);
                printAnalysis(score, "Automated Readability Index:");
            }
            case 2 -> {
                score = ScoreText.computeFKScore(sentences, words, numberOfSingleSyllables);
                printAnalysis(score, "Flesch–Kincaid readability tests:");
            }
            case 3 -> {
                score = ScoreText.computeSMOGScore(sentences, numberOfPollySyllables);
                printAnalysis(score, "Simple Measure of Gobbledygook:");
            }
            case 4 -> {
                score = ScoreText.computeCLScore(sentences, words, characters);
                printAnalysis(score, "Coleman–Liau index:");
            }
            case 5 -> {
                double score1 = ScoreText.computeARIScore(sentences, words, characters);
                int age1 = printAnalysis(score1, "Automated Readability Index:");
                double score2 = ScoreText.computeFKScore(sentences, words, numberOfSingleSyllables);
                int age2 = printAnalysis(score2, "Flesch–Kincaid readability tests:");
                double score3 = ScoreText.computeSMOGScore(sentences, numberOfPollySyllables);
                int age3 = printAnalysis(score3, "Simple Measure of Gobbledygook:");
                double score4 = ScoreText.computeCLScore(sentences, words, characters);
                int age4 = printAnalysis(score4, "Coleman–Liau index:");

                age = (age1 + age2 + age3 + age4) / (long) 4;

                System.out.printf("\nThis text should be understood in average by %.2f-year-olds.", age);

            }
        }
    }
}
