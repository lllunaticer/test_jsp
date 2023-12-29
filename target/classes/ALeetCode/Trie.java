package ALeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-19
 */
public class Trie {
    private final PrefixTree root;

    public Trie() {
        this.root = new PrefixTree('0');
    }

    public void insert(String word) {

        PrefixTree idx = root;
        for (char c : word.toCharArray()) {
            if (!idx.sons.containsKey(c)) {
                idx.sons.put(c, new PrefixTree(c));
            }
            idx = idx.sons.get(c);
        }
        idx.setEnd();
    }

    public boolean search(String word) {
        PrefixTree idx = root;
        for (char c : word.toCharArray()) {
            if (!idx.sons.containsKey(c)) {
                return false;
            }
            idx = idx.sons.get(c);
        }
        return idx.isEnd();
    }

    public boolean startsWith(String prefix) {
        PrefixTree idx = root;
        for (char c : prefix.toCharArray()) {
            if (!idx.sons.containsKey(c)) {
                return false;
            }
            idx = idx.sons.get(c);
        }
        return true;
    }

    class PrefixTree {
        private boolean end;
        private final char value;
        private final Map<Character, PrefixTree> sons;

        public PrefixTree(char c) {
            this.value = c;
            sons = new HashMap<>();
        }

        public char getValue() {
            return value;
        }

        public Map<Character, PrefixTree> getSons() {
            return sons;
        }

        public void setEnd() {
            end = true;
        }

        public boolean isEnd() {
            return end;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        trie.search("app");
        System.out.println(trie.startsWith("ap"));
    }
}
