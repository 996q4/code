package com.zy.web.user;

import com.zy.pojo.User;
import com.zy.service.UserService;
import com.zy.service.impl.UserServiceImpl;
import com.zy.utils.UploadUtil;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/updateUserPhotoServlet")
public class UpdateUserPhotoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("name");

        if (user != null) {
            try {
                // 使用UploadUtil处理上传
                Map<String, String> resultMap = UploadUtil.uploadFile(
                        request,
                        "user",  // uploadType
                        String.valueOf(user.getUser_id())  // newFileName
                );

                String fileName = resultMap.get("fileName");
                if (!fileName.isEmpty()) {
                    // 更新数据库
                    UserService service = new UserServiceImpl();
                    service.updatePhotoById(user.getUser_id(), fileName);

                    response.sendRedirect("toMyInfoServlet");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("<script>alert('更新失败！');</script>");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}