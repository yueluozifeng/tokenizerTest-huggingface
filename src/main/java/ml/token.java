package ml;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/** Interface to load TfLite model and provide predictions. */
public class token {
    private static final String TAG = "BertDemo";
    private static final String DIC_PATH = "D:\\computer_learn\\java\\test2\\src\\main\\resources\\vocab.txt";
    private static final int MAX_QUERY_LEN = 64;
    private static final int MAX_SEQ_LEN = 384;
    private static final boolean DO_LOWER_CASE = true;

    private final Map<String, Integer> dic = new HashMap<>();
    private final FeatureConverter featureConverter;


    public token() throws IOException {
        this.featureConverter = new FeatureConverter(dic, DO_LOWER_CASE, MAX_QUERY_LEN, MAX_SEQ_LEN);
        loadDictionary();
        predict();
    }

    public synchronized void loadDictionary() {
        try {
            loadDictionaryFile();
            System.out.println(dic.size());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    /** Load dictionary from assets. */
    public void loadDictionaryFile() throws IOException {
        try (FileInputStream  ins = new FileInputStream(DIC_PATH);
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins))) {
                int index = 0;
                while (reader.ready()) {
                    String key = reader.readLine();
                    dic.put(key, index++);
            }
        }
    }

    public void predict() throws IOException {
        featureConverter.convert();
    }
}
