package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserDAO;
import model.Note;
import utils.HttpUtils;

@WebServlet(urlPatterns = "/api-home-note")
public class HomeNotebook extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4694898228806724719L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		StringBuilder builder = new StringBuilder();
		String readLine = new String();
		BufferedReader reader = req.getReader();
		while ((readLine = reader.readLine()) != null) {
			builder.append(readLine);
		}

		String jSon = builder.toString();
		String[] info = jSon.split("-");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy/MM/dd"));
		Note note = mapper.readValue(info[0], Note.class);
		int idUser = Integer.parseInt(info[2]);
		int kind = Integer.parseInt(info[1]);
		int idInserted = 0;
		try {
			if (kind == 1) {
				idInserted = UserDAO.insertNoteOfNormalUser(0, idUser, note);
			} else {
				idInserted = UserDAO.insertNoteOfEmailUser(0, idUser, note);
			}
			mapper.writeValue(resp.getOutputStream(), idInserted);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		Note note = HttpUtils.toModel(Note.class, req.getReader());
		try {
			int affectedRow = UserDAO.deleteNote(note);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(resp.getOutputStream(), affectedRow);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		Note note = HttpUtils.toModel(Note.class, req.getReader());
		try {
			int affectedRow = UserDAO.updateNote(note);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(resp.getOutputStream(), affectedRow);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
