package study.cooper;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by wangliang on 2016/8/19.
 */
public class CommonOperation {

    public static void indexDocs(String docPath,String indexPath) throws IOException{

        Path docDir = Paths.get(docPath, new String[0]);
        if (!Files.isReadable(docDir)) {
            System.out.println("Document directory '" + docDir.toAbsolutePath() + "' does not exist or is not readable, please check the path");
            return;
        }
        if (!Files.isDirectory(docDir)){
            System.out.println("Document directory '" + docDir.toAbsolutePath() + "' does not exist or is not directory, please check the path");
            return;
        }

        //创建保存Index的Directory
        Directory dir = FSDirectory.open(Paths.get(docPath, new String[0]));
        //创建分析器 Analyzer
        Analyzer analyzer = new StandardAnalyzer();
        //创建indexWriterConfig
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        //设置config的模式 ： CREATE, APPEND, CREATE_OR_APPEND
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        //创建IndexWriter
        IndexWriter writer = new IndexWriter(dir, iwc);
        Files.walkFileTree(docDir, new SimpleFileVisitor() {
            public IndexWriter val$writer;

            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                try {
                    CommonOperation.indexDoc(this.val$writer, file);
                } catch (IOException localIOException) {
                    System.out.println("Visit File error!!");
                }
                return FileVisitResult.CONTINUE;
            }});
        writer.close();
    }

    public static void indexDoc(String docPath,String indexPath) throws IOException{
        Path docDir = Paths.get(docPath);
        if (!Files.isReadable(docDir)) {
            System.out.println("Document directory '" + docDir.toAbsolutePath() + "' does not exist or is not readable, please check the path");
            return;
        }

        Path indexDir = Paths.get(indexPath);
        Directory directory = FSDirectory.open(indexDir);
        Analyzer analyzer = new SimpleAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        indexDoc(indexWriter,docDir);
        indexWriter.close();

    }

    public static void indexDoc(IndexWriter writer, Path file) throws IOException{
        Document doc = new Document();
//        Field pathField = new StringField("path", file.toString(), Field.Store.YES);
//        doc.add(pathField);
//        doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));
        doc.add(new Field("field","This is the text to be indexed.", TextField.TYPE_STORED));

        switch (writer.getConfig().getOpenMode()){
            case CREATE:
                System.out.println("adding " + file);
                writer.addDocument(doc);
                break;
            case APPEND:
            case CREATE_OR_APPEND:
                System.out.println("updating " + file);
                writer.updateDocument(new Term("path", file.toString()), doc);
                break;
        }
    }

    public static void main(String[] args) {
        try {
//            indexDoc("D:/meishi/douguo/jiachangcai_index","D:/meishi/douguo/jiachangcai_index");
            queryString("D:/meishi/douguo/jiachangcai_index");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String queryString(String dirPath) throws IOException, ParseException{
        Path path = Paths.get(dirPath);
        Directory directory = FSDirectory.open(path);
        // Now search the index:
        Analyzer analyzer = new SimpleAnalyzer();
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("field", analyzer);
        Query query = parser.parse("text");
        ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
        // Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            System.out.println(hitDoc.get("field"));
        }
        ireader.close();
        directory.close();
        return null;
    }
}
