package fr.epita.neo4j.datamodel;



public class Movie {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + "]";
	}
	
	
	
	

}
