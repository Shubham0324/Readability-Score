package readability;

public class ScoreText {

    private static final ComputationalClass computationalAccessClass = new ComputationalClass();

    /* Protected methods to interact with outside worlds*/
    protected static double computeARIScore(long sentences, long words, long characters) {
        return computationalAccessClass.getARIScore(sentences, words, characters);
    }

    protected static double computeFKScore(long sentences, long words, long syllables) {
        return computationalAccessClass.getFKScore(sentences, words, syllables);
    }

    protected static double computeSMOGScore(long sentences, long pollySyllables) {
        return computationalAccessClass.getSMOGScore(sentences, pollySyllables);
    }

    protected static double computeCLScore(long sentences, long words, long characters) {
        return computationalAccessClass.getCLScore(sentences, words, characters);
    }


    private static class ComputationalClass {

        /* Private methods for computation of different types of readability score*/
        private static double getARIScore(long sentences, long words, long characters) {
            return (4.71 * (double) characters / words) + (0.5 * (double) words / sentences) - 21.43;
        }

        private static double getFKScore(long sentences, long words, long syllables) {
            return (0.39 * (double) words / sentences) + (11.8 * (double) syllables / words) - 15.59;
        }

        private static double getSMOGScore(long sentences, long pollySyllables) {
            return 1.043 * Math.sqrt(pollySyllables * ((double) 30 / sentences)) + 3.1291;
        }

        private static double getCLScore(long sentences, long words, long characters) {
            double L = ((double) characters / words) * 100;
            double S =  ((double) sentences / words) * 100;

            return (0.0588 * L) - (0.296 * S) - 15.8;
        }
    }

}
