package persistency;

import java.sql.ResultSet;
import java.util.ArrayList;

public abstract class Repository<T extends DatabaseObject> {

	protected String table;
	protected String[] columns;
	
	private DatabaseConnection conn;
	
	/**
	 * @param conn
	 */
	public Repository(DatabaseConnection conn) {
		this.conn = conn;
	}
	
	abstract protected ArrayList<T> parse(ResultSet rs);

	protected int create(String[] values) {
		// TODO
		return 0;
	}
	
	public T read(int id) {
		// TODO
		return null;
	}
	
	protected void update(T entity) {
		// TODO
	}
	
	public void delete(int id) {
		// TODO
	}
	
}