package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * InvitedExpertsSpeechScore entity. @author MyEclipse Persistence Tools
 */

public class InvitedExpertsSpeechScore  implements java.io.Serializable {


    // Fields    

     private String iesscoreId;
     private SubModular subModular;
     private ExpertType expertType;
     private Long score;
     private String spareTire;
     private Set teacherAndinvitedExpertsSpeechs = new HashSet(0);


    // Constructors

    /** default constructor */
    public InvitedExpertsSpeechScore() {
    }

	/** minimal constructor */
    public InvitedExpertsSpeechScore(String iesscoreId) {
        this.iesscoreId = iesscoreId;
    }
    
    /** full constructor */
    public InvitedExpertsSpeechScore(String iesscoreId, SubModular subModular, ExpertType expertType, Long score, String spareTire, Set teacherAndinvitedExpertsSpeechs) {
        this.iesscoreId = iesscoreId;
        this.subModular = subModular;
        this.expertType = expertType;
        this.score = score;
        this.spareTire = spareTire;
        this.teacherAndinvitedExpertsSpeechs = teacherAndinvitedExpertsSpeechs;
    }

   
    // Property accessors

    public String getIesscoreId() {
        return this.iesscoreId;
    }
    
    public void setIesscoreId(String iesscoreId) {
        this.iesscoreId = iesscoreId;
    }

    public SubModular getSubModular() {
        return this.subModular;
    }
    
    public void setSubModular(SubModular subModular) {
        this.subModular = subModular;
    }

    public ExpertType getExpertType() {
        return this.expertType;
    }
    
    public void setExpertType(ExpertType expertType) {
        this.expertType = expertType;
    }

    public Long getScore() {
        return this.score;
    }
    
    public void setScore(Long score) {
        this.score = score;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTeacherAndinvitedExpertsSpeechs() {
        return this.teacherAndinvitedExpertsSpeechs;
    }
    
    public void setTeacherAndinvitedExpertsSpeechs(Set teacherAndinvitedExpertsSpeechs) {
        this.teacherAndinvitedExpertsSpeechs = teacherAndinvitedExpertsSpeechs;
    }
   








}