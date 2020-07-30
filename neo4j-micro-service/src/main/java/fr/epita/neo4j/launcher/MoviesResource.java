package fr.epita.neo4j.launcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.epita.neo4j.datamodel.Movie;

@RestController("/neo4j")
public class MoviesResource {
	
	@GetMapping
	public List<Movie> listMovies() {
		Driver driver = GraphDatabase.driver("bolt://192.168.137.50:10687", 
				AuthTokens.basic("neo4j", "root"));
		Session session = driver.session();
		Transaction tx = session.beginTransaction();
		List<Movie> movies = new ArrayList<>();
		
		//Result result = tx.run("MATCH (n {name: $varName }) RETURN n", parameters("varName", name));
		Result result = tx.run("MATCH (n:Movie) RETURN n");
		while (result.hasNext()) {
			Record row = result.next();
			Value value = row.get("n");
			Map<String, Object> properties = value.asEntity().asMap();
			Movie movie = new Movie();
			movie.setName(String.valueOf(properties.get("title")));
			movies.add(movie);
		}
		
		System.out.println("returned this list : " + movies);
		
		return movies;
		
		
	}
 
}
