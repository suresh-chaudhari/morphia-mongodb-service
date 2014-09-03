package com.mongo;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.mongo.MongoQueryConstant.OPERATOR;

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
	
//	/**
//	 * Returns all record according to query
//	 * @param column
//	 * @param columnValue
//	 * @param clazz
//	 * @return
//	 * @throws PersistenceException
//	 */
//	public <T> List<T> findByQuery(Query<T> q, Class<T> clazz) throws PersistenceException {
//		try {
//			Query query = datastore.createQuery(clazz);
//			 q = datastore.createQuery(clazz);
//			 q.asList();
////			 q.getSortObject().put(arg0, arg1)
//			return datastore.find(query, clazz);
//		}catch (Exception e) {
//			throw new PersistenceException(e);
//		}
//	}

	
	public enum OrderBy {
		ASCENDING,DESCENDING
	}

}
