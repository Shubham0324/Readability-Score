package readability;


public class Main {
    public static void main(String [] args) {

        try{
            if (args.length == 0) {
                throw new IllegalArgumentException();
            }
            else {
                App readabilityScoreApp = new App();
                readabilityScoreApp.executeApplication(args[0]);
            }
        } catch (IllegalArgumentException exception) {
            System.out.println("Path to file not present or may be wrong. Debug using stack tree");
            exception.printStackTrace();
        }
    }
}
