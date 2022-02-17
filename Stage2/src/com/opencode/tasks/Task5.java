package tasks;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Task5 implements Runnable {
    @Override
    public void run() {
        Sentence sentence = Sentence.from(TaskUtil.getUserInput("Введите набор слов"));

        System.out.println("Модель создана: " + sentence);
        System.out.println("original: " + sentence.getOriginalSentence());
        System.out.println("1fs word: " + sentence.getWordByPosition(0).getOriginalWord());
        System.out.println("last word: " + sentence.getWordByPosition(sentence.getWordsCount() - 1).getOriginalWord());
    }

    public static class Sentence {
        private Word[] words;

        public static Sentence from(String input) {
            return new Sentence(
                    Arrays
                        .stream(input.split("\\s"))
                        .filter((word) -> !" ".equals(word))
                        .toArray(String[]::new)
                );
        }

        public Sentence(String[] wordsRaw) {
            this.words = Arrays
                    .stream(wordsRaw)
                    .map((word) -> new Word(word))
                    .toArray(Word[]::new);
        }

        public int getWordsCount() {
            return words.length;
        }

        public String getOriginalSentence() {
            return Arrays
                    .stream(words)
                    .map((word) -> word.getOriginalWord())
                    .collect(Collectors.joining(" "));
        }

        public Word getWordByPosition(int pos) {
            return words[pos];
        }

        public String toString() {
            return getOriginalSentence();
        }

        public class Word {
            private char[] values;

            Word(String value) {
                this.values = value.toCharArray();
            }

            public char[] getValues() {
                return values;
            }

            public int getSymbolCount() {
                return values.length;
            }

            public String getOriginalWord() {
                return new String(values);
            }
        }
    }
}
