package ml;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interface to load squad dataset. Provide passages for users to choose from & provide questions
 * for autoCompleteTextView.
 */
public class LoadDatasetClient {
    private static final String TAG = "BertAppDemo";
    private static final String DIC_DIR = "vocab.txt";

    private String[] contents;
    private String[] titles;
    private String[][] questions;

    public LoadDatasetClient() {

    }

    private static String[] listToArray(List<List<String>> list) {
        String[] answer = new String[list.size()];
        int index = 0;
        for (List<String> item : list) {
            answer[index++] = item.get(0);
        }
        return answer;
    }


    public Map<String, Integer> loadDictionary() {
        Map<String, Integer> dic = new HashMap<>();
        try (FileInputStream ins = new FileInputStream(DIC_DIR);
             BufferedReader reader = new BufferedReader(new InputStreamReader(ins))) {
            int index = 0;
            while (reader.ready()) {
                String key = reader.readLine();
                dic.put(key, index++);
            }
        } catch (IOException ex) {
//            Log.e(TAG, ex.getMessage());
        }
        return dic;
    }
}
