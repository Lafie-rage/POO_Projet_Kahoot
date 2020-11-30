package utils.json;

import java.util.ArrayList;
import java.util.List;

public class URLOfQuizz {
    private static final String URL_QUIZZ1 = "https://www.kiwime.com/oqdb/files/3228365465/OpenQuizzDB_228/openquizzdb_228.json";
    private static final String URL_QUIZZ2 = "https://www.kiwime.com/oqdb/files/1093442352/OpenQuizzDB_093/openquizzdb_93.json";
    private static final String URL_QUIZZ3 = "https://www.kiwime.com/oqdb/files/1040856382/OpenQuizzDB_040/openquizzdb_40.json";
    private static final String URL_QUIZZ4 = "https://www.kiwime.com/oqdb/files/1002468684/OpenQuizzDB_002/openquizzdb_2.json";

    public static List<String> retrievetURLs() {
        List<String> list = new ArrayList<>();
        list.add(URL_QUIZZ1);
        list.add(URL_QUIZZ2);
        list.add(URL_QUIZZ3);
        list.add(URL_QUIZZ4);
        return list;
    }
}
