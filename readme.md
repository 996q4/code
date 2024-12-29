我已经接收到了所有前端JSP文件。这些文件看起来是一个家居电子商城系统的前端页面,主要包含以下功能模块:
用户相关:
登录(login.jsp)
注册(reg.jsp)
忘记密码(forget.jsp)
个人信息管理(mygrxx.jsp)
个人中心(mygxin.jsp)
商品相关:
首页(index.jsp)
商品列表(productlist.jsp)
商品详情(productview.jsp)
订单相关:
购物车(cart.jsp)
订单确认(order.jsp)
订单列表(myorderlist.jsp)
订单详情(orderxq.jsp)
订单评价(myprod.jsp)
公共组件:
页头导航(header.jsp)
操作成功提示(ok.jsp)
这些页面使用了以下主要技术:
JSP + JSTL标签库
jQuery JavaScript库
CSS样式(public.css等)
图片资源(img目录)


1. 地址管理相关(Address):
AddAddressServlet: 添加收货地址
DelAddressServlet: 删除收货地址
ToMyAddressListServlet: 查看地址列表
购物车相关(Cart):
AddCartServlet: 添加商品到购物车
ToCartServlet: 查看购物车
Filter: 购物车过滤器
3. 商品分类相关(Category):
AddCategoryFatherServlet: 添加父级分类
AddCategoryServlet: 添加子级分类
CategoryListServlet: 查看分类列表
DelCategoryByIdServlet: 删除分类
ToAddCategoryServlet: 跳转到添加分类页面
ToUpdateCategoryServlet: 跳转到更新分类页面
UpdateCategoryServlet: 更新分类信息

评论相关(Comment):（已删除）
AddCommentServlet: 添加商品评论
ToMyCommentListServlet: 查看我的评论列表
订单相关(Order):
DelOrderServlet: 删除订单
OrderDetailServlet: 查看订单详情
OrderListByKeyServlet: 按关键字搜索订单
ToAllOrderListServlet: 查看所有订单(带分页)
ToEnOrderIsReceiptServlet: 确认收货
ToMyOrderListServlet: 查看我的订单
ToOrderServlet: 生成订单
ToPayServlet: 支付订单
UpdateOrderIsShipServlet: 更新发货状态
首页相关(Home):
IndexServlet: 首页展示,包含:
商品分类列表
最新商品列表
销量排行商品列表
购物车数量显示

产品相关Servlet
产品管理基础操作
AddProductServlet: 添加新产品
UpdateProductServlet: 更新产品信息
DelProductServlet: 删除产品
产品列表和查询
ProductListServlet: 显示产品列表(带分页)
ProductListByCategoryServlet: 按分类显示产品
SearchProductServlet: 搜索产品
SelectProductList: 选择产品列表
产品页面跳转
ToAddProductServlet: 跳转到添加产品页面
ToProductViewServlet: 跳转到产品详情页
ToUpdateProductServlet: 跳转到更新产品页面
用户相关Servlet
用户认证
LoginServlet: 用户登录
LoginAdminServlet: 管理员登录
LogoutServlet: 用户退出
LogoutAdminServlet: 管理员退出
RegServlet: 用户注册
CheckCodeServlet: 生成验证码
用户管理基础操作
AddUserServlet: 添加用户
DelUserByIdServlet: 删除用户
UpdateUserServlet: 更新用户基本信息
UpdateUserListServlet: 批量更新用户
UpdateUserPhotoServlet: 更新用户头像
UpdateUserPasswordServlet: 更新用户密码
UpdateUserInfoServlet: 更新用户详细信息
用户列表和查询
UserListServlet: 显示用户列表(带分页)
UserListByKeyServlet: 按关键字搜索用户
用户页面跳转
ToUpdateUserListServlet: 跳转到用户列表更新页面
ToMyInfoServlet: 跳转到个人信息页面
ToUserInfoServlet: 跳转到用户信息页面