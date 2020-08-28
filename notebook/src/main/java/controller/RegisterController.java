package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.NormalUser;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/registration.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		int status = 0;
		try {
			List<NormalUser> list = UserDAO.allNormalUser();
			for (NormalUser user : list) {
				if (user.getName().equals(username) || user.getPassword().equals(password)) {
					status = 1;
					break;
				}
			}

			if (status == 1) {
				resp.sendRedirect(req.getContextPath() + "/register?error=0");
			} else {
				UserDAO.insertNormalUser(username, password);
				RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
				rd.forward(req, resp);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
