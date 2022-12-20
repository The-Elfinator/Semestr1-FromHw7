import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class WsppSortedPosition {

    public static void main(String[] args) {
        try {
            MyScanner scan = new MyScanner(
                    new InputStreamReader(
                            new FileInputStream(args[0]),
                            "utf-8"
                    )
            );
            try {
                TreeMap<String, ArrayList<Integer>> arr = new TreeMap<>();
                String currentWord;
                int ind_line = 1, ind = 0;
                while (scan.hasNextWord()) {
                    currentWord = scan.nextWord();
                    if (!currentWord.isEmpty()) {
                        ind++;
                        currentWord = currentWord.toLowerCase();
                        if (!arr.containsKey(currentWord)) {
                            arr.put(currentWord, new ArrayList<>());
                        }
                        arr.get(currentWord).add(ind_line);
                        arr.get(currentWord).add(ind);
                    } else {
                        ind_line++;
                        ind = 0;
                    }
                }
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(args[1]),
                                "utf-8"
                        )
                );
                try {
                    for (String word: arr.keySet()) {
                        int n = arr.get(word).size();
                        out.write(word + " " + n/2);
                        for (int i = 0; i < n; i += 2) {
                            out.write(" " + arr.get(word).get(i) + ":" +
                                    arr.get(word).get(i + 1));
                        }
                        out.newLine();
                    }
                } finally {
                    out.close();
                }
            } catch (IOException e) {
                System.out.println("Could not read or write: " + e.getMessage());
            } finally {
                scan.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't open file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not read or write: " + e.getMessage());
        }
    }
}
