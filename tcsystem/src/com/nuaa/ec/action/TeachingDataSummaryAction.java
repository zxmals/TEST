package com.nuaa.ec.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.junit.Test;

import com.nuaa.ec.dao.DepartmentDAO;
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

	private TfclassTeachPefromanceDAO classteachdao = new TfclassTeachPefromanceDAO();
	private TfdegreeThesisGuidancePerformanceDAO paperguidancedao = new TfdegreeThesisGuidancePerformanceDAO();
	private TfteachingCompetitionPerformanceDAO teachingcompetedao = new TfteachingCompetitionPerformanceDAO();
	private TfteachingAbilityImprovePerformanceDAO teachingabilitydao = new TfteachingAbilityImprovePerformanceDAO();
	private TffamousTeacherTeamPerformanceDAO famousteachteamdao = new TffamousTeacherTeamPerformanceDAO();
	private TfteachingRearchPerformanceDAO teachingresearchdao = new TfteachingRearchPerformanceDAO();
	private TfteachingPaperPerformanceDAO teachingpaperdao = new TfteachingPaperPerformanceDAO();
	private TfteachingAchievementPerformanceDAO teachingachievedao = new TfteachingAchievementPerformanceDAO();
	private TftextbookConstructionPerformanceDAO textbookdao = new TftextbookConstructionPerformanceDAO();
	private TffineCourseConstructionPerformanceDAO finecoursedao = new TffineCourseConstructionPerformanceDAO();
	private TfprofessionalProjectDeclarePerformanceDAO professionprojectdao = new TfprofessionalProjectDeclarePerformanceDAO();
	private TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO firmworkstationdao = new TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO();
	private TfsummerCourseInternationalConstructionPerformanceDAO summercoursedao = new TfsummerCourseInternationalConstructionPerformanceDAO();
	private TfpracticeInnovationGuidePerformanceDAO practiceinnovationdao = new TfpracticeInnovationGuidePerformanceDAO();
	private TfstudentCompetitionGuidancePerformanceDAO studentscompetdao = new TfstudentCompetitionGuidancePerformanceDAO();
	private TfjoinStudentActivityPerformanceDAO studentactivitydao = new TfjoinStudentActivityPerformanceDAO();
	private TfundergraduateTutorGuidancePerformanceDAO undergraduateguidancedao = new TfundergraduateTutorGuidancePerformanceDAO();
	private TfoffCampusPracticeGuidancePerformanceDAO offcampusdao = new TfoffCampusPracticeGuidancePerformanceDAO();
	//default method
	public String execute(){
		return "success";
	}
	public String getDepartSummaryData() throws Exception{
		try {
			if("alldepart".equals(depart.getDepartmentId().trim())){
				session.put("alldepartsumdata", getAllSum());
			}else{
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return "success";
	}
	
	public List<E_SummaryOfTeaching> getAllSum(){
		@SuppressWarnings("unchecked")
		List<Department> departsli = new DepartmentDAO().findAll();
		List<E_SummaryOfTeaching> esotli = new ArrayList<E_SummaryOfTeaching>();
		E_SummaryOfTeaching es = null;
		double summarys[] = new double[18];
		for(int i=0;i<departsli.size();i++){
			double sum = 0;
			es = new E_SummaryOfTeaching();
			es.setDeparts(departsli.get(i));
			
			es.setClassTeaching(classteachdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getClassTeaching().getSum();
			summarys[0] += es.getClassTeaching().getSum();
			
			es.setDegreeGuidance(paperguidancedao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getDegreeGuidance().getSum();
			summarys[1] += es.getDegreeGuidance().getSum();
			
			es.setFamousTeacherTeam(famousteachteamdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getFamousTeacherTeam().getSum();
			summarys[2] += es.getFamousTeacherTeam().getSum();
			
			es.setFineCourse(finecoursedao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getFineCourse().getSum();
			summarys[3] += es.getFineCourse().getSum();
			
			es.setFirmWorkstationTrainingBase(firmworkstationdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getFirmWorkstationTrainingBase().getSum();
			summarys[4] += es.getFirmWorkstationTrainingBase().getSum();
			
			es.setOff_campusPracticeGuidance(offcampusdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getOff_campusPracticeGuidance().getSum();
			summarys[5] += es.getOff_campusPracticeGuidance().getSum();
			
			es.setPracticeInnovationGuidance(practiceinnovationdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getPracticeInnovationGuidance().getSum();
			summarys[6] += es.getPracticeInnovationGuidance().getSum();
			
			es.setProfessionalProjectConstruction(professionprojectdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getProfessionalProjectConstruction().getSum();
			summarys[7] += es.getProfessionalProjectConstruction().getSum();
			
			es.setStudentsActivity(studentactivitydao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getStudentsActivity().getSum();
			summarys[8] += es.getStudentsActivity().getSum();
			
			es.setStudentsCompetitionGuidance(studentscompetdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getStudentsCompetitionGuidance().getSum();
			summarys[9] += es.getStudentsCompetitionGuidance().getSum();
			
			es.setSummerInternationalCourse(summercoursedao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getSummerInternationalCourse().getSum();
			summarys[10] += es.getSummerInternationalCourse().getSum();
			
			es.setTeachingAbilityImprove(teachingabilitydao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getTeachingAbilityImprove().getSum();
			summarys[11] += es.getTeachingAbilityImprove().getSum();
			
			es.setTeachingAchievement(teachingachievedao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getTeachingAchievement().getSum();
			summarys[12] += es.getTeachingAchievement().getSum();
			
			es.setTeachingCompetition(teachingcompetedao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getTeachingCompetition().getSum();
			summarys[13] += es.getTeachingCompetition().getSum();
			
			es.setTeachingPaper(teachingpaperdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getTeachingPaper().getSum();
			summarys[14] += es.getTeachingPaper().getSum();
			
			es.setTeachingResearch(teachingresearchdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getTeachingResearch().getSum();
			summarys[15] += es.getTeachingResearch().getSum();
			
			es.setTextBookConstruction(textbookdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getTextBookConstruction().getSum();
			summarys[16] += es.getTextBookConstruction().getSum();
			
			es.setUndergraduateGuidance(undergraduateguidancedao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getUndergraduateGuidance().getSum();
			summarys[17] += es.getUndergraduateGuidance().getSum();
			
			es.setSumUP(new Statistics_asist(sum,(double)((int)((sum/18)/0.01))*0.01));
			esotli.add(es);
		}
		E_SummaryOfTeaching eso = new E_SummaryOfTeaching();
		eso.setDeparts(new Department("", "合计", "", "", "", null));
		double sum = 0;
		eso.setClassTeaching(new Statistics_asist(summarys[0], (double)((int)((summarys[0]/departsli.size())/0.01))*0.01));
		sum += eso.getClassTeaching().getSum();
		eso.setDegreeGuidance(new Statistics_asist(summarys[1], (double)((int)((summarys[1]/departsli.size())/0.01))*0.01));
		sum += eso.getDegreeGuidance().getSum();
		eso.setFamousTeacherTeam(new Statistics_asist(summarys[2], (double)((int)((summarys[2]/departsli.size())/0.01))*0.01));
		sum += eso.getFamousTeacherTeam().getSum();
		eso.setFineCourse(new Statistics_asist(summarys[3], (double)((int)((summarys[3]/departsli.size())/0.01))*0.01));
		sum += eso.getFineCourse().getSum();
		eso.setFirmWorkstationTrainingBase(new Statistics_asist(summarys[4], (double)((int)((summarys[4]/departsli.size())/0.01))*0.01));
		sum += eso.getFirmWorkstationTrainingBase().getSum();
		eso.setOff_campusPracticeGuidance(new Statistics_asist(summarys[5], (double)((int)((summarys[5]/departsli.size())/0.01))*0.01));
		sum += eso.getOff_campusPracticeGuidance().getSum();
		eso.setPracticeInnovationGuidance(new Statistics_asist(summarys[6], (double)((int)((summarys[6]/departsli.size())/0.01))*0.01));
		sum += eso.getPracticeInnovationGuidance().getSum();
		eso.setProfessionalProjectConstruction(new Statistics_asist(summarys[7], (double)((int)((summarys[7]/departsli.size())/0.01))*0.01));
		sum += eso.getProfessionalProjectConstruction().getSum();
		eso.setStudentsActivity(new Statistics_asist(summarys[8], (double)((int)((summarys[8]/departsli.size())/0.01))*0.01));
		sum += eso.getStudentsActivity().getSum();
		eso.setStudentsCompetitionGuidance(new Statistics_asist(summarys[9], (double)((int)((summarys[9]/departsli.size())/0.01))*0.01));
		sum += eso.getStudentsCompetitionGuidance().getSum();
		eso.setSummerInternationalCourse(new Statistics_asist(summarys[10], (double)((int)((summarys[10]/departsli.size())/0.01))*0.01));
		sum += eso.getSummerInternationalCourse().getSum();
		eso.setTeachingAbilityImprove(new Statistics_asist(summarys[11], (double)((int)((summarys[11]/departsli.size())/0.01))*0.01));
		sum += eso.getTeachingAbilityImprove().getSum();
		eso.setTeachingAchievement(new Statistics_asist(summarys[12], (double)((int)((summarys[12]/departsli.size())/0.01))*0.01));
		sum += eso.getTeachingAchievement().getSum();
		eso.setTeachingCompetition(new Statistics_asist(summarys[13], (double)((int)((summarys[13]/departsli.size())/0.01))*0.01));
		sum += eso.getTeachingCompetition().getSum();
		eso.setTeachingPaper(new Statistics_asist(summarys[14], (double)((int)((summarys[14]/departsli.size())/0.01))*0.01));
		sum += eso.getTeachingPaper().getSum();
		eso.setTeachingResearch(new Statistics_asist(summarys[15], (double)((int)((summarys[15]/departsli.size())/0.01))*0.01));
		sum += eso.getTeachingResearch().getSum();
		eso.setTextBookConstruction(new Statistics_asist(summarys[16], (double)((int)((summarys[16]/departsli.size())/0.01))*0.01));
		sum += eso.getTextBookConstruction().getSum();
		eso.setUndergraduateGuidance(new Statistics_asist(summarys[17], (double)((int)((summarys[17]/departsli.size())/0.01))*0.01));
		sum += eso.getUndergraduateGuidance().getSum();
		eso.setSumUP(new Statistics_asist(sum, (double)((int)((sum/18)/0.01))*0.01));
		esotli.add(eso);
		return esotli;
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
