package com.nuaa.ec.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.junit.Test;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.TeachingSummaryDao;
import com.nuaa.ec.dao.TfclassTeachPefromanceDAO;
import com.nuaa.ec.dao.TfdegreeThesisGuidancePerformanceDAO;
import com.nuaa.ec.dao.TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO;
import com.nuaa.ec.dao.TffamousTeacherTeamPerformanceDAO;
import com.nuaa.ec.dao.TffineCourseConstructionPerformanceDAO;
import com.nuaa.ec.dao.TfjoinStudentActivityPerformanceDAO;
import com.nuaa.ec.dao.TfoffCampusPracticeGuidancePerformanceDAO;
import com.nuaa.ec.dao.TfpracticeInnovationGuidePerformanceDAO;
import com.nuaa.ec.dao.TfprofessionalProjectDeclarePerformanceDAO;
import com.nuaa.ec.dao.TfstudentCompetitionGuidancePerformanceDAO;
import com.nuaa.ec.dao.TfsummerCourseInternationalConstructionPerformanceDAO;
import com.nuaa.ec.dao.TfteachingAbilityImprovePerformanceDAO;
import com.nuaa.ec.dao.TfteachingAchievementPerformanceDAO;
import com.nuaa.ec.dao.TfteachingCompetitionPerformanceDAO;
import com.nuaa.ec.dao.TfteachingPaperPerformanceDAO;
import com.nuaa.ec.dao.TfteachingRearchPerformanceDAO;
import com.nuaa.ec.dao.TftextbookConstructionPerformanceDAO;
import com.nuaa.ec.dao.TfundergraduateTutorGuidancePerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.utils.E_SummaryOfTeaching;
import com.nuaa.ec.utils.Statistics_asist;

public class TeachingDataSummaryAction implements RequestAware, SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private String foreterm;
	private String afterterm;
	private Department depart;
	
	private TeachingSummaryDao teachsumdao = new TeachingSummaryDao();
	//default method
	public String execute(){
		return "success";
	}
	public String getDepartSummaryData() throws Exception{
		try {
			if("alldepart".equals(depart.getDepartmentId().trim())){
				session.put("departsumdata", teachsumdao.getAllSum(foreterm,afterterm));
			}else{
				session.put("departsumdata", teachsumdao.getSingleSum(foreterm, afterterm, depart));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return "success";
	}
	
	//Getter & Setter
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getForeterm() {
		return foreterm;
	}

	public void setForeterm(String foreterm) {
		this.foreterm = foreterm;
	}

	public String getAfterterm() {
		return afterterm;
	}

	public void setAfterterm(String afterterm) {
		this.afterterm = afterterm;
	}

	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}

}
