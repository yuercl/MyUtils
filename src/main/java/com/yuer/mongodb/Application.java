package com.yuer.mongodb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * Java + MongoDB Hello world Example
 * 
 */
public class Application {
	public static void main(String[] args) {

		try {
			// connect to mongoDB, ip and port number
			Mongo mongo = new Mongo("127.0.0.1", 3333);

			// get database from MongoDB,
			// if database doesn't exists, mongoDB will create it automatically
			DB db = mongo.getDB("yourdb");

			// Get collection from MongoDB, database named "yourDB"
			// if collection doesn't exists, mongoDB will create it
			// automatically
			DBCollection collection = db.getCollection("yourCollection");

			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("id", 1001);
			document.put("msg", "hello world mongoDB in Java");
			BasicDBObject document2 = new BasicDBObject();
			document2.put("id", 1002);
			document2.put("msg", "hello world mongoDB in Java22222222");

			// save it into collection named "yourCollection"
			collection.insert(document, document2);

			// search query
			BasicDBObject searchQuery = new BasicDBObject();
			// searchQuery.put("_id", "530d525f25efc8862a2877ff");
			searchQuery.put("id", 1002);

			// query it
			DBCursor cursor = collection.find(searchQuery);

			// loop over the cursor and display the retrieved result
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}
}
