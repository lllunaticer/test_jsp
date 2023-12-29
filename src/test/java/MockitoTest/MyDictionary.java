package MockitoTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xingjian LONG
 * Created on 2021-08-27
 */
public class MyDictionary {
    Map<String, String> wordMap;

    public MyDictionary() {
        wordMap = new HashMap<String, String>();
    }
    public void add(final String word, final String meaning) {
        wordMap.put(word, meaning);
    }
    public String getMeaning(final String word) {
        return wordMap.get(word);
    }
}