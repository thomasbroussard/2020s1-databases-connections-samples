package fr.epita.databases;

import java.util.Map;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.Value;
import static org.neo4j.driver.Values.parameters;

public class TestNeo4J {

	public static void main(String[] args) {
		Driver driver = GraphDatabase.driver("bolt://192.168.137.50:10687", 
				AuthTokens.basic("neo4j", "root"));
		Session session = driver.session();
		Transaction tx = session.beginTransaction();

		displaySearch(tx, "Tom Hanks");
		createNode(tx, "Jean Dujardin");
		displaySearch(tx, "Jean Dujardin");
		
		Result result = tx.run("MATCH (Person {name: 'Tom Hanks'})--(Movie) RETURN Movie.title");
		while(result.hasNext()) {
			System.out.println(result.next().get("Movie.title").asString());
		}
		
		tx.commit();
		tx.close();
		session.close();
		driver.close();
	}

	private static void displaySearch(Transaction tx, String name) {
		Result result = tx.run("MATCH (n {name: $varName }) RETURN n", parameters("varName", name));
	
		while (result.hasNext()) {
			Record row = result.next();
			Value value = row.get("n");
			Map<String, Object> properties = value.asEntity().asMap();
			System.out.println(String.valueOf(properties.get("name")));
		}
	}

	
	private static void createNode(Transaction tx, String name) {
		tx.run("CREATE (n:Person {name: $varName }) ", parameters("varName", name));
	}
}
