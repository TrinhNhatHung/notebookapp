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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserDAO;
import model.NormalUser;
import utils.HttpUtils;

@WebServlet(urlPatterns = "/api-login-normal")
public class LoginNormal extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -218018967433580229L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<NormalUser> list = new ArrayList<NormalUser>();
			list = UserDAO.allNormalUser();
			NormalUser requestUser = new NormalUser();
			requestUser = HttpUtils.toModel(NormalUser.class, req.getReader());
			int status = 0;
			int id=1;
			for (NormalUser x : list) {
				if (requestUser.getName().equals(x.getName())) {
					if (requestUser.getPassword().equals(x.getPassword())) {
						status = 2;
						id =x.getId();
					} else {
						status = 1;
					}
					break;
				}
			}
			String message;
			if (status == 0) {
				message = "Không tồn tại username";
				mapper.writeValue(resp.getOutputStream(), message);
			} else if (status == 1) {
				message = "Sai mật khẩu";
				mapper.writeValue(resp.getOutputStream(), message);
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("kind",1);
				session.setAttribute("id", id);
				session.setAttribute("username", requestUser.getName());
				session.setAttribute("image", "");
				message = "http://localhost:8080/notebook/home";
				mapper.writeValue(resp.getOutputStream(), message);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
