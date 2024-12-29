package com.zy.web.user;

import com.zy.pojo.User;
import com.zy.service.UserService;
import com.zy.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        // 创建文件项目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置文件上传限制
        upload.setFileSizeMax(1024 * 1024 * 10); // 10MB

        // 解析请求的参数
        String userName = null;
        String nickname = null;
        String passWord = null;
        String rePassWord = null;
        String sex = null;
        String status = null;
        String fileName = "";

        try {
            // 解析请求
            List<FileItem> items = upload.parseRequest(request);

            // 处理上传的文件和表单字段
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // 处理普通表单字段
                    String fieldName = item.getFieldName();
                    String value = item.getString("UTF-8");

                    switch (fieldName) {
                        case "userName":
                            userName = value;
                            break;
                        case "nickname":
                            nickname = value;
                            break;
                        case "passWord":
                            passWord = value;
                            break;
                        case "rePassWord":
                            rePassWord = value;
                            break;
                        case "sex":
                            sex = value;
                            break;
                        case "status":
                            status = value;
                            break;
                    }
                } else {
                    // 处理上传的文件
                    String fileExtension = item.getName().substring(item.getName().lastIndexOf(".") + 1);
                    // 验证文件类型
                    if (!isAllowedFileType(fileExtension)) {
                        throw new ServletException("不允许的文件类型");
                    }
                    fileName = userName + "." + fileExtension;

                    // 确保上传目录存在
                    File uploadDir = new File(request.getServletContext().getRealPath("/upload/user"));
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    // 保存文件
                    File file = new File(uploadDir, fileName);
                    item.write(file);
                }
            }

            // 创建用户对象并保存
            User u = new User(userName, nickname, passWord, sex, "0", "0", status, fileName);
            UserService service = new UserServiceImpl();
            Boolean flag = service.addUser(u);

            if (flag) {
                response.sendRedirect(request.getContextPath() + "/userListServlet");
            } else {
                PrintWriter out = response.getWriter();
                out.write("<script>");
                out.write("alert('新增用户失败！');");
                out.write("</script>");
                out.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("<script>alert('添加失败！');</script>");
        }
    }

    private boolean isAllowedFileType(String fileExtension) {
        String[] allowedTypes = {"jpg", "gif", "jpeg", "png"};
        for (String type : allowedTypes) {
            if (type.equalsIgnoreCase(fileExtension)) {
                return true;
            }
        }
        return false;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}