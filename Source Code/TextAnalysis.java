package readability;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalysis {
    
    private long totalNumberOfSentences;
    private long totalNumberOfWords;
    private long totalNumberOfCharacters;
    private long totalNumberOfSingleSyllables;
    private long getTotalNumberOfPolySyllables;
    
    private final TextAnalysisPrivate callTextAnalysisPrivate = new TextAnalysisPrivate();
    
    protected long[] doFileTextAnalysis(String text) {
        try {
            callTextAnalysisPrivate.doAnalysis(text);
        } catch (Exception e) {
            System.out.println("System error. Please try again");
        }

        return new long[] {totalNumberOfSentences, totalNumberOfWords, totalNumberOfCharacters, 
                totalNumberOfSingleSyllables, getTotalNumberOfPolySyllables};
    }
    
    
    private class TextAnalysisPrivate {
        private void doAnalysis(String text) {
  
            String regex = "[!.?]";
            String[] textPartitionedArray = text.split(regex);

            totalNumberOfSentences = textPartitionedArray.length;

            for(String sentence : textPartitionedArray) {
                sentence = sentence.replaceAll(",", "");

                String[] arrayOfWords = sentence.trim().split("\s");
                totalNumberOfWords = totalNumberOfWords + arrayOfWords.length;

                for(String word : arrayOfWords) {
                    totalNumberOfSingleSyllables += getNumberOfSyllables(word);
                    getTotalNumberOfPolySyllables += getNumberOfPolysyllables(word);
                }
            }

            text = text.replaceAll("\s", "");

            totalNumberOfCharacters  = text.length();
        }

        private int getNumberOfPolysyllables(String word) {
            int polysyllables = 0;

            String i = "(?i)[aiou][aeiou]*|e[aeiou]*(?!d?\\b)";
            Matcher m = Pattern.compile(i).matcher(word);
            int count = 0;

            while (m.find()) {
                count++;
            }

            if (count > 2) {
                polysyllables += 1;
            }

            return polysyllables;
        }

        private int getNumberOfSyllables(String word) {

            final Pattern p = Pattern.compile("([ayeiou]+)");
            final String lowerCase = word.toLowerCase();
            final Matcher m = p.matcher(lowerCase);

            int count = 0;
            while (m.find())
                count++;

            if (lowerCase.endsWith("e"))
                count--;

            return count < 0 ? 1 : count;

        }
    }

}
