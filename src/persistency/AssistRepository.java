package persistency;

import java.sql.ResultSet;
import java.util.ArrayList;

import app.Assist;
import app.Developer;

public class AssistRepository extends Repository<Assist> {

	 public AssistRepository(DatabaseConnection conn) {
		 super(conn);
		 
		 this.table = "assist";
		 this.columns = new String[]{"developer", "spentTime"};
	 }
	 
	 public Assist create(Developer developer, Number spentTime) {
		 int id = this.create(new String[]{String.valueOf(developer.getId()), 
				 String.valueOf(spentTime)});
		 Assist assist = new Assist(id, developer, spentTime);
		 return assist;
	 }

	@Override
	protected ArrayList<Assist> parse(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
}