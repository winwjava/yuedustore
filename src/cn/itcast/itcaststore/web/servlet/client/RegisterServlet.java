package cn.itcast.itcaststore.web.servlet.client;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.RegisterException;
import cn.itcast.itcaststore.service.UserService;
import cn.itcast.itcaststore.utils.ActiveCodeUtils;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将表单提交的数据封装到javaBean
		//封装注册参数到user
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			//封装激活参数，通过工具类得到激活参数。
			// 封裝激活码
			user.setActiveCode(ActiveCodeUtils.createActiveCode());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// 调用UserService的注册方法，完成注册操作。
		UserService service = new UserService();
		try {
			service.register(user);
			//注册成功
		} catch (RegisterException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());//注册失败了！！！
			return;
		}
		// 注册成功，跳转到成功页面registersuccess.jsp
		response.sendRedirect(request.getContextPath() + "/client/registersuccess.jsp");
		return;
	}
}
