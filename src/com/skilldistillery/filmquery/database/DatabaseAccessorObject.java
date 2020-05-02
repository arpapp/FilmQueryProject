package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "select film.*, language.name from film join language on film.language_id = language.id where film.id = ?";
		PreparedStatement psql = conn.prepareStatement(sql);
		psql.setInt(1, filmId);
		ResultSet rs = psql.executeQuery();
		if (rs.next()) {
			film = new Film();
			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getInt("release_year"));
			film.setLanguage_id(rs.getInt("language_id"));
			film.setRentalDuration(rs.getInt("rental_duration"));
			film.setRentalRate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecialFeatures(rs.getString("special_features"));
			film.setLanguage(rs.getString("language.name"));
			film.setFilmActors(findActorsByFilmId(rs.getInt("id")));
		}
		else {
			System.out.println("No film found.");
		}
		rs.close();
		psql.close();
		conn.close();
		return film;
	}
	
	public List<Film> findFilmBySearch(String keyword) throws SQLException {
		Film film = null;
		List<Film> searchResult = new ArrayList<>();
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "select film.*, language.name from film join language on film.language_id = language.id where title like ? or description like ?";
		PreparedStatement psql = conn.prepareStatement(sql);
		psql.setString(1, "%" + keyword + "%");
		psql.setString(2, "%" + keyword + "%");
		ResultSet rs = psql.executeQuery();
		while (rs.next()) {
			film = new Film();
			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getInt("release_year"));
			film.setLanguage_id(rs.getInt("language_id"));
			film.setRentalDuration(rs.getInt("rental_duration"));
			film.setRentalRate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setLanguage(rs.getString("language.name"));
			film.setSpecialFeatures(rs.getString("special_features"));
			film.setFilmActors(findActorsByFilmId(rs.getInt("id")));
			searchResult.add(film);
		}
		rs.close();
		psql.close();
		conn.close();
		return searchResult;
	}


	@Override
	public List<Actor> findActorsByFilmId(int filmId){
		List<Actor> actorList = new ArrayList<>();
		Actor actor = null;
		try {
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT actor.first_name, actor.last_name, actor.id, film_actor.film_id\n" + "FROM actor\n"
				+ "JOIN film_actor\n" + "ON film_actor.actor_id = actor.id\n" + "JOIN film\n"
				+ "ON film.id = film_actor.film_id\n" + "WHERE film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet actorFilmResult = stmt.executeQuery();
		while (actorFilmResult.next()) {
			actor = new Actor();
			actor.setId(actorFilmResult.getInt("id"));
			actor.setFirstName(actorFilmResult.getString("first_name"));
			actor.setLastName(actorFilmResult.getString("last_name"));
			actorList.add(actor);
		}
		actorFilmResult.close();
		stmt.close();
		conn.close();
	}
		catch (SQLException E) {
			E.printStackTrace();
		}
		return actorList;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		// not implemented
		return null;
	}

}
