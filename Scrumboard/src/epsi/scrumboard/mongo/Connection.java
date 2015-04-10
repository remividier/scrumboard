package epsi.scrumboard.mongo;

import com.mongodb.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Pierre on 10/04/2015.
 */
public class Connection implements ServletContextListener {



    private static MongoClient mongo;
    private static DB db;
    private static DBCollection collection;
    private static Logger log = Logger.getLogger(Connection.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            mongo = new MongoClient();
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }
        db = mongo.getDB("Scrumboard");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        mongo.close();
    }


    public static WriteResult insert(DBObject dbObject, String collectionName) {
        return db.getCollection(collectionName).insert(dbObject);
    }

    // Save methods
    public static WriteResult save(String collectionName, DBObject dbObject) {
        return db.getCollection(collectionName).save(dbObject);
    }

    // Update methods
    public static WriteResult update(String collectionName, DBObject searchQuery, DBObject updateQuery, boolean upsert, boolean multi) {
        return db.getCollection(collectionName).update(searchQuery, updateQuery, upsert, multi);
    }

    // Remove methods
    public static WriteResult remove(String collectionName, DBObject deleteQuery) {
        return db.getCollection(collectionName).remove(deleteQuery);
    }

    public static List<DBObject> find(String collectionName, BasicDBObject query, BasicDBObject fields, BasicDBObject sort) {
        return db.getCollection(collectionName).find(query, fields).sort(sort).toArray();
    }

    public static List<DBObject> find(String collectionName, BasicDBObject query, BasicDBObject fields) {
        return db.getCollection(collectionName).find(query, fields).toArray();
    }

    public static List<DBObject> find(String collectionName, BasicDBObject query) {
        return db.getCollection(collectionName).find(query).toArray();
    }
}
