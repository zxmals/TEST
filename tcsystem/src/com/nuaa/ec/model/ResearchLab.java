package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * ResearchLab entity. @author MyEclipse Persistence Tools
 */

public class ResearchLab  implements java.io.Serializable {


    // Fields    

     private String researchLabId;
     private String researchLabName;
     private String spareTire;
     private String researchLabAdminId;
     private String researchLabAdmin;
     private Set teachers = new HashSet(0);


    // Constructors

    /** default constructor */
    public ResearchLab() {
    }

	/** minimal constructor */
    public ResearchLab(String researchLabId) {
        this.researchLabId = researchLabId;
    }
    
    /** full constructor */
    public ResearchLab(String researchLabId, String researchLabName, String spareTire, String researchLabAdminId, String researchLabAdmin, Set teachers) {
        this.researchLabId = researchLabId;
        this.researchLabName = researchLabName;
        this.spareTire = spareTire;
        this.researchLabAdminId = researchLabAdminId;
        this.researchLabAdmin = researchLabAdmin;
        this.teachers = teachers;
    }

   
    // Property accessors

    public String getResearchLabId() {
        return this.researchLabId;
    }
    
    public void setResearchLabId(String researchLabId) {
        this.researchLabId = researchLabId;
    }

    public String getResearchLabName() {
        return this.researchLabName;
    }
    
    public void setResearchLabName(String researchLabName) {
        this.researchLabName = researchLabName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public String getResearchLabAdminId() {
        return this.researchLabAdminId;
    }
    
    public void setResearchLabAdminId(String researchLabAdminId) {
        this.researchLabAdminId = researchLabAdminId;
    }

    public String getResearchLabAdmin() {
        return this.researchLabAdmin;
    }
    
    public void setResearchLabAdmin(String researchLabAdmin) {
        this.researchLabAdmin = researchLabAdmin;
    }

    public Set getTeachers() {
        return this.teachers;
    }
    
    public void setTeachers(Set teachers) {
        this.teachers = teachers;
    }
   








}