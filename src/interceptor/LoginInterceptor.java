package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import entity.BackendUser;
import entity.DevUser;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
//		DevUser devUser =(DevUser) request.getSession().getAttribute("devUserSession");
//		BackendUser backendUser =(BackendUser) request.getSession().getAttribute("userSession");
//		if(devUser ==null || backendUser==null) {
//			request.getRequestDispatcher("403.jsp").forward(request, arg1);
//			return false;
//		}
		return true;
	}

}
