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

                wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

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
        for (WordInfo w : wordInfos) {
            String wordInfo = w.getValue() + " " +w.getWordCount();
            joiner.add(wordInfo);
        }
        return joiner.toString();
    }



    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            }
            else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
