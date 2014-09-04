package com.mongo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongo.MongoQueryConstant.OPERATOR;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * 
 * @author suresh
 *
 */
public class MongoPersistenceService {

	private Datastore datastore = MongoDbConnection.getDataStoreInstance();

	/**
	 * add or update single record object
	 * If a document does not exist with the specified _id value, the save() method performs an insert with the specified fields in the document 
	 * If a document exists with the specified _id value, the save() method performs an update, replacing all field in the existing record with the fields from the document.
	 * @param object
	 * @throws PersistenceException
	 */
	public void addSingleObject(Object object)throws PersistenceException {
		try {
			datastore.save(object);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * add or update multiple records object in collections
	 * If a document does not exist with the specified _id value, the save() method performs an insert with the specified fields in the document 
	 * If a document exists with the specified _id value, the save() method performs an update, replacing all field in the existing record with the fields from the document.
	 * @param object
	 * @throws PersistenceException
	 */
	public void addObjects(Object... object)throws PersistenceException {
		try {
			datastore.save(object);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Get count of collections 
	 * @return
	 */
	public long getCount(Class<?> clazz) throws PersistenceException {
		try {
			return datastore.getCount(clazz);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Get count By query collections 
	 * @return
	 */
	public long getCountByQuery(Query<?> query) throws PersistenceException {
		try {
			return datastore.getCount(query);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Get all record from Specify Collections
	 * @param clazz
	 * @return
	 */
	public <T> List<T> getRecordsList(Class<T> clazz) throws PersistenceException {
		try {
			return datastore.find(clazz).asList();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Returns only one record Object
	 * @param key
	 * @param value
	 * @param clazz
	 * @return
	 * @throws PersistenceException
	 */
	public <T> T findOne(String key ,Object value, Class<T> clazz)  throws PersistenceException {
		try {
			return datastore.find(clazz, key, value).get();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Returns only one record Object by Query
	 * Note : Use Operator <B> enum </B> for get data accodring to condtion
	 * like: OPERATOR.EQUAL.getName()
	 * @param key key
	 * @param value value
	 * @param operator operator enum for condition check
	 * @param clazz
	 * @return
	 * @throws PersistenceException
	 */
	public <T> T findOneByCondition(String key ,Object value, OPERATOR operator, Class<T> clazz) throws PersistenceException {
		try {
			String query = key + " " + operator.getName();
			return datastore.find(clazz, query, value).get();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Returns all record according to criteria by Order(ascending\descending)
	 * Note: If orderkey and orderby value is null then it gives record in default ordering.
	 * @param key
	 * @param value
	 * @param clazz
	 * @return
	 * @throws PersistenceException
	 */
	public <T> List<T> find(String key, Object value, String orderKey, OrderBy orderBy, Class<T> clazz) throws PersistenceException {
		try {
			if (orderKey !=null && orderBy !=null) {
				if(orderBy == OrderBy.DESCENDING)
					orderKey ="-"+orderKey;
				return datastore.find(clazz, key, value).order(orderKey).asList();
			}
			return datastore.find(clazz, key, value).asList();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Returns all record Object using Criteria filter by Order(ascending\descending)
	 * Note : Use Operator <B> enum </B> for get data according to condition
	 * Note: If orderkey and orderby value is null then it gives record in default ordering.
	 * like: OPERATOR.EQUAL.getName()
	 * @param key
	 * @param value
	 * @param operator operator for condition check
	 * @param clazz
	 * @return
	 * @throws PersistenceException
	 */
	public <T> List<T> findByCondition(String key ,Object value, OPERATOR operator, String orderKey, OrderBy orderBy, Class<T> clazz) 
			throws PersistenceException {
		try {
			String query = key + " " + operator.getName();
			if (orderKey !=null && orderBy !=null) {
				if(orderBy == OrderBy.DESCENDING)
					orderKey ="-"+orderKey; //gives data in descending order
				return datastore.find(clazz, query, value).order(orderKey).asList();
			}
			return datastore.find(clazz, query, value).asList();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Returns all records by $IN search query
	 * @param key
	 * @param value
	 * @param operator operator for condition check
	 * @param clazz
	 * @return
	 * @throws PersistenceException
	 */
	public <T> List<T> findByIn(String key ,Object value, OPERATOR operator, String orderKey, OrderBy orderBy, Class<T> clazz) 
			throws PersistenceException {
		try {
			String query = key + " " + operator.getName();
			if (orderKey !=null && orderBy !=null) {
				if(orderBy == OrderBy.DESCENDING)
					orderKey ="-"+orderKey; //gives data in descending order
				return datastore.find(clazz, query, value).order(orderKey).asList();
			}
			return datastore.find(clazz, query, value).asList();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Get all records by Paging 
	 * If you want paging by descending or ascending order specify key and order otherwise it should be null.
	 * @param query
	 * @param pageNumber The value must be 1 or greater than
	 * @param pageSize
	 * @param clazz
	 * @return
	 * @throws PersistenceException
	 */
	public <T> List<T> getListByFilterByPaging(Class<T> clazz, String key, Object value, int pageNumber, int pageSize ,String orderKey, OrderBy orderBy) throws PersistenceException {
		try {
			if (pageNumber <= 0) 
				throw new PersistenceException("Page Number value must be 1 or greater than.");
			if (orderKey !=null && orderBy !=null) {
				if(orderBy == OrderBy.DESCENDING)
					orderKey ="-"+orderKey; //gives data in descending order
				return datastore.find(clazz, key, value, (pageNumber-1)*pageSize, pageSize).order(orderKey).asList();
			}
			return datastore.find(clazz, key, value, (pageNumber-1)*pageSize, pageSize).asList();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Get all records by Paging 
	 * If you want paging by descending or ascending order specify key and order otherwise it should be null.
	 * @param query
	 * @param pageNumber The value must be 1 or greater than
	 * @param pageSize
	 * @param clazz
	 * @return
	 * @throws PersistenceException
	 */
	public <T> List<T> getListByPaging(Class<T> clazz, int pageNumber, int pageSize ,String orderKey, OrderBy orderBy) throws PersistenceException {
		try {
			if (pageNumber <= 0) 
				throw new PersistenceException("Page Number value must be 1 or greater than.");
			if (orderKey !=null && orderBy !=null) {
				if(orderBy == OrderBy.DESCENDING)
					orderKey ="-"+orderKey; //gives data in descending order
				return datastore.find(clazz).order(orderKey).offset((pageNumber-1)*pageSize).limit(pageSize).asList();
			}
			return datastore.find(clazz).offset((pageNumber-1)*pageSize).limit(pageSize).asList();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Get all records by Paging using native Query 
	 * If you want paging by descending or ascending order specify key and order otherwise it should be null.
	 * @param query
	 * @param clazz
	 * @param pageNumber The value must be 1 or greater than
	 * @param pageSize
	 * @param orderKey
	 * @param order
	 * @return
	 * @throws PersistenceException
	 */
	public <T> List<T> getListByPagingNativeQuery(Class<T> clazz, BasicDBObject query,int pageNumber, int pageSize ,String orderKey, OrderBy order)
			throws PersistenceException {
		try {
			List<DBObject> dbObjectList = null;
			if (pageNumber <= 0) 
				throw new PersistenceException("Page Number must be 1 or greater than.");
			DBCollection collection = datastore.getCollection(clazz);
			BasicDBObject orderBy = null;
			if (orderKey != null && order != null) 
				orderBy = new BasicDBObject().append(orderKey, order.value());	
			if (orderBy != null)
				dbObjectList = collection.find(query).sort(orderBy).skip((pageNumber-1)*pageSize).limit(pageSize).toArray();
			else 
				dbObjectList = collection.find(query).skip((pageNumber-1)*pageSize).limit(pageSize).toArray();
			return toObject(clazz, dbObjectList);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Get List of Records by Projection
	 * @param query
	 * @param projectionQuery
	 * @param orderKey
	 * @param order
	 * @param clazz
	 * @return
	 * @throws PersistenceException
	 */
	public <T> List<T> getListByProjection(Class<T> clazz, BasicDBObject query,BasicDBObject projectionQuery, int pageNumber, int pageSize, String orderKey, OrderBy order) 
			throws PersistenceException {
		try {
			List<DBObject> dbObjectList = null;
			DBCollection collection = datastore.getCollection(clazz);
			BasicDBObject orderBy = null;
			if (orderKey != null && order != null) 
				orderBy = new BasicDBObject().append(orderKey, order.value());	
			if (orderBy == null)
				dbObjectList = collection.find(query, projectionQuery).skip((pageNumber-1)*pageSize).limit(pageSize).toArray();
			else
				dbObjectList = collection.find(query, projectionQuery).sort(orderBy).skip((pageNumber-1)*pageSize).limit(pageSize).toArray();
			return toObject(clazz, dbObjectList);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}


	/**
	 * Get all records by more filter using native Query 
	 * @param query
	 * @return
	 * @throws PersistenceException
	 */
	public <T> List<T> getListByNativeQuery(BasicDBObject query, Class<T> clazz) throws PersistenceException {
		try {
			List<DBObject> dbObjectList = null;
			DBCollection collection = datastore.getCollection(clazz);
			dbObjectList = collection.find(query).toArray();
			return toObject(clazz, dbObjectList);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}
	
	/**
	 * Drop Collection
	 * @param clazz
	 * @throws PersistenceException 
	 */
	public void dropCollection(Class<?> clazz) throws PersistenceException {
		try {	
			datastore.getCollection(clazz).drop();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Delete document data
	 * @param key
	 * @param value
	 * @param clazz
	 * @throws PersistenceException
	 */
	public void deleteRecord(String key, Object value, Class<?> clazz)throws PersistenceException  {
		try {
			datastore.delete(datastore.createQuery(clazz).filter(key, value));
		}  catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Delete all documents from collection
	 * @param key
	 * @param value
	 * @param clazz
	 * @throws PersistenceException
	 */
	public void deleteRecords(Class<?> clazz)throws PersistenceException  {
		try {
			DBCollection collection = datastore.getCollection(clazz);
			collection.remove(new BasicDBObject());
		}  catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Delete Multiple documents by native query
	 * @param query 
	 * @param clazz
	 * @throws PersistenceException
	 */
	public void deleteRecordByNativeQuery(BasicDBObject query, Class<?> clazz)throws PersistenceException  {
		try {
			DBCollection collection = datastore.getCollection(clazz);
			collection.remove(query);
		}  catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Update Single documents
	 * @param clazz
	 * @param query
	 * @param updateQuery
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("deprecation")
	public int updateSingleRecordByQuery(Class<?> clazz, BasicDBObject query, BasicDBObject updateQuery) throws PersistenceException  {
		try {
			DBCollection collection = datastore.getCollection(clazz);
			WriteResult res = collection.update(query, updateQuery); //update single column
			if (res.getError()!=null && res.getN() == 0) 
				System.err.println("Update Document Failed,  Error:"+res.getError()+", count: "+res.getN()+", LastError"+res.getLastError());
			return res.getN();
		}  catch (Exception e) {
			throw new PersistenceException(e);
		}
	}
	
	/**
	 * Update multiple record
	 * Note:Made update query according to below operation
	 * <B>You can use any function like
	 * <li>$set - update column
	 * <li>$unset - remove column which match
	 * <li>$push
	 * @param clazz
	 * @param query
	 * @param updateQuery
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("deprecation")
	public int updateMultiRecordByQuery(Class<?> clazz,BasicDBObject query, BasicDBObject updateQuery) throws PersistenceException  {
		try {
			DBCollection collection = datastore.getCollection(clazz);
			WriteResult res = collection.updateMulti(query, updateQuery);
			if (res.getError()!=null && res.getN() == 0) {
				System.err.println("Update  Document Failed,  Error:"+res.getError()+", count: "+res.getN()+", LastError:"+res.getLastError());
			}
			return res.getN();
		}  catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	
	/**
	 * 
	 * @author suresh.c
	 *
	 */
	public enum OrderBy {
		ASCENDING(1),
		DESCENDING(-1),
		;
		public final int value;

		private OrderBy(int value) {
			this.value = value;
		}
		public int value() {
			return value;
		}
	}
	/**
	 * Convert DBObject to Object 
	 * @param clazz
	 * @param dbObjectList
	 * @return
	 * @throws IOException 
	 */
	public  <T> List<T> toObject(Class<T> clazz, List<DBObject> dbObjectList) throws IOException {
		if (dbObjectList == null || dbObjectList.size() == 0)
			return null;
		List<T> t = new ArrayList<T>();
		for (DBObject dbObject : dbObjectList) 
			t.add(getObject(dbObject, clazz));
		return t;
	}

	/**
	 * Convert Dbobject to Object
	 * @param dbObj
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public <T> T getObject(DBObject dbObj, Class<T> clazz) throws IOException {
		Morphia morphia = new Morphia();
		morphia.map(clazz);
		T pojo = morphia.fromDBObject(clazz, dbObj);
		return pojo;
	}

}
