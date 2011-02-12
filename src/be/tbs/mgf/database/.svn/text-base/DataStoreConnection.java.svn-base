package be.tbs.mgf.database;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import be.tbs.mgf.database.data.Data;

/**
 * @author Gino
 */
public interface DataStoreConnection {
	
	/**
	 * Gets the persistence manager for this connection.
	 * Be careful using the persistence manager directy,
	 * as this {@link DataStoreConnection} object my not be
	 * abe to execute queries or transactions if you do
	 * not close th {@link PersistenceManager} properly.
	 * @return PersistenceManager
	 */
	public PersistenceManager getPersistenceManager();
	
	/**
	 * Gets all stored instances of the given class.
	 * @param c - The name of the stored entity.
	 * @return Object - A list of entities matching the given name.
	 */
	public Object getAll(String c);
	
	/**
	 * Gets all stored instances of the given class for wich the specified field has a specified value.
	 * @param c - The name of the stored entity.
	 * @param field - The field for which a filter should be added.
	 * @param value - The desired value for the field, as a string.
	 * @return Object - A list of entities matching the given name and field values.
	 */
	public Object getAll(String c, String field, String value);
	
	/**
	 * Gets all stored instances of the given class for wich the specified fields have specified values.
	 * @param c - The name of the stored entity.
	 * @param fieldA - The field for which a filter should be added.
	 * @param valueA - The desired value for the field, as a string.
	 * @param fieldB - The field for which a filter should be added.
	 * @param valueB - The desired value for the field, as a string.
	 * @return Object - A list of entities matching the given name and field values.
	 */
	public Object getAll(String c, String fieldA, String valueA, String fieldB, String valueB);
	
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
	public Object getAll(String c, String fieldA, String valueA, String fieldB, String valueB, String fieldC, String valueC);
	
	/**
	 * Performs a given query on the data store and returns its result.
	 * @param q - A JDOQL formatted query.
	 * @return Object - The result(s) of your query.
	 */
	public Object doQuery(String q);
	
	/**
	 * A rather nonsensical method, executing a given query and returning its result.
	 * @param q - The query to be executed.
	 * @return Object - The entities matching the provided query.
	 */
	public Object doQuery(Query q);
	
	/**
	 * Gives you a transaction object, to make changes to entities stored in the datastore.
	 * @return Transaction
	 */
	public Transaction getTransaction();
	
	public <T> Object getObjectByID(Class<T> c, Object id);
	
	/**
	 * Adds {@link Data} to the datastore.
	 * @param data - The data to add.
	 */
	public void add(Data data);
	
	/**
	 * Adds all {@link Data} provided to the datastore.
	 * @param data - The data to add.
	 */
	public void add(Collection<Data> data);
	
	/**
	 * Deletes the specified object from the datastore.
	 * @param o - The object to be deleted.
	 */
	public void delete(Data d);
	
}
