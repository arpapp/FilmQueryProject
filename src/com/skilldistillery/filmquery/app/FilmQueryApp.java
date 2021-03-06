package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();

		try {
			app.launch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {

		System.out.println("Welocme to Film Fiends!");
		System.out.println();

		boolean menu = true;

		while (menu) {
			System.out.println("What would you like to do?");
			System.out.println("1. Look up a film by its ID");
			System.out.println("2. Look up a film by a search keyword");
			System.out.println("3. Exit the application");
			System.out.println();
			System.out.print("Please enter your selection(1-3): ");
			int selection = input.nextInt();
			switch (selection) {
			case 1:
				System.out.print("Please enter the film ID: ");
				int filmID = input.nextInt();
				Film result = (db.findFilmById(filmID));
				if (result != null) {
					result.displayInfo();
					System.out.println();
					boolean submenu = true;
					while (submenu) {
						System.out
								.println("Would you like to (1) view all film details or (2) return to the main menu?");
						System.out.println("Please enter your selection(1 or 2): ");
						int selection2 = input.nextInt();
						if (selection2 == 1) {
							result.displayMoreInfo();
						} else if (selection2 == 2) {
							submenu = false;
						} else {
							System.out.println("Invalid selection.");
						}
					}
				}
				break;
			case 2:
				System.out.print("Please enter your search keyword: ");
				String keyword = input.next();
				List<Film> results = db.findFilmBySearch(keyword);

				if (results.size() != 0) {
					for (Film film : results) {
						System.out.println();
						film.displayInfo();
						System.out.println();
					}
				} else {
					System.out.println("No results matching your keyword.");
				}

				break;
			case 3:
				System.out.println("Thank you for using Film Fiends!");
				menu = false;
				break;
			default:
				System.out.println("Invalid selection.");
			}

		}

	}

}
