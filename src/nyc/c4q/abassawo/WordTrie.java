package nyc.c4q.abassawo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c4q-Abass on 12/5/15.
 */
public class WordTrie {

    Node head;

    public WordTrie(){
        this.head = new Node(0); //0 representing empty letter. " "
    }

    public void add(String word) {
        if(this.contains(word))return;
        Node pointer = head;

            for (int i = 0; i < word.length(); i++) {
                int idxPointer = getIndex(word.charAt(i));
                if(pointer.hasChild(idxPointer)){
                    pointer = pointer.childNodes[idxPointer];
                } else {
                    Node node = new Node(getIndex(word.charAt(i)));
                    if(i == word.length() - 1) {
                        node.setWordTrue();
                    } else{
                        node.setPrefixTrue();
                    }
                    pointer.addChild(node);
                    pointer = node;
                }
            }

    }

    public int getIndex(char c){
        return c - 65;
    }


    public boolean contains(String word) {
        Node pointer =  head;

        for (int i = 0; i < word.length(); i++) {
            int idxPointer = getIndex(word.charAt(i));

            if(pointer.hasChild(idxPointer)){
                pointer = pointer.childNodes[idxPointer];
            } else {
                return false;
            }
        }
        return pointer.isWord;
    }

    public List<String> toList() {
        //in-depth traversal from left to right
        List<String> words = new ArrayList<String>();
        Node pointer = head;
        while(pointer.hasNext()){

        }
        return words;
    }


    private class Node {

        private int data; //field goes from 0 to 26.

        private Node[] childNodes;
        private boolean isWord;
        private boolean isPrefix;

        public void setWordTrue(){
            this.isWord = true;
        }
        public void setPrefixTrue(){
            this.isPrefix = true;
        }

        public boolean hasNext(){
            for(Node i : childNodes){
                return i != null;
            }
            return false;
        }
        
        public boolean hasChild(int index){
            return this.childNodes[index] != null;
        }

        public void addChild(Node node){
            this.childNodes[node.data] = node;
        }




        public Node(int data){
            this.data = data;
            this.childNodes = new Node[26];
        }
    }
}
