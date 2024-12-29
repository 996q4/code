package com.zy.web.category;

import com.zy.pojo.Category;

import com.zy.service.CategoryService;
import com.zy.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delCategoryByIdServlet")
public class DelCategoryByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("id");
        int cid2 = Integer.valueOf(cid);

        CategoryService service = new CategoryServiceImpl();
        service.delCategoryById(cid2);

        request.getRequestDispatcher("/categoryListServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
