package mypackage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypackage.dao.UserDao;
import mypackage.model.User;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String MAIN_JSP = "/main.jsp";
    private static String LOGIN_FORM = "/login.jsp";
    private UserDao uDao;
	
    public LoginController() {
        super();
        uDao = new UserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "¬ход в админ-панель");
		request.setAttribute("content", LOGIN_FORM);

		
		RequestDispatcher view = request.getRequestDispatcher(MAIN_JSP);
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = uDao.getUser(username, password);
		
		if(user != null) {
			request.getSession().setAttribute("userInfo", user);
			request.getSession().setMaxInactiveInterval(1800);
			response.sendRedirect(request.getContextPath() + "/admin");
		} else {
			request.setAttribute("error", "Ћогин и\\или пароль не совпадают!");
			doGet(request, response);
		}
	}
}
