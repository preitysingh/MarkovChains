//author: Preity Singh

import java.util.*;

public class MarkovChains {

    private Map<String, List<String>> transitions;
    private Random random;

    public MarkovChains() {
        transitions = new HashMap<>();
        random = new Random();
    }

    //this method takes a string in and creates a
    // 'mapping' between each word in the input string
    //the mapping is storied in transitions
    public void train(String input) {
        String[] words = input.split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            String current = words[i];
            String next = words[i+1];
            if (!transitions.containsKey(current)) {
                transitions.put(current, new ArrayList<>());
            }
            transitions.get(current).add(next);
        }
    }

    //this method takes the wanted output length &
    // uses the mapping to randomly generate a new string
    public String generate(int length) {
        List<String> currentWords = new ArrayList<>(transitions.keySet());
        String current = currentWords.get(random.nextInt(currentWords.size()));
        StringBuilder output = new StringBuilder(current);
        for (int i = 1; i < length; i++) {
            List<String> possibleNext = transitions.get(current);
            if (possibleNext == null || possibleNext.isEmpty()) {
                break;
            }
            String next = possibleNext.get(random.nextInt(possibleNext.size()));
            output.append(" ").append(next);
            current = next;
        }
        return output.toString();
    }

    public static void main(String[] args) {
        MarkovChains chain = new MarkovChains();
        chain.train("kyle brought mochi donuts for the class");
        String generatedText = chain.generate(10);
        System.out.println(generatedText);
    }

}
