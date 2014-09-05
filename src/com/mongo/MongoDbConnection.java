package com.mongo;


import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;

/**
 * 
 * @author suresh
 *
 */
public class MongoDbConnection {


	/**
	 * 
	 * @return
	 */
	public static Datastore getDataStoreInstance() {
		Datastore ds = null;
		try{
			ServerAddress address = new ServerAddress(MONGO_PROPERTY.HOST.V(), Integer.parseInt(MONGO_PROPERTY.PORT.V()) );
			MongoClientOptions options = MongoClientOptions.builder()
					.connectionsPerHost(Integer.parseInt(MONGO_PROPERTY.CONNECTION_PER_HOST.V()))
					.build();
			Morphia morphia = new Morphia();
			MongoClient mongoClient = new MongoClient(address,null, options);
			ds = morphia.createDatastore(mongoClient, MONGO_PROPERTY.DB_NAME.V() );	
			ds.ensureIndexes(); //creates all defined with @Indexed
		}  catch (UnknownHostException | MongoException e) {
			e.printStackTrace();
		}
		return ds;
	}
	/**
	 * Credentials is set or not in property file
	 * @return
	 */
	private static boolean isCredntials() {
		boolean isAvailable = false;
		if(MONGO_PROPERTY.USERNAME.V() !=null || MONGO_PROPERTY.USERNAME.V().trim().length() > 0 ||
				MONGO_PROPERTY.PASSWORD.V() !=null || MONGO_PROPERTY.PASSWORD.V().trim().length() > 0)
			isAvailable = true;
		return isAvailable;
	}
	/**
	 * Mongo Connection Database Property 
	 * @author suresh
	 *
	 */
	public enum MONGO_PROPERTY {
		HOST( PropertyUtil.getProperty("mongo.host") ),
		PORT( PropertyUtil.getProperty("mongo.port") ),
		DB_NAME( PropertyUtil.getProperty("mongo.dbname") ),
		USERNAME( PropertyUtil.getProperty("mongo.username") ),
		PASSWORD( PropertyUtil.getProperty("mongo.password") ),
		CONNECTION_PER_HOST( PropertyUtil.getProperty("mongo.connectionsPerHost") ),
		;

		private final String v;

		private MONGO_PROPERTY(String v) {
			this.v = v;
		}

		public String V() {
			return v;
		}
	}

}
