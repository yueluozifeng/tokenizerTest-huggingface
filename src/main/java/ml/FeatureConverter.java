package ml;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tokenlization.FullTokenizer;

/** Convert String to features that can be fed into BERT model. */
public final class FeatureConverter {
    private final FullTokenizer tokenizer;
    private final int maxQueryLen;
    private final int maxSeqLen;

    public FeatureConverter(
            Map<String, Integer> inputDic, boolean doLowerCase, int maxQueryLen, int maxSeqLen) {
        this.tokenizer = new FullTokenizer(inputDic, doLowerCase);
        this.maxQueryLen = maxQueryLen;
        this.maxSeqLen = maxSeqLen;
    }

    public void convert() throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("D:\\computer_learn\\java\\test2\\src\\main\\resources\\test.txt"));

        String sentence = null;
        ArrayList<List<Integer>> result = new ArrayList<>();
        while ((sentence = in.readLine()) != null) {
            List<String> queryTokens = tokenizer.tokenize(sentence);
            List<String> tokens = new ArrayList<>();
            Map<Integer, Integer> tokenToOrigMap = new HashMap<>();
            tokens.add("[CLS]");
            tokens.addAll(queryTokens);
            tokens.add("[SEP]");
            List<Integer> inputIds = tokenizer.convertTokensToIds(tokens);
            result.add(inputIds);
        }

        FileWriter fw = new FileWriter("D:\\computer_learn\\java\\test2\\src\\main\\resources\\result.txt");
        for (List<Integer> ids : result) {
            fw.write(ids.toString());
            fw.write("\n");
        }
        in.close();
        fw.close();
    }
}
