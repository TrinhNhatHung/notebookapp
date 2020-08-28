package api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserDAO;
import model.EmailUser;
import utils.HttpUtils;

@WebServlet(urlPatterns = "/api-login-email")
public class LoginEmail extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5779117996730342193L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		EmailUser requestUser = HttpUtils.toModel(EmailUser.class, req.getReader()) ;
		List<EmailUser> list = new ArrayList<EmailUser> ();
		try {
			list= UserDAO.allEmailUser();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		int status =0;
		int id = 0;
		for (EmailUser x : list) {
			if (x.getEmail().equals(requestUser.getEmail())) {
				status =1;
				id = x.getId();
				break;
			}
		}
		if (status ==0) {
			id = UserDAO.insertEmailUser(requestUser);
		} 
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resp.getOutputStream(), id);
	}
}
