package utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestQueryBuilder {

	@Test
	public void testSelectAll() {
		String q1 = Query.SelectAllFrom("foo").End();
		assertEquals("Simple select", "SELECT * FROM foo", q1);
	}
	
	@Test
	public void testSelectFrom() {
		String q1 = Query.Select("test_func()").End();
		assertEquals("Simple select", "SELECT test_func()", q1);

		String q2 = Query.Select("id").From("foo").End();
		assertEquals("Simple select", "SELECT id FROM foo", q2);

		String q3 = Query.Select("id, name, age").From("foo").End();
		assertEquals("Simple select", "SELECT id, name, age FROM foo", q3);
	}
	
	@Test
	public void testUpdate() {
		String q1 = Query.Update("foo", new String[]{"name"}, new String[]{"Karlsson"}).End();
		assertEquals("Update single field", "UPDATE foo SET name='Karlsson'", q1);

		String q2 = Query.Update("foo", new String[]{"name"}, new String[]{"Karlsson"}).WhereEquals("id", 1).End();
		assertEquals("Update where", "UPDATE foo SET name='Karlsson' WHERE id = 1", q2);

		String q3 = Query.Update("foo", new String[]{"name"}, new String[]{"Karlsson"}).WhereEquals("name", "foo").End();
		assertEquals("Update where", "UPDATE foo SET name='Karlsson' WHERE name = 'foo'", q3);
		
		String q4 = Query.Update("foo", new String[]{"name", "age"}, new String[]{"Karlsson", "37"}).End();
		assertEquals("Update multiple fields", "UPDATE foo SET name='Karlsson',age='37'", q4);
	}
	
	@Test
	public void testDelete() {
		String q1 = Query.DeleteFrom("foo").End();
		assertEquals("Update single field", "DELETE FROM foo", q1);

		String q2 = Query.DeleteFrom("foo").WhereEquals("id", 1).End();
		assertEquals("Update single field", "DELETE FROM foo WHERE id = 1", q2);
	}
	
	@Test
	public void testWhere() {
		String q1 = Query.SelectAllFrom("foo").WhereEquals("id", 1).End();
		assertEquals("Where equals", "SELECT * FROM foo WHERE id = 1", q1);

		String q2 = Query.SelectAllFrom("foo").WhereEquals("name", "bar").End();
		assertEquals("Where equals", "SELECT * FROM foo WHERE name = 'bar'", q2);

		String q3 = Query.SelectAllFrom("foo").WhereEquals("name", "bar").WhereEquals("id", 1).End();
		assertEquals("Where equals", "SELECT * FROM foo WHERE name = 'bar' AND id = 1", q3);

		String q4 = Query.SelectAllFrom("foo").WhereLessThan("name", "bar").End();
		assertEquals("Where less", "SELECT * FROM foo WHERE name < 'bar'", q4);
		
		String q5 = Query.SelectAllFrom("foo").WhereLessThan("id", 5).End();
		assertEquals("Where less", "SELECT * FROM foo WHERE id < 5", q5);

		String q6 = Query.SelectAllFrom("foo").WhereMoreThan("name", "bar").End();
		assertEquals("Where less", "SELECT * FROM foo WHERE name > 'bar'", q6);

		String q7 = Query.SelectAllFrom("foo").WhereMoreThan("id", 6).End();
		assertEquals("Where more", "SELECT * FROM foo WHERE name > 6", q7);
	}
	
	@Test
	public void testWhereIn() {
		String q1 = Query.Select("foo").From("bar").WhereIn("id", Query.Select("id").From("baz")).End();
		assertEquals("Where In", "SELECT foo FROM bar WHERE id IN (SELECT id FROM baz)", q1);
	}
	
	@Test
	public void testInsertInto() {
		String q1 = Query.InsertInto("foo", new String[]{"name", "age"}, new String[]{"karl", "37"}).End();
		assertEquals("Insert into", "INSERT INTO foo (name,age) VALUES (\"karl\",\"37\")", q1);

		String q2 = Query.InsertInto("foo", new String[]{"name"}, new String[]{"karl"}).End();
		assertEquals("Insert into", "INSERT INTO foo (name) VALUES (\"karl\")", q2);
		
		String q3 = Query.InsertInto("foo", new String[]{}, new String[]{}).End();
		assertEquals("Insert into", "", q3);
	}
	
	@Test
	public void testOrderBy() {
		String q2 = Query.SelectAllFrom("foo").WhereEquals("id", 1).OrderBy("name").End();
		assertEquals("Order by after where", "SELECT * FROM foo WHERE id = 1 ORDER BY name ASC", q2);
		
		String q3 = Query.SelectAllFrom("foo").OrderBy("name", SortDirection.Desc).End();
		assertEquals("Order by desc", "SELECT * FROM foo ORDER BY name DESC", q3);
	}

	
	@Test
	public void testLimit() {
		String q2 = Query.SelectAllFrom("foo").WhereEquals("id", 1).OrderBy("name").Limit(5).End();
		assertEquals("Order by after where", "SELECT * FROM foo WHERE id = 1 ORDER BY name ASC LIMIT 5", q2);
		
		String q3 = Query.SelectAllFrom("foo").Limit(1).End();
		assertEquals("Order by desc", "SELECT * FROM foo LIMIT 1", q3);
	}
	
	@Test
	public void testExists() {
		String q1 = Query.Exists("foo", 1).End();
		assertEquals("Exists", "SELECT EXISTS(SELECT * FROM foo WHERE id=1)", q1);
	}
	
	@Test
	public void testCount() {
		String q1 = Query.Count("foo").End();
		assertEquals("Count", "SELECT COUNT(*) FROM foo", q1);
	}
}
