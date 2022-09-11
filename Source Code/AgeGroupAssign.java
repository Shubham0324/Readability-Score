package readability;

public class AgeGroupAssign {

    private static final PrintService printService = new PrintService();

    protected int ageGroupAssignBasedOnScore(long textScore, String text) {
        return AgeGroupAssignPrivate.ageGroupAssignBasedOnScore(textScore, text);
    }


    private class AgeGroupAssignPrivate {
        private static int ageGroupAssignBasedOnScore(double textScore, String text) {

            String ageGroup = "4";
            textScore = (long) Math.ceil(textScore);

            switch ((int)textScore) {
                case 0 -> {
                    ageGroup = "5";
                }
                case 1 -> {
                    ageGroup = "6";
                }
                case 2 -> {
                    ageGroup = "7";
                }
                case 3 -> {
                    ageGroup = "8";
                }
                case 4 -> {
                    ageGroup = "9";
                }
                case 5 -> {
                    ageGroup = "10";
                }
                case 6 -> {
                    ageGroup = "11";
                }
                case 7 -> {
                    ageGroup = "12";
                }
                case 8 -> {
                    ageGroup = "13";
                }
                case 9 -> {
                    ageGroup = "14";
                }
                case 10 -> {
                    ageGroup = "15";
                }
                case 11 -> {
                    ageGroup = "16";
                }
                case 12 -> {
                    ageGroup = "17";
                }
                case 13 -> {
                    ageGroup = "18";
                }
                case 14 -> {
                    ageGroup = "22";
                }
                default -> System.out.println("Error. Scoring mechanism is wrong");
            }
            printService.printf("\n%s %.2f (about %s-year-olds) .", text, textScore, ageGroup);
            return Integer.parseInt(ageGroup);
        }
    }
}
