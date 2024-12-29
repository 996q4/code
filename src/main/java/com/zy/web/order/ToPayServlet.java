package com.zy.web.order;

import com.zy.pojo.Category;

import com.zy.pojo.Order;
import com.zy.service.CategoryService;
import com.zy.service.OrderService;
import com.zy.service.impl.CategoryServiceImpl;
import com.zy.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/toPayServlet")
public class ToPayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取地址aid
        //获取订单号oid
        //通过oid查找订单对象
        //给该订单添加（update）aid
        //并设置is_pay为1
        String aid = request.getParameter("aid");
        String oid = request.getParameter("oid");

        OrderService service = new OrderServiceImpl();
        Order order = service.findOrderById(oid);


        int addressid = Integer.valueOf(aid);
        service.updateOrderAddress(oid,addressid);
        service.updateOrderIsPay(oid,"1");

        CategoryService service1 = new CategoryServiceImpl();
        List<Category> flist = service1.findCategoryListByName("father");
        List<Category> clist = service1.findCategoryListByName("child");
        request.setAttribute("flist",flist);
        request.setAttribute("clist",clist);


        response.sendRedirect("ok.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
