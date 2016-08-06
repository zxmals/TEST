package com.nuaa.ec.interceptor;

import java.util.Map;

import com.nuaa.ec.model.Teacher;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginCheckInterceptor implements Interceptor{
	
	public String intercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		String actionName = ai.getProxy().getActionName();
		Map<String, Object>session = ai.getInvocationContext().getSession();
		Teacher teacher = (Teacher)session.get("teacher");
		String str  = "error";
		System.out.println("actio方法之前---------------------------------------");
		if("login".equals(actionName)||teacher!=null)
			str = ai.invoke();
		System.out.println("action方法之后---------------------------------------");
		return str;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

}
