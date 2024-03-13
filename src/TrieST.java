//-----------------------------------------------------
// Title: TrieST class
// Author: Ceyda Kuşçuoğlu

// Description: This class implements the R-Way Trie Structure
//-----------------------------------------------------
public class TrieST<Value> {
    private static final int R = 256;
    private Node root = new Node();

    private static class Node {
        private Object val;
        private Node[] next = new Node[R]; // initializing the next array for nodes in trie
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0); // putting firstly root node then putting nodes in tree
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) // if there is no root , it will create root
            x = new Node();
        if (d == key.length()) {// when it inserts all letters,it will put value
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1); // it will put letters to trie
        return x;
    }

    public boolean contains(String key) {// checkes whether trie contains given string or not
        return get(key) != null;
    }

    public Value get(String key) { // if trie contains key,this will return
        Node x = get(root, key, 0);
        if (x == null)
            return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) // firstly it controls root,if there is no root it returns null
            return null;
        if (d == key.length()) // when d -depth- equals to length of key,it will return the last node
            return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1); // recursively goes deeper until the depth = length of key
    }

    public void Search(String args) { // if trie contains the given string it prints true,if not it returns false
        if (contains(args)) { // checkes whether given string is in trie or not
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    public Iterable<String> keys() { // returns all keys in trie with help of collect method
        Queue<String> queue = new Queue<String>(); // this queue keeps the keys in trie
        collect(root, "", queue);
        return queue;
    }

    private void collect(Node x, String prefix, Queue<String> q) {
        if (x == null)
            return; // it starts with no if it is not exist then it returns null
        if (x.val != null)
            q.enqueue(prefix); // if node has value it inserts it to queue
        for (char c = 0; c < R; c++)
            collect(x.next[c], prefix + c, q); // collects the nodes till the value
    }

    // public Iterable<String> keysWithPrefix(String prefix) { // it keeps the
    // string that starts with given prefix
    // Queue<String> queue = new Queue<String>(); // the list of keys
    // Node x = get(root, prefix, 0);
    // collect(x, prefix, queue);
    // return queue; // returns the iterable queue of strings
    // }

    public void countPrefix() { // this method runs the keysWithPrefix method for all keys in the tree and
        // prints the size of the queue but it substrack 1 because list contains the
        // actual word

        for (String iterable_element : keys()) {
            Queue<String> queue = new Queue<String>(); // the list of keys
            Node x = get(root, iterable_element, 0);
            collect(x, iterable_element, queue);
            System.out.println(iterable_element + ": " + (queue.size() - 1));
        }
    }

    // public Iterable<String> keysThatMatch(String suffix) { // this method holds
    // the list of string that contains the
    // // given suffix
    // Queue<String> queue = new Queue<String>();

    // for (String str : keys()) {
    // if (str.length() >= suffix.length()) {
    // String s = str.substring(str.length() - suffix.length(), str.length());

    // if (s.equalsIgnoreCase(suffix)) {
    // queue.enqueue(str);
    // }
    // }
    // }
    // return queue;

    // }

    public void reverseFind(String suffix) { // this method prints the words that ends with given suffix

        Queue<String> queue = new Queue<String>();

        for (String str : keys()) {
            if (str.length() >= suffix.length()) {
                String s = str.substring(str.length() - suffix.length(), str.length());

                if (s.equalsIgnoreCase(suffix)) {// if end of the word equals the given suffix,it adds the queueu
                    queue.enqueue(str);
                }
            }
        }

        for (String iterable_element : queue) {
            System.out.println(iterable_element);
        }
    }

    public void ShortestUniquePrefix() { // this method returns the shortest unique prefixies of the keys in the trie

        if (root == null) { // if there is no root,it returns null
            return;
        }
        int count = 0;
        for (String word : keys()) {
            String prefix = "";
            for (int i = 0; i < word.length(); i++) {
                prefix += word.charAt(i);
                count = 0;
                for (String otherWord : keys()) {
                    if (otherWord.startsWith(prefix)) { // if there is another word with that prefix it increases the
                                                        // count
                        count++;
                    }
                }
                if (count == 1) {// if there is no another number with that prefix count stay as 1 and prints the
                                 // prefix
                    System.out.println(word + ": " + prefix);
                    break;
                }
            }
            if (prefix.length() == word.length() && count != 1) {// if count is not 1 and prefix length is equal to
                                                                 // other word's length than it means the word has no
                                                                 // prefix
                System.out.println(word + ": not exists");
            }
        }
    }

    public void LongestCommonPrefix() { // this method returns if there exists common prefix of word ,it returns the
                                        // longest one
        if (root == null) { // if there is no root,it returns null
            return;
        }
        String firstWord = (String) ((Queue) keys()).dequeue(); // starts with first word in queue
        int prefixLength = 0;
        for (int i = 0; i < firstWord.length(); i++) {
            char currentChar = firstWord.charAt(i);
            for (String word : keys()) {
                if (word.length() <= i || word.charAt(i) != currentChar) {
                    if (prefixLength == 0) {// if prefix length stay 0 it means there is no common prefix
                        System.out.println("not exists");
                    } else {
                        System.out.println(firstWord.substring(0, prefixLength));
                    }
                    return;
                }
            }
            prefixLength++; // if letters match, it increases the length of prefix
        }
        System.out.println(firstWord.substring(0, prefixLength)); // if there exists common prefix ,prints it
    }

}
