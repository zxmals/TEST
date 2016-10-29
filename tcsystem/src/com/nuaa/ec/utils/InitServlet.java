package com.nuaa.ec.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.nuaa.ec.dao.TfdegreeThesisGuidanceRewardLevelDAO;
import com.nuaa.ec.dao.TfteachingCompetitionRewardLevelDAO;


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
		ServletContext context=this.getServletContext();
		List<Integer> pageSizeList=new ArrayList<Integer>();
		Integer[] pageSizeArray=new Integer[]{1,2,5,10,20};
		Collections.addAll(pageSizeList, pageSizeArray);
		context.setAttribute("pageSizeList", pageSizeList);
		System.out.println("pageSize列表初始化成功...");
		/**
		 * 获得论文奖励水平的种类，到时候如果有变动在这里改
		 */
		context.setAttribute("degreeGuidanceRewardLevels", new TfdegreeThesisGuidanceRewardLevelDAO().findAll());
		context.setAttribute("teachingCompetitionRewardLevelList", new TfteachingCompetitionRewardLevelDAO().findAll());
		
	}

}
