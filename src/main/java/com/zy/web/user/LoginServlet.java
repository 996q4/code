package com.zy.web.user;

import com.zy.pojo.User;
import com.zy.service.UserService;
import com.zy.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		UserService service = new UserServiceImpl();
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		
		User user = service.loginByNameAndPassword(userName, passWord);
		
		if(user != null && "1".equals(user.getUser_status())) {
			HttpSession session = request.getSession();
			
			session.setAttribute("name", user);
			session.setAttribute("isLogin", "1");
			
			response.sendRedirect("indexServlet");
			
		}else{
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("alert('用户登录失败！');");
			out.write("location.href='login.jsp'");
			out.write("</script>");
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}
