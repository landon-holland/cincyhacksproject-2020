import java.util.ArrayList;

public class TextAnalyzer {
    private ArrayList<Word> words;

    public TextAnalyzer(){
        this("");
    }

    public TextAnalyzer(String text){
        this.words = new ArrayList<>();

        String[] words = text.split(" ");
        for (String word : words){
            this.words.add(new Word(word));
        }
    }

    public int countSyllables(){
        int syllables = 0;
        for (Word word : words){
            syllables += word.countSyllables();
        }
        return syllables;
    }

    public void pronounce(){
        for (Word word : words){
            word.pronounce();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
