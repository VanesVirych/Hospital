package mypackage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mypackage.model.User;
import mypackage.util.DbConnect;

public class UserDao {
	private Connection connection;
	private String sql;

	public UserDao() {
		super();
		connection = DbConnect.getConnection();
	}
	public User getUser(String username, String password) {
		User user = null;
		sql = "SELECT `username`, `password` " +
				"FROM `users` " +
				"WHERE `username`=? AND `password`=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				user = new User();
				user.setUserName(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
