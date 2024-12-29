package com.zy.web.order;

import com.zy.pojo.*;
import com.zy.service.*;
import com.zy.service.impl.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.zy.pojo.OrderList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/orderselect")
public class OrderSelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("name");

        if (user != null) {
            // 获取分类数据
            CategoryService categoryService = new CategoryServiceImpl();
            List<Category> flist = categoryService.findCategoryListByName("father");
            List<Category> clist = categoryService.findCategoryListByName("child");
            request.setAttribute("flist", flist);
            request.setAttribute("clist", clist);

            String eids = request.getParameter("eids");
            if (eids != null && !eids.isEmpty()) {
                // 1. 获取选中的购物车项
                String[] cartIds = eids.split(",");
                CartService cartService = new CartServiceImpl();
                List<Cart> cartList = new ArrayList<>();
                double totalAmount = 0.0;

                for (String cartId : cartIds) {
                    Cart cart = cartService.findCartById(Integer.parseInt(cartId));
                    if (cart != null) {
                        cartList.add(cart);
                        totalAmount += cart.getProduct_price() * cart.getProduct_quantity();
                    }
                }

                // 2. 生成订单号
                String orderId = generateOrderId();

                // 3. 创建订单对象
                Order order = new Order();
                order.setOrder_id(orderId);
                order.setUser_id(user.getUser_id());
                order.setOrder_price((int) totalAmount);
                order.setOrder_time(new Date());
                order.setIs_pay("0");  // 未支付

                // 4. 保存订单
                OrderService orderService = new OrderServiceImpl();
                orderService.addOrder(order);

                // 5. 保存订单项
                OrderListService orderListService = new OrderListServiceImpl();
                for (Cart cart : cartList) {
                    OrderList orderList = new OrderList();
                    orderList.setOrder_id(orderId);
                    orderList.setProduct_id(cart.getProduct_id());
                    orderList.setProduct_quantity(cart.getProduct_quantity());
                    orderListService.addOrderList(orderList);
                }

                // 6. 获取商品详细信息
                List<Product> productList = new ArrayList<>();
                ProductService productService = new ProductServiceImpl();
                for (Cart cart : cartList) {
                    Product product = productService.findProductById(cart.getProduct_id());
                    if (product != null) {
                        productList.add(product);
                    }
                }

                // 7. 设置请求属性
                request.setAttribute("order", order);
                request.setAttribute("cartList", cartList);  // 改为cartList
                request.setAttribute("productList", productList);
                request.setAttribute("totalPrice", totalAmount);

                // 8. 转发到订单确认页面
                request.getRequestDispatcher("order.jsp").forward(request, response);
            } else {
                response.getWriter().write("<script>alert('请选择要结算的商品！');history.back();</script>");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private String generateOrderId() {
        // 生成更短的订单号：当前时间毫秒数后6位 + 3位随机数
        String timestamp = String.valueOf(System.currentTimeMillis());
        String lastSix = timestamp.substring(timestamp.length() - 6);
        String randomNum = String.format("%03d", new Random().nextInt(1000));
        return "OD" + lastSix + randomNum;
    }
}