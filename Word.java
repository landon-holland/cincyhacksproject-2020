import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Word {
    private String word;

    private static final String[] diphthongs = {
            "eau","ague","uai","tion","sion",
            "au","ai","ay","ea","ee","ei","ey","ie","oa","oe","oi","oo","ou","oy","ui"};


    private static List<String> dict;
    private static String[][] dictionaryPronunciation = new String[134298][];

    static {
        try {
            BufferedReader br = new BufferedReader(new FileReader("cmudict.txt"));
            String[] dictionary = new String[134298];
            int big = 0;

            String[] tempArr;
            int i = 0;
            while (br.ready()) {
                tempArr = br.readLine().split("  ");
                dictionary[i] = tempArr[0];
                dictionaryPronunciation[i] = tempArr[1].split(" ");

                if (dictionaryPronunciation[i].length > big)
                    big = dictionaryPronunciation[i].length;
                i++;
            }
            dict = Arrays.asList(dictionary);
            System.out.println(big);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public Word(){
        this("");
    }

    public Word(String word){
        this.word = word;
        this.word = this.word.trim();
        this.word = this.word.toLowerCase();
        String tempWord = "";
        for (int i = 0; i < this.word.length(); i++){
            if (this.word.charAt(i) >= 'a' && this.word.charAt(i) <= 'z') {
                tempWord += this.word.charAt(i);
            }
        }
        this.word = tempWord;
    }

    public int countSyllables(){
        int index = dict.indexOf(word.toUpperCase());
        int numSyllables = 0;

        if (index >= 0){
            for (String phoneme : dictionaryPronunciation[index]){
                if (phoneme.contains("A") || phoneme.contains("E") || phoneme.contains("I")
                        || phoneme.contains("O") || phoneme.contains("U"))
                    numSyllables++;
            }

            System.out.println(word + " " + numSyllables);
        }
        else {

            numSyllables = countVowels() - countDiphthongs();

            if (word.charAt(word.length() - 1) == 'e')
                numSyllables--;

            System.out.println(word + " " + countVowels() + " " + countDiphthongs() + " " + numSyllables);
        }



        return numSyllables;
    }

    public int countVowels(){
        int numVowels = 0;

        for (int i = 0; i < word.length(); i++){
            switch (word.charAt(i)){
                case 'a':
                case 'i':
                case 'o':
                case 'u':
                case 'y':
                case 'e':
                    numVowels++;
                    break;
            }
        }
        return numVowels;
    }

    public int countDiphthongs(){
        int numDiphthong = 0;
        String tempWord = word;

        for (String diphthong : diphthongs){
            while (tempWord.contains(diphthong)){
                tempWord = tempWord.replace(diphthong, "");

                numDiphthong += new Word(diphthong).countVowels() - 1;

                System.out.println(diphthong);
            }
        }

        return numDiphthong;
    }

    public void pronounce(){
        try {
            int index = dict.indexOf(word.toUpperCase());

            if (index == -1)
                return;

            for (String phoneme : dictionaryPronunciation[index]){
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("Phonemes\\" + phoneme + ".wav"));

                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();

                AudioFormat format = audioIn.getFormat();
                long frames = audioIn.getFrameLength();
                double durationInSeconds = (frames+0.0) / format.getFrameRate();
                Thread.sleep((long) (durationInSeconds * 1000));
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
