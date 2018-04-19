package main;

public class Word {
    private String word;
    private int count;

    public Word(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public void addCount() {
        count++;
    }
    
    public int compareTo(Word wordB) {
        String word=wordB.getWord();
        return this.word.compareTo(word);
    }

    @Override
    public String toString() {
        return "[ word=" + word + ", times=" + count + "]";
    }
    
    
           
}
