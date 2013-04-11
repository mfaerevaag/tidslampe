package persistency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ArrayUtils;
import utils.StringUtils;

public class DatabaseConnection {

	private Connection conn;
	private Statement stmt;

	public DatabaseConnection(String dbFile) {
		try {
			Class.forName("org.sqlite.JDBC");

			this.conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			this.stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int create(String table, String[] columns, String[] values) {
		String[] cols = ArrayUtils.wrapElementsWith(columns, "\"");
		String[] vals = ArrayUtils.wrapElementsWith(values, "\"");
		final String query = String
				.format("insert into %s (%s) values (%s)", table,
						StringUtils.join(cols, ','),
						StringUtils.join(vals, ','));
		int id = -1;
		try {
			this.execUpdate(query);

			// Get the id of the inserted row
			ResultSet rs = this.execQuery("select last_insert_rowid()");
			if (rs.next()) {
				id = rs.getInt("last_insert_rowid()");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public ResultSet execQuery(String query) {
//		System.out.println(query);
		try {
			return this.stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int execUpdate(String query)  {
//		System.out.println(query);
		try {
			return this.stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	 
	public ResultSet readSpecific(String table, String key, String value){
		String query = "SELECT * FROM " + table + " WHERE " + key + "='" + value +"'";
		return this.execQuery(query);
	}

	public ResultSet readByID(String table, int id)  {
//		String query = String.format("select * from %s where id=%d", table, id);
//		return this.execQuery(query);
		return this.readSpecific(table, "id", String.valueOf(id));
	}

	public ResultSet readAll(String table)  {
		String query = String.format("select * from %s", table);
		return this.execQuery(query);
	}

	public ResultSet readAllWhere(String table, String key, String value) {
		String query = String.format("select * from %s where %s=%s", table, key, value);
		return this.execQuery(query);
	}
	
	public void update(String table, int id, String column, String value) {
//		if (count(table,id) < 1) Troll.getInstance().showYourself();
		
		String query = String.format("update %s set %s=\"%s\" where id=%d", table, column, value, id);
		this.execUpdate(query);
	}
	
	public void update(String table, int id, String[] columns, String[] values) {
//		if (count(table,id) < 1) Troll.getInstance().showYourself();
		
		String[] vals = ArrayUtils.wrapElementsWith(values, "\"");
		String query = String.format("UPDATE %s SET", table);
		
		for (int i = 0; i < columns.length; i++) {
			if (i+1 >= columns.length) {
				query = String.format("%s %s=%s", query, columns[i], vals[i]);	
			} else {
				query = String.format("%s %s=%s,", query, columns[i], vals[i]);
			}
		}
		
		query = String.format("%s WHERE id=%d", query, id);
		this.execUpdate(query);
	}

	public void delete(String table, int id) {
//		if (count(table,id) < 1) Troll.getInstance().showYourself();
		
		String query = String.format("delete from %s where id=%d", table, id);
		this.execUpdate(query);
	}
	
	public int count(String table) {
		String query = "SELECT COUNT(*) FROM " + table;
		ResultSet rs = this.execQuery(query);
		int count = -1;
		try {
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int count(String table, int id) {
		String query = String.format("SELECT COUNT(*) FROM %s WHERE id=%d", table, id);
		ResultSet rs = this.execQuery(query);
		int count = -1;
		try {
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
