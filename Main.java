import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String INCREMENT_WORD = "copy";
    private static final Long INITIAL_INCREMENT = 1L;

    private static final List<String> randomNamesListWithRepeatedValues = Arrays.asList(
            "flesh", "handy", "slap", "flesh", "chop", "scenario",
            "slap", "spokesperson", "language", "heroin", "flesh",
            "flesh", "slap", "handy"
    );

    private static String incrementOne(String value) {
        try {
            return String.valueOf(Long.parseLong(value) + 1);
        }
        catch (Exception e) {
            System.out.println(e);
            return String.valueOf(INITIAL_INCREMENT);
        }
    }

    private static String addIncrementAmountInWord(String word) {
        List<String> splitedWord = Arrays.asList(word.split(" "));
        splitedWord.set(splitedWord.size() - 1, incrementOne(splitedWord.get(splitedWord.size() - 1)));
        return String.join(" ", splitedWord);
    }

    private static boolean hasIncrementWordInItsName(String name, String incrementWord) {
        List<String> splitedName = Arrays.asList(name.split(" "));
        return splitedName.get(splitedName.size() - 2).equals(incrementWord);
    }

    private static String addIncrementAndIncrementWord(String word, String incrementWord, Long value) {
        return word + " - " + incrementWord + " " + value;
    }

    private static boolean hasMoreThenOneWord(String name) {
        return Arrays.asList(name.split(" ")).size() > 1;
    }

    private static String calculateNonRepeatedName(List<String> nameList, String newName) {
        if (!hasMoreThenOneWord(newName) || !hasIncrementWordInItsName(newName, INCREMENT_WORD)) {
            return getNewNameToFitList(nameList, addIncrementAndIncrementWord(newName, INCREMENT_WORD, INITIAL_INCREMENT));
        }
        if (hasIncrementWordInItsName(newName, INCREMENT_WORD)) {
            return getNewNameToFitList(nameList, addIncrementAmountInWord(newName));
        }
        return null;
    }

    public static String getNewNameToFitList(List<String> nameList, String newName) {
        if (nameList.contains(newName)) return calculateNonRepeatedName(nameList, newName);
        return newName;
    }

    public static void main(String[] args) {

        // Initializing the List
        List<String> nameList = new ArrayList<>();

        // Filling it with the processed values of Main.randomNamesListWithRepeatedValues
        // Notice that "flesh", "slap" and "handy" are repeated
        randomNamesListWithRepeatedValues.forEach(name ->
                nameList.add(getNewNameToFitList(nameList, name))
        );

        // Showing the result
        nameList.forEach(System.out::println);
    }
}
