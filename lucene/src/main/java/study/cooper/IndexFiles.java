package study.cooper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.apache.lucene.document.Field.Store.*;

public class IndexFiles {

    private static final Path INDEXPATH= Paths.get("F:\\workspaces\\lucene-index");

    public static void createIndex(Path docPath) throws IOException {
        if (!Files.isReadable(docPath)){
            throw new RuntimeException("Document directory '" + docPath.toAbsolutePath() + "' does not exist or is not readable, please check the path");
        }

        //创建保存Index的Directory
        Directory directory = FSDirectory.open(INDEXPATH);
        //创建Analyszer
        Analyzer analyzer = new CJKAnalyzer();
        //创建IndexWriterConfig
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        //创建IndexWriter
        IndexWriter iw = new IndexWriter(directory,iwc);
        //创建Index
        indexDoc(iw,docPath);
        //关闭IndexWriter
        iw.close();
    }

    private static void indexDoc(IndexWriter indexWriter, Path docPath) throws IOException {
        System.out.println("创建索引："+docPath.getFileName());
        Document doc = new Document();
        String title = docPath.getFileName().toString();
        if (!title.endsWith(".json")){
            return;
        }
        doc.add(new TextField("title", title, YES));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(docPath.toFile())));
        String content = reader.readLine();

        JSONObject object = (JSONObject) JSONObject.parse(content);
        StringBuilder material = new StringBuilder();
        JSONArray materials = object.getJSONArray("materials");
        for (int i = 0; i < materials.size(); i++) {
            JSONObject o = (JSONObject) materials.get(i);
            material.append(o.get("materialName")+"、");
        }
        doc.add(new TextField("id", object.getString("id"), YES));
        doc.add(new TextField("catId", object.getString("catId"), YES));
        doc.add(new TextField("chefId", object.getString("chefId"), YES));
        doc.add(new TextField("description", object.getString("description"), YES));
        doc.add(new TextField("materials", material.toString(), YES));
        switch (indexWriter.getConfig().getOpenMode()){
            case CREATE:
                indexWriter.addDocument(doc);
                break;
            case APPEND:
            case CREATE_OR_APPEND:
                indexWriter.updateDocument(new Term("materials", material.toString()), doc);
                break;
        }
        indexWriter.commit();
    }

    public static void main(String[] args) {
        File file = new File("D:\\meishi");
        File[] files = file.listFiles();
        for(File f : files){
            Path p = Paths.get(f.getAbsolutePath());
            try {
                createIndex(p);
            } catch (IOException e) {}
        }
    }
}