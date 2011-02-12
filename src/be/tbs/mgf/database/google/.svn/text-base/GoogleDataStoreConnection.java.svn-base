package be.tbs.mgf.database.google;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import be.tbs.mgf.database.DataStoreConnection;
import be.tbs.mgf.database.PMF;
import be.tbs.mgf.database.data.Data;

/**
 * DataStore Connection class responsible for making connections and performing operations on the Datastore.
 * No physical connection is actually made until you execute a query or transaction.
 * Objects of this class are reusable and you needn't create new objects for every query.
 * @author Gino
 */
public class GoogleDataStoreConnection implements DataStoreConnection {
	
	private static PersistenceManagerFactory fPMF = PMF.get();
		
	/**
	 * Sets up a Datastore connection.
	 */
	public GoogleDataStoreConnection() {}
	
	/**
	 * Gets the persistence manager for this connection.
	 * Be careful using the persistence manager directy,
	 * as this {@link GoogleDataStoreConnection} object my not be
	 * abe to execute queries or transactions if you do
	 * not close it {@link PersistenceManager} properly.
	 * @return PersistenceManager
	 */
	public PersistenceManager getPersistenceManager() {
//		PersistenceManager pm = fPMF.getPersistenceManager();
//		if (pm.isClosed()) {
//			return getPersistenceManager();
//		} else {
//			return pm;
//		}
		return fPMF.getPersistenceManager();
	}
	
	/**
	 * Gets all stored instances of the given class.
	 * @param c - The name of the stored entity.
	 * @return Object - A list of entities matching the given name.
	 */
	public Object getAll(String c) {
		PersistenceManager pm = getPersistenceManager();
        List<Object> o = (List<Object>) pm.newQuery("select from " + c).execute();
        Object o2 = pm.detachCopyAll(o);
        //pm.close();
        return o2;
	}
	
	/**
	 * Gets all stored instances of the given class for wich the specified field has a specified value.
	 * @param c - The name of the stored entity.
	 * @param field - The field for which a filter should be added.
	 * @param value - The desired value for the field, as a string.
	 * @return Object - A list of entities matching the given name and field values.
	 */
	public Object getAll(String c, String field, String value) {
		PersistenceManager pm = getPersistenceManager();
        Query q = pm.newQuery("select from " + c);
        q.setFilter(field + " == fieldValue");
        q.declareParameters("String fieldValue");
        List<Object> o = (List<Object>) q.execute(value);
        Object o2 = pm.detachCopyAll(o);
        //pm.close();
        return o2;
	}
	
	/**
	 * Gets all stored instances of the given class for wich the specified fields have specified values.
	 * @param c - The name of the stored entity.
	 * @param fieldA - The field for which a filter should be added.
	 * @param valueA - The desired value for the field, as a string.
	 * @param fieldB - The field for which a filter should be added.
	 * @param valueB - The desired value for the field, as a string.
	 * @return Object - A list of entities matching the given name and field values.
	 */
	public Object getAll(String c, String fieldA, String valueA, String fieldB, String valueB) {
		PersistenceManager pm = getPersistenceManager();
        Query q = pm.newQuery("select from " + c);
        q.setFilter(fieldA + "== fieldAValue");
        q.declareParameters("String fieldAValue");
        q.setFilter(fieldB + "== fieldBValue");
        q.declareParameters("String fieldBValue");
        List<Object> o = (List<Object>) q.execute(valueA,valueB);
        Object o2 = pm.detachCopyAll(o);
        //pm.close();
        return o2;
	}
	
	/**
	 * Gets all stored instances of the given class for wich the specified fields have specified values.
	 * @param c - The name of the stored entity.
	 * @param fieldA - The field for which a filter should be added.
	 * @param valueA - The desired value for the field, as a string.
	 * @param fieldB - The field for which a filter should be added.
	 * @param valueB - The desired value for the field, as a string.
	 * @param fieldC - The field for which a filter should be added.
	 * @param valueC - The desired value for the field, as a string.
	 * @return Object - A list of entities matching the given name and field values.
	 */
	public Object getAll(String c, String fieldA, String valueA, String fieldB, String valueB, String fieldC, String valueC) {
		PersistenceManager pm = getPersistenceManager();
        Query q = pm.newQuery("select from " + c);
        q.setFilter(fieldA + "== fieldAValue");
        q.declareParameters("String fieldAValue");
        q.setFilter(fieldB + "== fieldBValue");
        q.declareParameters("String fieldBValue");
        q.setFilter(fieldC + "== fieldCValue");
        q.declareParameters("String fieldCValue");
        List<Object> o = (List<Object>) q.execute(valueA, valueB, valueC);
        Object o2 = pm.detachCopyAll(o);
        //pm.close();
        return o2;
	}
	
	/**
	 * Performs a given query on the data store and returns its result.
	 * @param q - A JDOQL formatted query.
	 * @return Object - The result(s) of your query.
	 */
	public Object doQuery(String q) {
		PersistenceManager pm = getPersistenceManager();
        Object o = pm.newQuery(q).execute();
        pm.close();
        return o;
	}
	
	/**
	 * A rather nonsensical method, executing a given query and returning its result.
	 * @param q - The query to be executed.
	 * @return Object - The entities matching the provided query.
	 */
	public Object doQuery(Query q) {
		return q.execute();
	}
	
	/**
	 * Gives you a transaction object, to make changes to entities stored in the datastore.
	 * @return Transaction
	 */
	public Transaction getTransaction() {
		PersistenceManager pm = getPersistenceManager();
		return pm.currentTransaction();
	}
	
	/**
	 * Adds {@link Data} to the datastore.
	 * @param data - The data to add.
	 */
	public void add(Data data) {
		PersistenceManager pm = getPersistenceManager();
        try {
            pm.makePersistent(data);
        } finally {
            pm.close();
        }
	}
	
	/**
	 * Adds all {@link Data} provided to the datastore.
	 * @param data - The data to add.
	 */
	public void add(Collection<Data> data) {
		PersistenceManager pm = getPersistenceManager();
        try {
            pm.makePersistentAll(data);
        } finally {
            pm.close();
        }
	}

	@Override
	public void delete(Data d) {
		PersistenceManager pm = getPersistenceManager();
		pm.deletePersistent(d);
	}
	
	public <T> Object getObjectByID(Class<T> c, Object id) {
		PersistenceManager pm = getPersistenceManager();
		return pm.getObjectById(c, id);
	}

}
