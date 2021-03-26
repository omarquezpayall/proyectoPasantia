package com.payall.dataaccess;

import com.payall.dataaccess.Dao.DaoMongo;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DaoMongoTests {

    @Test
    public void MongoConnectionTest() {
        DaoMongo daoMongo = new DaoMongo("oscar","mongo","localhost", "27017", "prueba");
        daoMongo.createConnection();
        assertNotNull(daoMongo.getMongoClient());
    }

    @Test
    public void insertDocumentsInCollectionTest(){
        Document doc = new Document();
        doc.append("firstName", "Yeferson");
        doc.append("lastName", "Soteldo");
        Document doc2 = new Document();
        doc2.append("firstName", "Salomon");
        doc2.append("lastName", "Rondon");
        List<Document> documents = new ArrayList<Document>();
        documents.add( doc);
        documents.add( doc2);
        DaoMongo daoMongo = new DaoMongo("oscar","mongo","localhost", "27017", "prueba");
        daoMongo.insertDocumentsInCollection("test", documents);

    }
}
