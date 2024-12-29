package com.zy.web.user;



import com.zy.pojo.User;
import com.zy.service.UserService;
import com.zy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListByKeyServlet")
public class UserListByKeyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keywords = request.getParameter("keywords");

        UserService service = new UserServiceImpl();
        List<User> userList =  service.findUserListByKey(keywords);
        request.setAttribute("userList",userList);

        request.getRequestDispatcher("admin_user.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
