import java.util.*;

public class WordFrequencyGame {

    private static final String BLANK_SPACE = "\\s+";
    private static final String NEW_LINE = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {


        if (sentence.split(BLANK_SPACE).length==1) {
            return sentence + " 1";
        } else {

            try {


                List<WordInfo> wordInfos = calculateWordFrequency(sentence);

                wordInfos.sort((wordInfo1, wordInfo2) -> wordInfo2.getWordCount() - wordInfo1.getWordCount());

                return getWordFrequencyResult(wordInfos);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(BLANK_SPACE));
        List<WordInfo> wordInfos = new ArrayList<>();
        for (String uniqueWord: new HashSet<>(words)) {
            wordInfos.add(new WordInfo(uniqueWord, Collections.frequency(words, uniqueWord)));
        }
        return wordInfos;
    }

    private String getWordFrequencyResult(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner(NEW_LINE);
        for (WordInfo wordInfo: wordInfos) {
            String wordFrequencyResult = wordInfo.getValue() + " " +wordInfo.getWordCount();
            joiner.add(wordFrequencyResult);
        }
        return joiner.toString();
    }




}
