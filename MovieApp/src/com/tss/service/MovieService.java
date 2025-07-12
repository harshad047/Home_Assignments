package com.tss.service;

import com.tss.exception.MovieStoreEmptyException;
import com.tss.exception.MovieStoreFullException;
import com.tss.exception.NoSuchMovieExceptionFound;
import com.tss.model.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
	private List<Movie> movies;
	private static final int MAX_MOVIES = 5;
	private static final String FILE_NAME = "movies.json";

	public MovieService() {
		this.movies = loadMovies();
	}

	public List<Movie> getMovies(){
		if (movies.isEmpty()) {
			throw new MovieStoreEmptyException();
		}
		return movies;
	}

	public void addMovie(String name, String genre, int year){
	    if (movies.size() >= MAX_MOVIES) {
	        throw new MovieStoreFullException();
	    }

	    if (!isValidGenre(genre)) {
	        System.out.println("Invalid genre: Genre cannot be only numbers or empty.");
	        return;
	    }

	    if (!isValidYear(year)) {
	        System.out.println("Invalid year: Year must be a 4-digit number.");
	        return;
	    }

	    String id = generateId(name, genre, year);
	    movies.add(new Movie(id, name, genre, year));
	    saveMovies();
	    System.out.println("Movie added with ID: " + id);
	}


	public void deleteMovie(String id) {
	    if (movies.isEmpty()) {
	        throw new MovieStoreEmptyException();
	    }
	    boolean found = false;
	    Movie deletedMovie = null;
	    for (int i = 0; i < movies.size(); i++) {
	        Movie m = movies.get(i);
	        if (m.getId().equalsIgnoreCase(id)) {
	            deletedMovie = m;
	            movies.remove(i);
	            found = true;
	            break;
	        }
	    }
	    if (found) {
	        displayMovie(deletedMovie);
	        saveMovies();
	        System.out.println("Movie deleted successfully.");
	        return;
	    }
	    throw new NoSuchMovieExceptionFound();
	}
	
	public void searchMovie(String id) {
	    if (movies.isEmpty()) {
	        throw new MovieStoreEmptyException();
	    }

	    boolean found = false;
	    Movie foundMovie = null;

	    for (Movie movie : movies) {
	        if (movie.getId().equalsIgnoreCase(id)) {
	            foundMovie = movie;
	            found = true;
	            break;
	        }
	    }

	    if (found) {
	        displayMovie(foundMovie);
	        System.out.println("Movie ID founded.");
	        return;
	    } 
	    throw new NoSuchMovieExceptionFound();
	    }

	public void clearAll() {
		movies.clear();
		saveMovies();
	}

	private String generateId(String name, String genre, int year) {
	    String cleanedName = name != null ? name.replaceAll("\\s+", "") : "";
	    String cleanedGenre = genre != null ? genre.replaceAll("\\s+", "") : "";
	    String namePart = cleanedName.length() >= 2 ? cleanedName.substring(0, 2) : cleanedName;
	    String genrePart = cleanedGenre.length() >= 2 ? cleanedGenre.substring(0, 2) : cleanedGenre;
	    String yearPart = String.valueOf(year);
	    yearPart = yearPart.length() >= 2 ? yearPart.substring(yearPart.length() - 2) : yearPart;

	    return namePart + genrePart + yearPart;
	}


	public void saveMovies() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(movies);
		} catch (IOException e) {
			System.out.println("Error saving to file: " + e.getMessage());
		}
	}

	private List<Movie> loadMovies() {
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			return new ArrayList<>();
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			return (List<Movie>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error loading from file: " + e.getMessage());
			return new ArrayList<>();
		}
	}
	
	private boolean isValidGenre(String genre) {
	    return genre != null && !genre.trim().isEmpty() && genre.matches(".*[a-zA-Z].*");
	}

	private boolean isValidYear(int year) {
	    return String.valueOf(year).matches("\\d{4}");
	}
	
	private void displayMovie(Movie movie) {
	    System.out.println("+----------------+----------------------+----------------+------+\n" +
	            "| ID             | Name                 | Genre          | Year |\n" +
	            "+----------------+----------------------+----------------+------+");
	    System.out.printf("| %-14s | %-20s | %-14s | %-4d |\n",
	            movie.getId(),
	            movie.getName(),
	            movie.getGenre(),
	            movie.getYear());
	    System.out.println("+----------------+----------------------+----------------+------+");
	}


}
