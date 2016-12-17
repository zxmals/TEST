package com.nuaa.ec.action;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

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
import com.nuaa.ec.utils.StoreData;

public class TeachingModuleDataExport implements RequestAware, SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private String foredate;
	private String afterdate;
	private Department department;
	private String entitys;
	private Map<String, String> filenameExported = StoreData
			.getTeachingFilenameExported();
	private DepartmentDAO departmentDAO = new DepartmentDAO();
	private TfclassTeachPefromanceDAO tfClasTechPformsDAO = new TfclassTeachPefromanceDAO();
	private TfdegreeThesisGuidancePerformanceDAO tfDegreThsisGuidnsPerfomsDAO = new TfdegreeThesisGuidancePerformanceDAO();
	private TfteachingCompetitionPerformanceDAO tfTechingComptionPrfmnsDAO = new TfteachingCompetitionPerformanceDAO();
	private TfteachingAbilityImprovePerformanceDAO tfTchingAbilityImprovDAO=new TfteachingAbilityImprovePerformanceDAO();
	private TfpracticeInnovationGuidePerformanceDAO tfPracInnoGuidPerfDAO = new TfpracticeInnovationGuidePerformanceDAO();
	private TfstudentCompetitionGuidancePerformanceDAO studCompGuidDAO = new TfstudentCompetitionGuidancePerformanceDAO();
	private TfjoinStudentActivityPerformanceDAO joinStudActPerfDAO = new TfjoinStudentActivityPerformanceDAO();
	private TfundergraduateTutorGuidancePerformanceDAO tfUndergTutorGuidanceDAO = new TfundergraduateTutorGuidancePerformanceDAO();
	private TfoffCampusPracticeGuidancePerformanceDAO tfOffCampusPracGuidPerfDAO = new TfoffCampusPracticeGuidancePerformanceDAO();
	private TffamousTeacherTeamPerformanceDAO tfFamosTchrTmPerfDAO = new TffamousTeacherTeamPerformanceDAO();
	private TfteachingRearchPerformanceDAO tfTchingReschPerfDAO = new TfteachingRearchPerformanceDAO();
	private TfteachingPaperPerformanceDAO tfTchingPaperPerfDAO = new TfteachingPaperPerformanceDAO();
	private TfteachingAchievementPerformanceDAO tfTchingAchivmtPerfDAO = new TfteachingAchievementPerformanceDAO();
	private TftextbookConstructionPerformanceDAO tfTkstBukConstrucPerfDAO = new TftextbookConstructionPerformanceDAO();
	private TffineCourseConstructionPerformanceDAO tfFineCourseConstPerfDAO = new TffineCourseConstructionPerformanceDAO();
	private TfprofessionalProjectDeclarePerformanceDAO tfProProjectDeclarePerfDAO = new TfprofessionalProjectDeclarePerformanceDAO();
	private TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO enterBaseConstructionPerformanceDAO=new TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO();
	private TfsummerCourseInternationalConstructionPerformanceDAO tfsummerConstructionPerformanceDAO=new TfsummerCourseInternationalConstructionPerformanceDAO();
	// default method
	public String execute() {
		return "success";
	}

	public void generateExportData() throws Exception {
		try {
			ByteArrayOutputStream baos = null;
			if ("classTeaching".equals(entitys.trim())) {
				baos = tfClasTechPformsDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
								.getDepartmentName());
			}else if("degreeThesisGuidance".equals(entitys.trim())){
				baos = tfDegreThsisGuidnsPerfomsDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}else if("teachingCompetition".equals(entitys.trim())){
				baos = tfTechingComptionPrfmnsDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}else if("teachingAbilityImprove".equals(entitys.trim())){
				baos = tfTchingAbilityImprovDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}else if("practiceInnovationGuidance".equals(entitys.trim())){
				baos = tfPracInnoGuidPerfDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("studentCompetitionGuidance".equals(entitys.trim())){
				baos = studCompGuidDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("joinStudentActivity".equals(entitys.trim())){
				baos = joinStudActPerfDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("undergraduateTutorGuidance".equals(entitys.trim())){
				baos = tfUndergTutorGuidanceDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("offCampusPracticeGuidance".equals(entitys.trim())){
				baos = tfOffCampusPracGuidPerfDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("famousTeacherTeam".equals(entitys.trim())){
				baos = tfFamosTchrTmPerfDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("teachingResearch".equals(entitys.trim())){
				baos = tfTchingReschPerfDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("teachingPaper".equals(entitys.trim())){
				baos = tfTchingPaperPerfDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("teachingAchievement".equals(entitys.trim())){
				baos = tfTchingAchivmtPerfDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("textbookConstruction".equals(entitys.trim())){
				baos = tfTkstBukConstrucPerfDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("fineCourseConstruction".equals(entitys.trim())){
				baos = tfFineCourseConstPerfDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("professionalProjectDeclare".equals(entitys.trim())){
				baos = tfProProjectDeclarePerfDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("enterpriseWorkstation".equals(entitys.trim())){
				baos = enterBaseConstructionPerformanceDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			else if("summerInternationalCourse".equals(entitys.trim())){
				baos = tfsummerConstructionPerformanceDAO.findwithexport(department, foredate,
						afterdate,
						departmentDAO.findById(department.getDepartmentId())
						.getDepartmentName());
			}
			if (baos != null) {
				HttpServletResponse resp = ServletActionContext.getResponse();
				OutputStream out = resp.getOutputStream();
				resp.setHeader(
						"Content-Disposition",
						"attachment;filename="
								+ URLEncoder.encode(
										filenameExported.get(entitys.trim()),
										"UTF-8") + ".xls");
				byte[] bt = baos.toByteArray();
				out.write(bt, 0, bt.length);
				out.flush();
				out.close();
			} else {
				ServletActionContext.getResponse()
						.setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter()
						.write(" 该区间暂时没有数据..... 请返回到上一页 ");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	// Getter & Setter
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

	public String getForedate() {
		return foredate;
	}

	public void setForedate(String foredate) {
		this.foredate = foredate;
	}

	public String getAfterdate() {
		return afterdate;
	}

	public void setAfterdate(String afterdate) {
		this.afterdate = afterdate;
	}

	public String getEntitys() {
		return entitys;
	}

	public void setEntitys(String entitys) {
		this.entitys = entitys;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
