package com.tss.test;

import com.tss.exception.MovieStoreEmptyException;
import com.tss.exception.MovieStoreFullException;
import com.tss.exception.NoSuchMovieExceptionFound;
import com.tss.model.Movie;
import com.tss.service.MovieService;

import java.util.List;
import java.util.Scanner;

public class MovieApp {
	static Scanner scanner = new Scanner(System.in);
	static MovieService service = new MovieService();

	public static void main(String[] args) {
		int choice;
		boolean loop = true;

		while (loop) {

			choice = displayMenu();
			scanner.nextLine();
			switch (choice) {
			case 1:
				try {
					List<Movie> movies = service.getMovies();
					displayMovies(movies);
				} catch (MovieStoreEmptyException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2:
				try {
					addMovie();
				} catch (MovieStoreFullException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				try {
					deleteMovieById();
				} catch (MovieStoreEmptyException | NoSuchMovieExceptionFound e) {
					System.out.println(e.getMessage());
				}

				break;

			case 4:
				try {
					searchMovieById();
				} catch (MovieStoreEmptyException | NoSuchMovieExceptionFound e) {
					System.out.println(e.getMessage());
				}
				break;

			case 5:
				service.clearAll();
				System.out.println("Movie Store Cleared!!");
				break;

			case 6:
				System.out.println("Saving and exiting...");
				service.saveMovies();
				loop = false;
				break;

			default:
				System.out.println("Invalid Choice");
			}
		}
		scanner.close();
	}

	private static void searchMovieById() {
		System.out.println("Enter Id Of Movie You Want To Search: ");
		String id = scanner.nextLine();
		service.searchMovie(id);
	}

	private static void deleteMovieById() {
		System.out.println("Enter Id Of Movie You Want To Delete: ");
		String id = scanner.nextLine();
		service.deleteMovie(id);
	}

	private static void addMovie() {

		System.out.print("Enter movie name: ");
		String name = scanner.nextLine();

		System.out.print("Enter movie genre: ");
		String genre = scanner.nextLine();

		System.out.print("Enter movie year: ");
		int year = scanner.nextInt();
		scanner.nextLine();

		service.addMovie(name, genre, year);
		System.out.println("Movie added!");
	}

	private static void displayMovies(List<Movie> movies) {
		System.out.println("+----------------+------------------------------+----------------+----------+");
		System.out.printf("| %-14s | %-35s | %-10s | %-4s |\n", "ID", "Name", "Genre", "Year");
		System.out.println("+----------------+------------------------------+----------------+----------+");

		for (Movie movie : movies) {
			System.out.printf("| %-14s | %-35s | %-10s | %-4d |\n", movie.getId(), movie.getName(), movie.getGenre(),
					movie.getYear());
		}

		System.out.println("+----------------+------------------------------+----------------+-----------+");

	}

	private static int displayMenu() {
		System.out.println("+------------------------------+");
		System.out.println("|    Welcome To Movie Store    |");
		System.out.println("+------------------------------+");
		System.out.println("| 1. Display movies            |");
		System.out.println("| 2. Add movie                 |");
		System.out.println("| 3. Delete movie By ID        |");
		System.out.println("| 4. Search Movie By ID        |");
		System.out.println("| 5. Clear all                 |");
		System.out.println("| 6. Exit                      |");
		System.out.println("+------------------------------+");
		System.out.print("Enter Your Choice: ");
		return scanner.nextInt();
	}
}
