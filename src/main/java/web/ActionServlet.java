package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;


public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(
			HttpServletRequest request, 
			HttpServletResponse response) 
		throws ServletException, IOException {
		//处理中文参数值
		request.setCharacterEncoding("UTF-8");
		//获得请求资源路径
		String uri=request.getRequestURI();
		//分析请求资源路径
		String action=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		System.out.println("action:"+action);
		if("/login".equals(action)) {
			/**
			 * 先比较验证码是否正确，如果验证码不正确，不再比较用户名和密码
			 * 
			 */
			//用户提交的验证码
			String number=request.getParameter("number");
			//session对象上事先绑定的验证码
			HttpSession session=request.getSession();
			String number2=(String)session.getAttribute("number");
			if(!number.equals(number2)) {
				request.setAttribute("number_error", "验证码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			//读取用户名和密码
			String username=request.getParameter("username");
			String pwd=request.getParameter("pwd");
			System.out.println("username:"+username+"pwd:"+pwd);
			//查询数据库
			UserDAO dao=new UserDAO();
			try {
				User user=dao.findByUsername(username);
				if(user!=null&&user.getPassword().equals(pwd)) {
					//登陆成功
					session.setAttribute("user", user);
					
					response.sendRedirect("index.jsp");
				}else {
					//登录失败
					request.setAttribute("login_failed","用户名或密码错误");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
			
		}
	}

}
