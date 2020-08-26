package api;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserDAO;
import model.Note;
import utils.HttpUtils;

@WebServlet(urlPatterns = "/api-home-bin")
public class HomeBin extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3288912305833150188L;
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		Note note = HttpUtils.toModel(Note.class, req.getReader());
		try {
			int affectedRow = UserDAO.deleteBin(note);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(resp.getOutputStream(), affectedRow);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
}
