package study.cooper;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangliang on 2016/8/30.
 */
public class QueryFiles {

    private static final Path INDEXPATH= Paths.get("F:\\workspaces\\lucene-index");

    public static List<String> queryDoc(String material) throws IOException, ParseException {
        Directory directory = FSDirectory.open(INDEXPATH);
        // Now search the index:
        Analyzer analyzer = new CJKAnalyzer();
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("materials", analyzer);
        Query query = parser.parse(material);

        ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
        List<String> result = new ArrayList<String>();
        // Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            result.add(hitDoc.get("id"));
        }
        ireader.close();
        directory.close();

        return result;
    }

    public static void main(String[] args) {
        try {
            System.out.println(queryDoc("白菜"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
