package fr.epita.neo4j.launcher;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.epita.neo4j.datamodel.Movie;
import fr.epita.neo4j.services.data.Neo4JMovieDAO;

@RestController
public class MoviesResource {
	
	@Inject
	Neo4JMovieDAO dao;
	
	@GetMapping("/movies")
	public List<Movie> listMovies() {
	
		return dao.listAll();
		
		
	}
 
}
