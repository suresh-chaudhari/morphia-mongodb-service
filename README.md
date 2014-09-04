Mongo Persistence Service Using Morphia
==========================
This is a Generic Persistence Service to use for MongoDB easily. Created some generic method which is help full for ORM operation of MongoDB

MongoDB Property
==========================
Open the mongo.properties file from the conf folder.
Do the following changes in property file

mongo.host=localhost
mongo.port=27017
mongo.dbname=test
mongo.username=""
mongo.password =""
mongo.connectionsPerHost=200

Usage
==========================
```java
// Create the Mongo Persistence Service object
MongoPersistenceService persistenceService = new MongoPersistenceService();

//fetch the documents
List<User> user =  persistenceService.find("first_name", "test","_id",OrderBy.DESCENDING, User.class);
	
//fetch the documents by Conditional operator <>,>=
List<Employee> employee = persistenceService.findByCondition("empId", 1,OPERATOR.LESS_THAN_EQUAL,null, null,Employee.class);

// fetch the document by using native query of MongoDB and It also fetch data by pagination(lazy loading)
BasicDBObject query = new BasicDBObject();
query.append("first_name", "test");
List<User> user =  persistenceService.getListByPagingNativeQuery(User.class, query, 1, 5, "_id" , OrderBy.DESCENDING);

//fetch the document using projection 
BasicDBObject query = new BasicDBObject();
query.append("first_name", "test");

BasicDBObject projection = new BasicDBObject("first_name",1);
List<User> user1 =  persistenceService.getListByProjection(User.class, query,projection, 1, 2, null,null);
```
