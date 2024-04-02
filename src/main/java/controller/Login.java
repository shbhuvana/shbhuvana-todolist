package controller;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.User;

@WebServlet("/user")
public class Login extends HttpServlet {
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			Dao dao = new Dao();
			
			try {
				User u = dao.findByEmail(email);
				if(u!=null) {
					//verify the password
					if(u.getUserpassword().equals(password)) {
						//login success
						req.setAttribute("user", u);
						req.getRequestDispatcher("home.jsp").include(req, resp);
					}else {
						//password wrong
						req.setAttribute("message", "password wrong");
						req.getRequestDispatcher("login.jsp").include(req, resp);
					}
				}else {
					// email is wrong
					req.setAttribute("message", "wrong email");
					req.getRequestDispatcher("login.jsp").include(req, resp);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		
	}



