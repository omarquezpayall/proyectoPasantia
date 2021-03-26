package com.payall.dataaccess.Dao;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.payall.exceptions.CustomException;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DaoMongo {
    private static final Logger LOGGER= LoggerFactory.getLogger(DaoMongo.class);

    private String username;
    private String hostname;
    private String port;
    private String database;
    private String password;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public DaoMongo(String username, String password, String hostname, String port, String database){
        this.username = username;
        this.port = port;
        this.database = database;
        this.password = password;
        this.hostname = hostname;
    }

    public void createConnection(){
        try {
            // For Docker mongodb://root:root@mongodb:27017
            this.mongoClient = MongoClients.create(
                    "mongodb://" + username + ":" + password + "@" + hostname + ":" + port + "/" + database + "?authSource=admin"
            );
            this.mongoDatabase = mongoClient.getDatabase( database);
        }catch (MongoException e){
            throw new CustomException( e.toString());
        }
    }

    public void closeConnection(){
        try {
            this.mongoClient.close();
            this.mongoDatabase = null;
        }catch (Exception e){
            throw new CustomException( e.toString());
        }
    }

    public void insertDocumentsInCollection(String collectionName, List<Document> documents){
        try {
            try {
                createConnection();
                mongoDatabase.createCollection(collectionName);

            } catch (MongoCommandException e) {
                mongoDatabase.getCollection(collectionName).drop();
            }
            MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
            collection.insertMany(documents);
            closeConnection();
        }catch (Exception e){
            throw new CustomException( e.toString());
        }
    }

    public MongoClient getMongoClient(){
        return this.mongoClient;
    }

}
