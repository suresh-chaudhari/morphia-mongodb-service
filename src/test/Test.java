package test;

import java.util.List;

import org.bson.types.ObjectId;

import com.dataobject.Employee;
import com.dataobject.User;
import com.mongo.MongoPersistenceService;
import com.mongo.MongoPersistenceService.OrderBy;
import com.mongo.MongoQueryConstant.OPERATOR;
import com.mongo.PersistenceException;
import com.mongodb.BasicDBObject;

/**
 * 
 * @author suresh.c
 *
 */
public class Test {
	private static MongoPersistenceService persistenceService = new MongoPersistenceService();

	public static void main(String[] args) {
		try {
//			addorUpdateSingleObject();
//			addMultipleObjects();
//			fetchRecords();
//			checkFindOneMethod();
//			checkFindMethod();
//			getListPaging();
//			getListPagingNative();
//			getListbyNativeQuery();
//			deleteRecords();
//			createIndex(); 
//			dropIndexs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * add single object
	 * @throws PersistenceException 
	 */
	private static void addorUpdateSingleObject() throws PersistenceException {
		User user = new User();
		user.setFirstName("test");
		user.setLastName("value");
		user.setId(new ObjectId("5405a2b93894e9be5e6f2430"));

		/**
		 * add single object
		 */
		persistenceService.addSingleObject(user);
		System.out.println("Object added successfully");
	}

	/**
	 * add single object
	 * @throws PersistenceException 
	 */
	private static void addMultipleObjects() throws PersistenceException {
		/**
		 * Create new document
		 */
		User user = new User();
		user.setFirstName("karan");
		user.setLastName("patel");

		/**
		 * If objectId exist then it updates document
		 */
		Employee employee = new Employee();
		employee.setId(new ObjectId("5406afa53894330ca20caeaf"));
		employee.setName("suresh");
		employee.setDesignation("Software Engineer");
		employee.setEmpId(1);
		/**
		 * add single object
		 */
		persistenceService.addObjects(user,employee);
		System.out.println("Mulitple Objects added successfully");
	}

	/**
	 * fetch all Records
	 * @throws PersistenceException
	 */
	private static void fetchRecords() throws PersistenceException {
		List<User> user = persistenceService.getRecordsList(User.class);
		for (User obj : user) {
			System.out.println(obj);
		}
	}

	/**
	 * fetch all Records
	 * @throws PersistenceException
	 */
	private static void checkFindOneMethod() throws PersistenceException {
		User user = persistenceService.findOne("first_name", "test", User.class);
		System.out.println(user);

		//findOne by Condition operator <>,>=
		Employee employee = persistenceService.findOneByCondition("empId", 1,OPERATOR.LESS_THAN, Employee.class);
		System.out.println(employee);
	}

	/**
	 * check the find methods for persistence service
	 * @throws PersistenceException
	 */
	private static void checkFindMethod() throws PersistenceException {
		List<User> user =  persistenceService.find("first_name", "test","_id",OrderBy.DESCENDING, User.class);
		System.out.println(user);

		//findOne by Condition operator <>,>=
		List<Employee> employee = persistenceService.findByCondition("empId", 1,OPERATOR.LESS_THAN_EQUAL,null, null, Employee.class);
		System.out.println(employee);
	}

	/**
	 * fetch all Records By pagination
	 * @throws PersistenceException
	 */
	private static void getListPaging() throws PersistenceException {
		List<User> user =  persistenceService.getListByPaging(User.class, 2, 3, null, null);
		for (User obj : user) {
			System.out.println(obj.getId());
		}
	}
	
	/**
	 * fetch all Records By pagination
	 * @throws PersistenceException
	 */
	private static void getListPagingNative() throws PersistenceException {
		List<User> user =  persistenceService.getListByNativeQuery(new BasicDBObject(), User.class);
		for (User obj : user) {
			System.out.println(obj.getId());
		}
	}
	
	/**
	 * Get documents by using Native query (Mongo Java driver api)
	 * @throws PersistenceException
	 */
	private static void getListbyNativeQuery() throws PersistenceException {
		BasicDBObject query = new BasicDBObject();
		query.append("first_name", "test");
		List<User> user =  persistenceService.getListByPagingNativeQuery(User.class, query, 1, 2, "_id" , OrderBy.DESCENDING);
		for (User obj : user) {
			System.out.println(obj.getId());
		}
		
		/**
		 * Projection
		 * Include use-1
		 * Excluse use-0
		 */
		
		BasicDBObject projection = new BasicDBObject("first_name",1);
		List<User> user1 =  persistenceService.getListByProjection(User.class, query,projection, 1, 2, null,null);
		for (User obj : user1) {
			System.out.println(obj.getId() +" firstname:"+obj.getFirstName() + " Lastname:"+obj.getLastName());
		}
	}
	/**
	 * Delete documents form collection
	 * @throws PersistenceException
	 */
	private static void deleteRecords() throws PersistenceException {
//		persistenceService.deleteRecord("first_name", "karan12", User.class);
		
		BasicDBObject query = new BasicDBObject("first_name", "karan1");
		persistenceService.deleteRecordByNativeQuery(query, User.class);
	}

	/**
	 * Create Index
	 * @throws PersistenceException
	 */
	private static void createIndex() throws PersistenceException {
		//create ascending order index
//		persistenceService.createIndex(User.class, null, "first_name");
//		
//		//create descending order index
//		persistenceService.createIndex(User.class, OrderBy.DESCENDING, "last_name");
		
		//create multiple column's ascending order index
		persistenceService.createIndex(User.class, OrderBy.ASCENDING, "first_name", "last_name");
		
		//create multiple column's ascending order index
		persistenceService.createCompoundIndex(User.class, "first_name", "last_name");
		
	}
	
	/**
	 * Drop Indexs for Collection
	 * @throws PersistenceException
	 */
	private static void dropIndexs() throws PersistenceException {
		//Drop indexes for collection
		persistenceService.dropIndexes(User.class);
		
	}
	
	
}
