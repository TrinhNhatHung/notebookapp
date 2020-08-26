package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserDAO;
import model.Note;

@WebServlet(urlPatterns = "/home")
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String key;
		ResultSet resultSet =null;
		List<Note> list = new ArrayList<Note>();
		try {
			if (session.getAttribute("kind").equals(1)) {
				key = (String) session.getAttribute("username");		
				resultSet = UserDAO.querryNormalUserNote(key);			
			} else {
				key = (String) session.getAttribute("email");
				resultSet = UserDAO.querryEmailUserNote(key);
			}
			while (resultSet.next()) {
				Note note = new Note();
				note.setId(resultSet.getInt("id"));
				note.setTitle(resultSet.getString("title"));
				note.setContent(resultSet.getString("content"));
				note.setTimeNote(resultSet.getDate("notetime"));
				note.setDeleted(resultSet.getBoolean("isDeleted"));
				list.add(note);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		String jSonOfList = mapper.writeValueAsString(list);
		req.setAttribute("jSonOfList",jSonOfList);
		RequestDispatcher rd = req.getRequestDispatcher("/views/index.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	

}
