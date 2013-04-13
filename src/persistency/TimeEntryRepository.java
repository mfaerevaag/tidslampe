package persistency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.Query;
import model.TimeEntry;

public class TimeEntryRepository extends Repository<TimeEntry> {

	public TimeEntryRepository(Database db) {
		super(db);
		this.table = "time_entry";
		this.columns = new String[]{"start_time", "end_time","developer_activity_relation_id","developer_id"};
	}
	
	public List<TimeEntry> entriesByDeveloperID(int id){
		ResultSet rs = this.db.getConn().execQuery(
				Query.SelectAllFrom(this.table).WhereEquals("developer_id", id));
		return this.parse(rs);
	}
	
	
	@Override
	protected List<TimeEntry> parse(ResultSet rs) {

		List<TimeEntry> timeEntries = new ArrayList<TimeEntry>();
		try {
			while (rs.next()) {
				timeEntries.add(new TimeEntry(
						this.db, rs.getInt("id"), 
						rs.getInt(this.columns[0]), rs.getInt(this.columns[1]), 
						rs.getInt(this.columns[2]), rs.getInt(this.columns[3])));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timeEntries;
	}

}
