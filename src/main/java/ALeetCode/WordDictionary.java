package ALeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-20
 */
class WordDictionary {
    private Trie root;

    public WordDictionary() {
        root = new Trie('0');
    }

    public void addWord(String word) {
        Trie idx = root;
        for (char c : word.toCharArray()) {
            if (!idx.sons.containsKey(c)) {
                idx.sons.put(c, new Trie(c));
            }
            idx = idx.sons.get(c);
        }
        idx.setEnd();
    }

    public boolean search(String word) {
        return searchDfs(word, root);
    }

    private boolean searchDfs(String word, Trie node) {
        char c = word.charAt(0);
        Map<Character, Trie> sons = node.getSons();
        if (c == '.') {
            if (word.length() == 1) {
                for (Trie trie : sons.values()) {
                    if (trie.isEnd()) {
                        return true;
                    }
                }
                return false;
            }
            boolean result = false;
            for (Trie trie : sons.values()) {
                result = result || searchDfs(word.substring(1), trie);
            }
            return result;
        } else {
            if (!sons.containsKey(c)) {
                return false;
            } else {
                if (word.length() == 1) {
                    return sons.containsKey(c) && sons.get(c).isEnd();
                } else {
                    return searchDfs(word.substring(1), sons.get(c));
                }
            }
        }
    }

    class Trie {
        private char value;
        private boolean end;
        private HashMap<Character, Trie> sons;

        public Trie(char value) {
            this.value = value;
            sons = new HashMap<>();
        }

        public char getValue() {
            return value;
        }

        public Map<Character, Trie> getSons() {
            return sons;
        }

        public void setEnd() {
            this.end = true;
        }

        public boolean isEnd() {
            return end;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */