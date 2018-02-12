package com.apress.bookWeb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apress.bookWeb.dao.BookDAO;
import com.apress.bookWeb.dao.BookDAOImpl;
import com.apress.bookWeb.model.Category;

public class BookController extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException{
		
		super.init(config);
		
		BookDAO bookDAO = new BookDAOImpl();
		// callineg DAO mehotd to retrieve bookList from database
		List<Category> categoryList = bookDAO.findAllCategories();
		ServletContext context = config.getServletContext();
		context.setAttribute("categoryList", categoryList);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String base = "/jsp/";
		String url = base + "/home.jsp/";
		String action = request.getParameter("action");
		String category = request.getParameter("category");
		String keyWord = request.getParameter("keyWord");
		
		if(action != null) {
			switch(action) {
			case "allBooks":
				findAllBooks(request, response);
				url = base + "listOfBooks.jsp";
				break;
			case "category":
				findAllBooks(request, response);
				url = base + "category.jsp?category=" + category;				
				break;
			case "search":
				searchBooks(request, response, keyWord);
				url = base + "searchResult.jsp";
				break;
			}
		}
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}
	
	private void findAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void searchBooks(HttpServletRequest request, HttpServletResponse response, String keyWord) {
		
	}
	
}
