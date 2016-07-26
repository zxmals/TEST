package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * SelfUndertakeTask entity. @author MyEclipse Persistence Tools
 */

public class SelfUndertakeTask  implements java.io.Serializable {


    // Fields    

     private String undertakeTaskId;
     private String undertakeTaskName;
     private String spareTire;
     private Set tffineCourseConstructionPerformances = new HashSet(0);
     private Set tfteachingPaperPerformances = new HashSet(0);
     private Set tfteachingAchievementPerformances = new HashSet(0);
     private Set teacherAndacademicWorks = new HashSet(0);
     private Set tftextbookConstructionPerformances = new HashSet(0);
     private Set tfprofessionalProjectDeclarePerformances = new HashSet(0);
     private Set teacherAndscientificResearchProjects = new HashSet(0);
     private Set tffamousTeacherTeamPerformances = new HashSet(0);
     private Set teacherAndmainUndertakeAcademicMeetings = new HashSet(0);
     private Set teacherAndinvitedExpertsSpeechs = new HashSet(0);
     private Set tfenterpriseWorkstationTrainingBaseConstructionPerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public SelfUndertakeTask() {
    }

	/** minimal constructor */
    public SelfUndertakeTask(String undertakeTaskId) {
        this.undertakeTaskId = undertakeTaskId;
    }
    
    /** full constructor */
    public SelfUndertakeTask(String undertakeTaskId, String undertakeTaskName, String spareTire, Set tffineCourseConstructionPerformances, Set tfteachingPaperPerformances, Set tfteachingAchievementPerformances, Set teacherAndacademicWorks, Set tftextbookConstructionPerformances, Set tfprofessionalProjectDeclarePerformances, Set teacherAndscientificResearchProjects, Set tffamousTeacherTeamPerformances, Set teacherAndmainUndertakeAcademicMeetings, Set teacherAndinvitedExpertsSpeechs, Set tfenterpriseWorkstationTrainingBaseConstructionPerformances) {
        this.undertakeTaskId = undertakeTaskId;
        this.undertakeTaskName = undertakeTaskName;
        this.spareTire = spareTire;
        this.tffineCourseConstructionPerformances = tffineCourseConstructionPerformances;
        this.tfteachingPaperPerformances = tfteachingPaperPerformances;
        this.tfteachingAchievementPerformances = tfteachingAchievementPerformances;
        this.teacherAndacademicWorks = teacherAndacademicWorks;
        this.tftextbookConstructionPerformances = tftextbookConstructionPerformances;
        this.tfprofessionalProjectDeclarePerformances = tfprofessionalProjectDeclarePerformances;
        this.teacherAndscientificResearchProjects = teacherAndscientificResearchProjects;
        this.tffamousTeacherTeamPerformances = tffamousTeacherTeamPerformances;
        this.teacherAndmainUndertakeAcademicMeetings = teacherAndmainUndertakeAcademicMeetings;
        this.teacherAndinvitedExpertsSpeechs = teacherAndinvitedExpertsSpeechs;
        this.tfenterpriseWorkstationTrainingBaseConstructionPerformances = tfenterpriseWorkstationTrainingBaseConstructionPerformances;
    }

   
    // Property accessors

    public String getUndertakeTaskId() {
        return this.undertakeTaskId;
    }
    
    public void setUndertakeTaskId(String undertakeTaskId) {
        this.undertakeTaskId = undertakeTaskId;
    }

    public String getUndertakeTaskName() {
        return this.undertakeTaskName;
    }
    
    public void setUndertakeTaskName(String undertakeTaskName) {
        this.undertakeTaskName = undertakeTaskName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTffineCourseConstructionPerformances() {
        return this.tffineCourseConstructionPerformances;
    }
    
    public void setTffineCourseConstructionPerformances(Set tffineCourseConstructionPerformances) {
        this.tffineCourseConstructionPerformances = tffineCourseConstructionPerformances;
    }

    public Set getTfteachingPaperPerformances() {
        return this.tfteachingPaperPerformances;
    }
    
    public void setTfteachingPaperPerformances(Set tfteachingPaperPerformances) {
        this.tfteachingPaperPerformances = tfteachingPaperPerformances;
    }

    public Set getTfteachingAchievementPerformances() {
        return this.tfteachingAchievementPerformances;
    }
    
    public void setTfteachingAchievementPerformances(Set tfteachingAchievementPerformances) {
        this.tfteachingAchievementPerformances = tfteachingAchievementPerformances;
    }

    public Set getTeacherAndacademicWorks() {
        return this.teacherAndacademicWorks;
    }
    
    public void setTeacherAndacademicWorks(Set teacherAndacademicWorks) {
        this.teacherAndacademicWorks = teacherAndacademicWorks;
    }

    public Set getTftextbookConstructionPerformances() {
        return this.tftextbookConstructionPerformances;
    }
    
    public void setTftextbookConstructionPerformances(Set tftextbookConstructionPerformances) {
        this.tftextbookConstructionPerformances = tftextbookConstructionPerformances;
    }

    public Set getTfprofessionalProjectDeclarePerformances() {
        return this.tfprofessionalProjectDeclarePerformances;
    }
    
    public void setTfprofessionalProjectDeclarePerformances(Set tfprofessionalProjectDeclarePerformances) {
        this.tfprofessionalProjectDeclarePerformances = tfprofessionalProjectDeclarePerformances;
    }

    public Set getTeacherAndscientificResearchProjects() {
        return this.teacherAndscientificResearchProjects;
    }
    
    public void setTeacherAndscientificResearchProjects(Set teacherAndscientificResearchProjects) {
        this.teacherAndscientificResearchProjects = teacherAndscientificResearchProjects;
    }

    public Set getTffamousTeacherTeamPerformances() {
        return this.tffamousTeacherTeamPerformances;
    }
    
    public void setTffamousTeacherTeamPerformances(Set tffamousTeacherTeamPerformances) {
        this.tffamousTeacherTeamPerformances = tffamousTeacherTeamPerformances;
    }

    public Set getTeacherAndmainUndertakeAcademicMeetings() {
        return this.teacherAndmainUndertakeAcademicMeetings;
    }
    
    public void setTeacherAndmainUndertakeAcademicMeetings(Set teacherAndmainUndertakeAcademicMeetings) {
        this.teacherAndmainUndertakeAcademicMeetings = teacherAndmainUndertakeAcademicMeetings;
    }

    public Set getTeacherAndinvitedExpertsSpeechs() {
        return this.teacherAndinvitedExpertsSpeechs;
    }
    
    public void setTeacherAndinvitedExpertsSpeechs(Set teacherAndinvitedExpertsSpeechs) {
        this.teacherAndinvitedExpertsSpeechs = teacherAndinvitedExpertsSpeechs;
    }

    public Set getTfenterpriseWorkstationTrainingBaseConstructionPerformances() {
        return this.tfenterpriseWorkstationTrainingBaseConstructionPerformances;
    }
    
    public void setTfenterpriseWorkstationTrainingBaseConstructionPerformances(Set tfenterpriseWorkstationTrainingBaseConstructionPerformances) {
        this.tfenterpriseWorkstationTrainingBaseConstructionPerformances = tfenterpriseWorkstationTrainingBaseConstructionPerformances;
    }
   








}