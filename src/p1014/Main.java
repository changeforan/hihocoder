package p1014;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Trie {
    private Map<Character, Trie> edges;
    private int count;
    public boolean isWord;
    public Trie(){
        edges = new HashMap<>();
        count = 0;
        isWord = false;
    }

    public int getCount() {
        return count;
    }

    public void insert(String word) {
        insert(word.toCharArray(), 0);

    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null) return false;
        Trie next = edges.get(word.charAt(0));
        if (next == null) return false;
        if (word.length() == 1 ) return next.isWord;
        return next.search(word.substring(1));

    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        int count = prefixCount(prefix);
        return count > 0;

    }






    public void insert(char[] characters, int start) {

        count++;
        if (start == characters.length) {
            isWord = true;
            return;

        }


        Trie next = edges.get(characters[start]);
        if (next == null) {
            next = new Trie();
            edges.put(characters[start], next);
        }

        next.insert(characters, start + 1);

    }

    public int prefixCount(String word) {
//        Trie next = edges.get(characters[start]);
//        if (start == characters.length - 1) {
//            return next == null ? 0 : next.getCount();
//        }else {
//            return next == null ? 0 : next.search(characters, start + 1);
//        }

        char[] chs = word.toCharArray();
        Trie pre = this;
        Trie node = null;
        for (char c : chs) {
            node = pre.edges.get(c);
            if (node == null) {
                return 0;
            }
            pre = node;
        }

        return node.count;
    }

}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Trie trie = new Trie();
        while (n-- > 0) {
            String s = sc.next();
            trie.insert( s.toCharArray(), 0);
        }
        int m = sc.nextInt();
        while (m-- > 0 ) {
            String s = sc.next();
            System.out.println(trie.prefixCount(s));
        }
    }
}
