package phuocthanh.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import phuocthanh.models.UserModel;
import phuocthanh.services.IUserService;
import phuocthanh.services.impl.UserService;
import phuocthanh.untils.Email;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IUserService userService = new UserService();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();

		if (url.contains("register")) {
			getRegister(req, resp);
		} else if (url.contains("login")) {
			getLogin(req, resp);
		} else if (url.contains("forgotpass")) {
			req.getRequestDispatcher("views/web/forgotpassword.jsp").forward(req, resp);
		} else if (url.contains("waiting")) {
			getWaiting(req, resp);
		} else if (url.contains("VertifyCode")) {
			req.getRequestDispatcher("/views/web/verify.jsp").forward(req, resp);
		} else {
			homePage(req, resp);
		}

		req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("register")) {
			postRegister(req, resp);
		} else if (url.contains("login")) {
			postLogin(req, resp);
		} else if (url.contains("forgotpass")) {
			postForgotPassword(req, resp);
		} else if (url.contains("VerifyCode")) {
			postVerifyCode(req, resp);
		}
	}

	private void postVerifyCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = resp.getWriter()) {
			HttpSession session = req.getSession();
			UserModel user = (UserModel) session.getAttribute("account");
			String code = req.getParameter("authcode");

			if (code.equals(user.getCode())) {
				user.setEmail(user.getEmail());
				user.setStatus(1);

				userService.updatestatus(user);

				out.println("<div class=\"container\"><br/>\r\n" + "       <br/>\r\n"
						+ "     <br/>Kich hoat tai khoan thanh cong!<br/>\r\n" + "     <br/>\r\n" + "     <br/></div>");
			} else {
				out.println("<div class=\"container\"><br/>\r\n" + "       <br/>\r\n"
						+ "     <br/>Sai ma kich hoat, vui long kiem tra lai!<br/>\r\n" + "     <br/>\r\n"
						+ "     <br/></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void postForgotPassword(HttpServletRequest req, HttpServletResponse resp) {

	}

	private void postLogin(HttpServletRequest req, HttpServletResponse resp) {

	}

	private void postRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");

		String alertMsg = "";
		if (userService.checkExistEmail(email)) {
			alertMsg = "email da ton tai";
			req.setAttribute("error", alertMsg);
			req.getRequestDispatcher("/views/web/register.jsp").forward(req, resp);
		} else if (userService.checkExistUsername(username)) {
			alertMsg = "Tai khoan da ton tai!";
			req.setAttribute("error", alertMsg);
			req.getRequestDispatcher("/views/web/register.jsp").forward(req, resp);
		} else {
			Email sm = new Email();
			String code = sm.getRandom();

			UserModel user = new UserModel(username, fullname, email, code);
			boolean test = sm.sendEmail(user);
			if (test) {
				HttpSession session = req.getSession();
				session.setAttribute("account", user);

				boolean isSuccess = userService.register(email, password, username, fullname, code);
				if (isSuccess) {
					resp.sendRedirect(req.getContextPath() + "/VerifyCode");
				} else {
					alertMsg = "Loi he thong!";
					req.setAttribute("error", alertMsg);
					req.getRequestDispatcher("/views/web/register.jsp").forward(req, resp);
				}
			} else {
				PrintWriter out = resp.getWriter();
				out.println("Loi khi gui mail!!!!");
			}
		}
	}

	protected void homePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/web/home.jsp").forward(req, resp);
	}

	private void getRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/web/register.jsp").forward(req, resp);
	}

	private void getWaiting(HttpServletRequest req, HttpServletResponse resp) {

	}

	private void getLogin(HttpServletRequest req, HttpServletResponse resp) {

	}

}
