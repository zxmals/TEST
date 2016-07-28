package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TalentProject entity. @author MyEclipse Persistence Tools
 */

public class TalentProject  implements java.io.Serializable {


    // Fields    

     private String talentProjectId;
     private String talentProjectName;
     private String spareTire;
     private String chargePersonId;
     private String chargePerson;
     private String checkout;
     private String selectedDate;
     private Set selectedTalentProjectScores = new HashSet(0);
     private Set teacherAndselectedTalentProjects = new HashSet(0);


    // Constructors

    /** default constructor */
    public TalentProject() {
    }

	/** minimal constructor */
    public TalentProject(String talentProjectId) {
        this.talentProjectId = talentProjectId;
    }
    
    /** full constructor */
    public TalentProject(String talentProjectId, String talentProjectName, String spareTire, String chargePersonId, String chargePerson, String checkout, String selectedDate, Set selectedTalentProjectScores, Set teacherAndselectedTalentProjects) {
        this.talentProjectId = talentProjectId;
        this.talentProjectName = talentProjectName;
        this.spareTire = spareTire;
        this.chargePersonId = chargePersonId;
        this.chargePerson = chargePerson;
        this.checkout = checkout;
        this.selectedDate = selectedDate;
        this.selectedTalentProjectScores = selectedTalentProjectScores;
        this.teacherAndselectedTalentProjects = teacherAndselectedTalentProjects;
    }

   
    // Property accessors

    public String getTalentProjectId() {
        return this.talentProjectId;
    }
    
    public void setTalentProjectId(String talentProjectId) {
        this.talentProjectId = talentProjectId;
    }

    public String getTalentProjectName() {
        return this.talentProjectName;
    }
    
    public void setTalentProjectName(String talentProjectName) {
        this.talentProjectName = talentProjectName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public String getChargePersonId() {
        return this.chargePersonId;
    }
    
    public void setChargePersonId(String chargePersonId) {
        this.chargePersonId = chargePersonId;
    }

    public String getChargePerson() {
        return this.chargePerson;
    }
    
    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getCheckout() {
        return this.checkout;
    }
    
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getSelectedDate() {
        return this.selectedDate;
    }
    
    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public Set getSelectedTalentProjectScores() {
        return this.selectedTalentProjectScores;
    }
    
    public void setSelectedTalentProjectScores(Set selectedTalentProjectScores) {
        this.selectedTalentProjectScores = selectedTalentProjectScores;
    }

    public Set getTeacherAndselectedTalentProjects() {
        return this.teacherAndselectedTalentProjects;
    }
    
    public void setTeacherAndselectedTalentProjects(Set teacherAndselectedTalentProjects) {
        this.teacherAndselectedTalentProjects = teacherAndselectedTalentProjects;
    }
   








}