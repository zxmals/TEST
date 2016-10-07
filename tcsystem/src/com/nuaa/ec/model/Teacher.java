package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher  implements java.io.Serializable {


    // Fields    

     private String teacherId;
     private ResearchLab researchLab;
     private Department department;
     private String spareTire;
     private String teacherName;
     private String departAdmin;
     private String vaadmin;
     private String researchLabAdmin;
     private Integer teacherprimaryid;
     private String teacherPost;
     private Set teacherAndinvitedExpertsSpeechs = new HashSet(0);
     private Set tfprofessionalProjectDeclarePerformances = new HashSet(0);
     private Set tfstudentCompetitionGuidancePerformances = new HashSet(0);
     private Set teacherAndperiodicals = new HashSet(0);
     private Set tfteachingCompetitionPerformances = new HashSet(0);
     private Set tfdegreeThesisGuidancePerformances = new HashSet(0);
     private Set tfsummerCourseInternationalConstructionPerformances = new HashSet(0);
     private Set tftextbookConstructionPerformances = new HashSet(0);
     private Set teacherAndmainUndertakeAcademicMeetings = new HashSet(0);
     private Set tfteachingPaperPerformances = new HashSet(0);
     private Set tfteachingAbilityImprovePerformances = new HashSet(0);
     private Set teacherAndscientificResearchProjects = new HashSet(0);
     private Set teacherAndselectedTalentProjects = new HashSet(0);
     private Set tffineCourseConstructionPerformances = new HashSet(0);
     private Set tfenterpriseWorkstationTrainingBaseConstructionPerformances = new HashSet(0);
     private Set tfclassTeachPefromances = new HashSet(0);
     private Set tfjoinStudentActivityPerformances = new HashSet(0);
     private Set tfundergraduateTutorGuidancePerformances = new HashSet(0);
     private Set tfpracticeInnovationGuidePerformances = new HashSet(0);
     private Set tfteachingRearchPerformances = new HashSet(0);
     private Set vateacherAndCollectiveActs = new HashSet(0);
     private Set teacherAndjoinAcademicMeetings = new HashSet(0);
     private Set vacollectiveActs = new HashSet(0);
     private Set tfteachingAchievementPerformances = new HashSet(0);
     private Set teacherLoginInfos = new HashSet(0);
     private Set teacherAndscientificResearchRewards = new HashSet(0);
     private Set teacherAndacademicWorks = new HashSet(0);
     private Set tffamousTeacherTeamPerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public Teacher() {
    }

	/** minimal constructor */
    public Teacher(String teacherId, String teacherName, Integer teacherprimaryid) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherprimaryid = teacherprimaryid;
    }
    
    /** full constructor */
    public Teacher(String teacherId, ResearchLab researchLab, Department department, String spareTire, String teacherName, String departAdmin, String researchLabAdmin,String vaadmin, Integer teacherprimaryid, String teacherPost, Set teacherAndinvitedExpertsSpeechs, Set tfprofessionalProjectDeclarePerformances, Set tfstudentCompetitionGuidancePerformances, Set teacherAndperiodicals, Set tfteachingCompetitionPerformances, Set tfdegreeThesisGuidancePerformances, Set tfsummerCourseInternationalConstructionPerformances, Set tftextbookConstructionPerformances, Set teacherAndmainUndertakeAcademicMeetings, Set tfteachingPaperPerformances, Set tfteachingAbilityImprovePerformances, Set teacherAndscientificResearchProjects, Set teacherAndselectedTalentProjects, Set tffineCourseConstructionPerformances, Set tfenterpriseWorkstationTrainingBaseConstructionPerformances, Set tfclassTeachPefromances, Set tfjoinStudentActivityPerformances, Set tfundergraduateTutorGuidancePerformances, Set tfpracticeInnovationGuidePerformances, Set tfteachingRearchPerformances, Set vateacherAndCollectiveActs, Set teacherAndjoinAcademicMeetings, Set vacollectiveActs, Set tfteachingAchievementPerformances, Set teacherLoginInfos, Set teacherAndscientificResearchRewards, Set teacherAndacademicWorks, Set tffamousTeacherTeamPerformances) {
        this.teacherId = teacherId;
        this.researchLab = researchLab;
        this.department = department;
        this.spareTire = spareTire;
        this.teacherName = teacherName;
        this.departAdmin = departAdmin;
        this.researchLabAdmin = researchLabAdmin;
        this.vaadmin = vaadmin;
        this.teacherprimaryid = teacherprimaryid;
        this.teacherPost = teacherPost;
        this.teacherAndinvitedExpertsSpeechs = teacherAndinvitedExpertsSpeechs;
        this.tfprofessionalProjectDeclarePerformances = tfprofessionalProjectDeclarePerformances;
        this.tfstudentCompetitionGuidancePerformances = tfstudentCompetitionGuidancePerformances;
        this.teacherAndperiodicals = teacherAndperiodicals;
        this.tfteachingCompetitionPerformances = tfteachingCompetitionPerformances;
        this.tfdegreeThesisGuidancePerformances = tfdegreeThesisGuidancePerformances;
        this.tfsummerCourseInternationalConstructionPerformances = tfsummerCourseInternationalConstructionPerformances;
        this.tftextbookConstructionPerformances = tftextbookConstructionPerformances;
        this.teacherAndmainUndertakeAcademicMeetings = teacherAndmainUndertakeAcademicMeetings;
        this.tfteachingPaperPerformances = tfteachingPaperPerformances;
        this.tfteachingAbilityImprovePerformances = tfteachingAbilityImprovePerformances;
        this.teacherAndscientificResearchProjects = teacherAndscientificResearchProjects;
        this.teacherAndselectedTalentProjects = teacherAndselectedTalentProjects;
        this.tffineCourseConstructionPerformances = tffineCourseConstructionPerformances;
        this.tfenterpriseWorkstationTrainingBaseConstructionPerformances = tfenterpriseWorkstationTrainingBaseConstructionPerformances;
        this.tfclassTeachPefromances = tfclassTeachPefromances;
        this.tfjoinStudentActivityPerformances = tfjoinStudentActivityPerformances;
        this.tfundergraduateTutorGuidancePerformances = tfundergraduateTutorGuidancePerformances;
        this.tfpracticeInnovationGuidePerformances = tfpracticeInnovationGuidePerformances;
        this.tfteachingRearchPerformances = tfteachingRearchPerformances;
        this.vateacherAndCollectiveActs = vateacherAndCollectiveActs;
        this.teacherAndjoinAcademicMeetings = teacherAndjoinAcademicMeetings;
        this.vacollectiveActs = vacollectiveActs;
        this.tfteachingAchievementPerformances = tfteachingAchievementPerformances;
        this.teacherLoginInfos = teacherLoginInfos;
        this.teacherAndscientificResearchRewards = teacherAndscientificResearchRewards;
        this.teacherAndacademicWorks = teacherAndacademicWorks;
        this.tffamousTeacherTeamPerformances = tffamousTeacherTeamPerformances;
    }

   
    // Property accessors

    public String getTeacherId() {
        return this.teacherId;
    }
    
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public ResearchLab getResearchLab() {
        return this.researchLab;
    }
    
    public void setResearchLab(ResearchLab researchLab) {
        this.researchLab = researchLab;
    }

    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public String getTeacherName() {
        return this.teacherName;
    }
    
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDepartAdmin() {
        return this.departAdmin;
    }
    
    public void setDepartAdmin(String departAdmin) {
        this.departAdmin = departAdmin;
    }

    public String getResearchLabAdmin() {
        return this.researchLabAdmin;
    }
    
    public void setResearchLabAdmin(String researchLabAdmin) {
        this.researchLabAdmin = researchLabAdmin;
    }

    public Integer getTeacherprimaryid() {
        return this.teacherprimaryid;
    }
    
    public void setTeacherprimaryid(Integer teacherprimaryid) {
        this.teacherprimaryid = teacherprimaryid;
    }

    public String getTeacherPost() {
        return this.teacherPost;
    }
    
    public void setTeacherPost(String teacherPost) {
        this.teacherPost = teacherPost;
    }

    public Set getTeacherAndinvitedExpertsSpeechs() {
        return this.teacherAndinvitedExpertsSpeechs;
    }
    
    public void setTeacherAndinvitedExpertsSpeechs(Set teacherAndinvitedExpertsSpeechs) {
        this.teacherAndinvitedExpertsSpeechs = teacherAndinvitedExpertsSpeechs;
    }

    public Set getTfprofessionalProjectDeclarePerformances() {
        return this.tfprofessionalProjectDeclarePerformances;
    }
    
    public void setTfprofessionalProjectDeclarePerformances(Set tfprofessionalProjectDeclarePerformances) {
        this.tfprofessionalProjectDeclarePerformances = tfprofessionalProjectDeclarePerformances;
    }

    public Set getTfstudentCompetitionGuidancePerformances() {
        return this.tfstudentCompetitionGuidancePerformances;
    }
    
    public void setTfstudentCompetitionGuidancePerformances(Set tfstudentCompetitionGuidancePerformances) {
        this.tfstudentCompetitionGuidancePerformances = tfstudentCompetitionGuidancePerformances;
    }

    public Set getTeacherAndperiodicals() {
        return this.teacherAndperiodicals;
    }
    
    public void setTeacherAndperiodicals(Set teacherAndperiodicals) {
        this.teacherAndperiodicals = teacherAndperiodicals;
    }

    public Set getTfteachingCompetitionPerformances() {
        return this.tfteachingCompetitionPerformances;
    }
    
    public void setTfteachingCompetitionPerformances(Set tfteachingCompetitionPerformances) {
        this.tfteachingCompetitionPerformances = tfteachingCompetitionPerformances;
    }

    public Set getTfdegreeThesisGuidancePerformances() {
        return this.tfdegreeThesisGuidancePerformances;
    }
    
    public void setTfdegreeThesisGuidancePerformances(Set tfdegreeThesisGuidancePerformances) {
        this.tfdegreeThesisGuidancePerformances = tfdegreeThesisGuidancePerformances;
    }

    public Set getTfsummerCourseInternationalConstructionPerformances() {
        return this.tfsummerCourseInternationalConstructionPerformances;
    }
    
    public void setTfsummerCourseInternationalConstructionPerformances(Set tfsummerCourseInternationalConstructionPerformances) {
        this.tfsummerCourseInternationalConstructionPerformances = tfsummerCourseInternationalConstructionPerformances;
    }

    public Set getTftextbookConstructionPerformances() {
        return this.tftextbookConstructionPerformances;
    }
    
    public void setTftextbookConstructionPerformances(Set tftextbookConstructionPerformances) {
        this.tftextbookConstructionPerformances = tftextbookConstructionPerformances;
    }

    public Set getTeacherAndmainUndertakeAcademicMeetings() {
        return this.teacherAndmainUndertakeAcademicMeetings;
    }
    
    public void setTeacherAndmainUndertakeAcademicMeetings(Set teacherAndmainUndertakeAcademicMeetings) {
        this.teacherAndmainUndertakeAcademicMeetings = teacherAndmainUndertakeAcademicMeetings;
    }

    public Set getTfteachingPaperPerformances() {
        return this.tfteachingPaperPerformances;
    }
    
    public void setTfteachingPaperPerformances(Set tfteachingPaperPerformances) {
        this.tfteachingPaperPerformances = tfteachingPaperPerformances;
    }

    public Set getTfteachingAbilityImprovePerformances() {
        return this.tfteachingAbilityImprovePerformances;
    }
    
    public void setTfteachingAbilityImprovePerformances(Set tfteachingAbilityImprovePerformances) {
        this.tfteachingAbilityImprovePerformances = tfteachingAbilityImprovePerformances;
    }

    public Set getTeacherAndscientificResearchProjects() {
        return this.teacherAndscientificResearchProjects;
    }
    
    public void setTeacherAndscientificResearchProjects(Set teacherAndscientificResearchProjects) {
        this.teacherAndscientificResearchProjects = teacherAndscientificResearchProjects;
    }

    public Set getTeacherAndselectedTalentProjects() {
        return this.teacherAndselectedTalentProjects;
    }
    
    public void setTeacherAndselectedTalentProjects(Set teacherAndselectedTalentProjects) {
        this.teacherAndselectedTalentProjects = teacherAndselectedTalentProjects;
    }

    public Set getTffineCourseConstructionPerformances() {
        return this.tffineCourseConstructionPerformances;
    }
    
    public void setTffineCourseConstructionPerformances(Set tffineCourseConstructionPerformances) {
        this.tffineCourseConstructionPerformances = tffineCourseConstructionPerformances;
    }

    public Set getTfenterpriseWorkstationTrainingBaseConstructionPerformances() {
        return this.tfenterpriseWorkstationTrainingBaseConstructionPerformances;
    }
    
    public void setTfenterpriseWorkstationTrainingBaseConstructionPerformances(Set tfenterpriseWorkstationTrainingBaseConstructionPerformances) {
        this.tfenterpriseWorkstationTrainingBaseConstructionPerformances = tfenterpriseWorkstationTrainingBaseConstructionPerformances;
    }

    public Set getTfclassTeachPefromances() {
        return this.tfclassTeachPefromances;
    }
    
    public void setTfclassTeachPefromances(Set tfclassTeachPefromances) {
        this.tfclassTeachPefromances = tfclassTeachPefromances;
    }

    public Set getTfjoinStudentActivityPerformances() {
        return this.tfjoinStudentActivityPerformances;
    }
    
    public void setTfjoinStudentActivityPerformances(Set tfjoinStudentActivityPerformances) {
        this.tfjoinStudentActivityPerformances = tfjoinStudentActivityPerformances;
    }

    public Set getTfundergraduateTutorGuidancePerformances() {
        return this.tfundergraduateTutorGuidancePerformances;
    }
    
    public void setTfundergraduateTutorGuidancePerformances(Set tfundergraduateTutorGuidancePerformances) {
        this.tfundergraduateTutorGuidancePerformances = tfundergraduateTutorGuidancePerformances;
    }

    public Set getTfpracticeInnovationGuidePerformances() {
        return this.tfpracticeInnovationGuidePerformances;
    }
    
    public void setTfpracticeInnovationGuidePerformances(Set tfpracticeInnovationGuidePerformances) {
        this.tfpracticeInnovationGuidePerformances = tfpracticeInnovationGuidePerformances;
    }

    public Set getTfteachingRearchPerformances() {
        return this.tfteachingRearchPerformances;
    }
    
    public void setTfteachingRearchPerformances(Set tfteachingRearchPerformances) {
        this.tfteachingRearchPerformances = tfteachingRearchPerformances;
    }

    public Set getVateacherAndCollectiveActs() {
        return this.vateacherAndCollectiveActs;
    }
    
    public void setVateacherAndCollectiveActs(Set vateacherAndCollectiveActs) {
        this.vateacherAndCollectiveActs = vateacherAndCollectiveActs;
    }

    public Set getTeacherAndjoinAcademicMeetings() {
        return this.teacherAndjoinAcademicMeetings;
    }
    
    public void setTeacherAndjoinAcademicMeetings(Set teacherAndjoinAcademicMeetings) {
        this.teacherAndjoinAcademicMeetings = teacherAndjoinAcademicMeetings;
    }

    public Set getVacollectiveActs() {
        return this.vacollectiveActs;
    }
    
    public void setVacollectiveActs(Set vacollectiveActs) {
        this.vacollectiveActs = vacollectiveActs;
    }

    public Set getTfteachingAchievementPerformances() {
        return this.tfteachingAchievementPerformances;
    }
    
    public void setTfteachingAchievementPerformances(Set tfteachingAchievementPerformances) {
        this.tfteachingAchievementPerformances = tfteachingAchievementPerformances;
    }

    public Set getTeacherLoginInfos() {
        return this.teacherLoginInfos;
    }
    
    public void setTeacherLoginInfos(Set teacherLoginInfos) {
        this.teacherLoginInfos = teacherLoginInfos;
    }

    public Set getTeacherAndscientificResearchRewards() {
        return this.teacherAndscientificResearchRewards;
    }
    
    public void setTeacherAndscientificResearchRewards(Set teacherAndscientificResearchRewards) {
        this.teacherAndscientificResearchRewards = teacherAndscientificResearchRewards;
    }

    public Set getTeacherAndacademicWorks() {
        return this.teacherAndacademicWorks;
    }
    
    public void setTeacherAndacademicWorks(Set teacherAndacademicWorks) {
        this.teacherAndacademicWorks = teacherAndacademicWorks;
    }

    public Set getTffamousTeacherTeamPerformances() {
        return this.tffamousTeacherTeamPerformances;
    }
    
    public void setTffamousTeacherTeamPerformances(Set tffamousTeacherTeamPerformances) {
        this.tffamousTeacherTeamPerformances = tffamousTeacherTeamPerformances;
    }

	public String getVaadmin() {
		return vaadmin;
	}

	public void setVaadmin(String vaadmin) {
		this.vaadmin = vaadmin;
	}
   








}