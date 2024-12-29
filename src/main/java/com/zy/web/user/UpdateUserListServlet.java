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

@WebServlet("/updateUserListServlet")
public class UpdateUserListServlet extends HttpServlet {
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
        String fileName = "";
        int userId = 0;
        String nickname = null;
        String username = null;
        String passWord = null;
        String sex = null;

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
                        case "userId":
                            userId = Integer.parseInt(value);
                            break;
                        case "nickName":
                            nickname = value;
                            break;
                        case "name":
                            username = value;
                            break;
                        case "passWord":
                            passWord = value;
                            break;
                        case "sex":
                            sex = value;
                            break;
                    }
                } else {
                    // 处理上传的文件
                    if (item.getSize() > 0) {
                        String fileExtension = item.getName().substring(item.getName().lastIndexOf(".") + 1);
                        // 验证文件类型
                        if (!isAllowedFileType(fileExtension)) {
                            throw new ServletException("不允许的文件类型");
                        }
                        fileName = userId + "." + fileExtension;

                        // 确保上传目录存在
                        File uploadDir = new File(request.getServletContext().getRealPath("/upload/user"));
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }

                        // 保存文件
                        File file = new File(uploadDir, fileName);
                        item.write(file);
                    } else {
                        // 如果没有上传新文件，获取原有文件名
                        UserService service = new UserServiceImpl();
                        User u = service.findUserByUserId(userId);
                        fileName = u.getUser_photo();
                    }
                }
            }

            // 创建用户对象并更新
            User user = new User(username, nickname, passWord, sex, "0", "0", "1", fileName);
            user.setUser_id(userId);

            UserService service = new UserServiceImpl();
            service.updateUserById(user);

            // 返回成功信息
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('更新成功！');");
            out.write("location.href='/HOMEECMS/userListServlet'");
            out.write("</script>");
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("<script>alert('更新失败！');</script>");
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