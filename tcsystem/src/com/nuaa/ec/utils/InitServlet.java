package com.nuaa.ec.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


@SuppressWarnings("serial")
public class InitServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		super.init();
		List<Integer> pageSizeList=new ArrayList<Integer>();
		Integer[] pageSizeArray=new Integer[]{1,2,5,10,20};
		Collections.addAll(pageSizeList, pageSizeArray);
		this.getServletContext().setAttribute("pageSizeList", pageSizeList);
		System.out.println("pageSize列表初始化成功...");
	}

}
