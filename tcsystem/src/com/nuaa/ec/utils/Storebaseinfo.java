package com.nuaa.ec.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TftermDAO;

public class Storebaseinfo implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(arg0, arg1);
	}

	@SuppressWarnings("unchecked")
	public void init(FilterConfig arg0) throws ServletException {
		StoreData.setTeachertranslate(new TeacherDAO().findAllT());
		StoreData.setResearchLabList(new ResearchLabDAO().findAll());
		StoreData.setTftermList(new TftermDAO().findAll());
		StoreData.setDepartmentList(new DepartmentDAO().findAll());
	}

}
