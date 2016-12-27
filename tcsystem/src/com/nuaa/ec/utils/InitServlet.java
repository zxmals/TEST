package com.nuaa.ec.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.struts2.ServletActionContext;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TfclassTeachEvaluationDAO;
import com.nuaa.ec.dao.TfclassTeachTimeDAO;
import com.nuaa.ec.dao.TfdegreeThesisGuidanceRewardLevelDAO;
import com.nuaa.ec.dao.TfoffCampusPracticeGuidanceLevelDAO;
import com.nuaa.ec.dao.TfpracticeInnovationGuideGraduationThesisGuideEvalutionDAO;
import com.nuaa.ec.dao.TfpracticeInnovationGuideLevelDAO;
import com.nuaa.ec.dao.TfstudentCompetitionGuidanceCompetitionTypeDAO;
import com.nuaa.ec.dao.TfstudentCompetitionGuidanceRewardLevelDAO;
import com.nuaa.ec.dao.TfsummerCourseInternationalConstructionLevelDAO;
import com.nuaa.ec.dao.TfteachingAbilityImproveLevelDAO;
import com.nuaa.ec.dao.TfteachingCompetitionRewardLevelDAO;
import com.nuaa.ec.dao.TftermDAO;

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
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		super.init();
		ServletContext context = this.getServletContext();
		List<Integer> pageSizeList = new ArrayList<Integer>();
		Integer[] pageSizeArray = new Integer[] { 1, 2, 5, 10, 20 };
		Collections.addAll(pageSizeList, pageSizeArray);
		context.setAttribute("pageSizeList", pageSizeList);
		System.out.println("pageSize列表初始化成功...");
		/**
		 * 获得论文奖励水平的种类，到时候如果有变动在这里改
		 */
		context.setAttribute("degreeGuidanceRewardLevels",
				new TfdegreeThesisGuidanceRewardLevelDAO().findAll());
		/**
		 * 获得教学竞赛的水平
		 */
		context.setAttribute("teachingCompetitionRewardLevelList",
				new TfteachingCompetitionRewardLevelDAO().findAll());
		/**
		 * 获得教学能力提高的水平
		 */
		context.setAttribute("teachingAbilityImproveLevelList",
				new TfteachingAbilityImproveLevelDAO().findAll());
		/**
		 * 获得暑期课程与国际课程的level信息， 尽管数据库中只有一条level但是为了 避免日后可能会有改动。所以还是用 列表存放起来
		 */
		context.setAttribute("summerAndInternationCourseLevelList",
				new TfsummerCourseInternationalConstructionLevelDAO().findAll());
		/**
		 * 获得实践创新指导绩效中的论文指导级别
		 */
		context.setAttribute(
				"practiceInnovationGuideGraduationThesisGuideEvalutionList",
				new TfpracticeInnovationGuideGraduationThesisGuideEvalutionDAO()
						.findAll());
		/**
		 * 获得实践创新指导绩效中的指导级别
		 */
		context.setAttribute("practiceInnovationGuideLevelList", new TfpracticeInnovationGuideLevelDAO().findAll());
		/**
		 * 获得学生竞赛指导绩效有关信息
		 * 1：竞赛级别的信息
		 * 2：奖励级别的信息 
		 */
		context.setAttribute("studentCompetitionGuidanceCompetitionTypeList", new TfstudentCompetitionGuidanceCompetitionTypeDAO().findAll());
		context.setAttribute("studentCompetitionGuidanceRewardLevelList", new TfstudentCompetitionGuidanceRewardLevelDAO().findAll());
		/*
		 * 获得校外实践指导绩效指导的级别（类型）信息
		 */
		context.setAttribute("tfoffCampusPracticeGuidanceLevelList", new TfoffCampusPracticeGuidanceLevelDAO().findAll());
		/*
		 * 获得课堂教学时间表的有关信息
		 */
		context.setAttribute("classTeachTimeList", new TfclassTeachTimeDAO().findAll());
		/*
		 * 获得课堂教学绩效评估表的有关信息
		 */
		context.setAttribute("classTeachEvaluationList", new TfclassTeachEvaluationDAO().findAll());
		/*
		 * 审核状态
		 */
		Map<String,Object> auditStatus=new TreeMap<String,Object>();
//		auditStatus.put("0", "全部记录");
		auditStatus.put("0", "待审核");
		auditStatus.put("1", "所长/系主任审核通过");
		auditStatus.put("2", "未通过审核");
		auditStatus.put("3", "管理员审核通过");
		auditStatus.put("4", "查看全部记录");
		auditStatus.put("5", "待完善");
		context.setAttribute("auditStatus", auditStatus);
		
		context.setAttribute("researchLabList", new ResearchLabDAO().findAll());
		context.setAttribute("departmentList", new DepartmentDAO().findAll());
		context.setAttribute("termList", new TftermDAO().findAll());
	}

}
