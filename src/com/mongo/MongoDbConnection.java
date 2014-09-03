package com.mongo;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * 
 * @author suresh
 *
 */
public class MongoDbConnection {

	private static final int port = 27017;
	private static final String host = "localhost";
	private static final String dbName="test";

	/**
	 * 
	 * @return
	 */
	public static Datastore getDataStoreInstance() {
		Datastore ds = null;
		try{
			MongoClient mongoClient = new MongoClient( host , port);
			Morphia morphia = new Morphia();
			ds = morphia.createDatastore(mongoClient, dbName);
		}  catch (UnknownHostException | MongoException e) {
			e.printStackTrace();
		}
		return ds;
	}

}
