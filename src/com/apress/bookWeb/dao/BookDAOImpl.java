package com.apress.bookWeb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.apress.bookWeb.model.Author;
import com.apress.bookWeb.model.Book;
import com.apress.bookWeb.model.Category;
import com.mysql.jdbc.PreparedStatement;

public class BookDAOImpl implements BookDAO{
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException ex) {
			// TODO: handle finally clause
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "#Madeleine0304");
	}
	
	private void closeConnection(Connection connection) {
		if(connection == null) {
			try {
				connection.close();
			} catch (SQLException ex) {
				// TODO: handle exception
			}
		}
	}
	
	@Override
	public List<Book> findAllBooks() {
		List<Book> result = new ArrayList<>();
		List<Author> authorList = new ArrayList<>();
		
		String sql = "slect * from bokk inner join author on book.id = author.book_id";
		
		Connection connection = null;
		
		try {
			connection = getConnection();
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				Author author = new Author();
				book.setId(resultSet.getLong("id"));
				book.setBookTitle(resultSet.getString("book_title"));
				book.setCategoryId(resultSet.getLong("category_id"));
				author.setBookId(resultSet.getLong("book_id"));
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));
				authorList.add(author);
				book.setAuthors(authorList);
				book.setPublisherName(resultSet.getString("publisher"));
				result.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		
		return result;
	}

	@Override
	public List<Book> searchBooksByKeyword(String keyWord) {
		List<Book> result = new ArrayList<>();
		List<Author> authorList = new ArrayList<>();
		
		String sql = "select * from book inner join author on book.id = author.book_id "
				+ "where book_title like'%"
				+keyWord.trim()+"%' or first_name like '%"
				+keyWord.trim()+"%' or last_name like '%"
				+keyWord.trim()+"%'";
		
		Connection connection = null;
		
		try {
			connection = getConnection();
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Book  book = new Book();
				Author author = new Author();
				
				book.setId(resultSet.getLong("id"));
				book.setBookTitle(resultSet.getString("book_title"));
				book.setPublisherName(resultSet.getString("publisher"));
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));
				author.setBookId(resultSet.getLong("book_id"));
				authorList.add(author);
				book.setAuthors(authorList);
				result.add(book);	
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		
		return result;
	}

	@Override
	public List<Category> findAllCategories() {
		List<Category> result = new ArrayList<>();
		
		String sql = "select * from category";
		
		Connection connection = null;
		
		try {
			connection = getConnection();
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getLong("id"));
				category.setCategoryDescription(resultSet.getString("category_description"));
				result.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	@Override
	public void insert(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long bookId) {
		// TODO Auto-generated method stub
		
	}

}
