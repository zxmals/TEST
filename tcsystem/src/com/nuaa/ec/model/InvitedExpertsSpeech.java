package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * InvitedExpertsSpeech entity. @author MyEclipse Persistence Tools
 */

public class InvitedExpertsSpeech  implements java.io.Serializable {


    // Fields    

     private String iespeechId;
     private Nationality nationality;
     private ExpertType expertType;
     private String expertsName;
     private String lectureName;
     private String spareTire;
     private String chargePersonId;
     private String chargePerson;
     private String checkout;
     private String speechDate;
     private Set teacherAndinvitedExpertsSpeechs = new HashSet(0);


    // Constructors

    /** default constructor */
    public InvitedExpertsSpeech() {
    }

	/** minimal constructor */
    public InvitedExpertsSpeech(String iespeechId) {
        this.iespeechId = iespeechId;
    }
    
    /** full constructor */
    public InvitedExpertsSpeech(String iespeechId, Nationality nationality, ExpertType expertType, String expertsName, String lectureName, String spareTire, String chargePersonId, String chargePerson, String checkout, String speechDate, Set teacherAndinvitedExpertsSpeechs) {
        this.iespeechId = iespeechId;
        this.nationality = nationality;
        this.expertType = expertType;
        this.expertsName = expertsName;
        this.lectureName = lectureName;
        this.spareTire = spareTire;
        this.chargePersonId = chargePersonId;
        this.chargePerson = chargePerson;
        this.checkout = checkout;
        this.speechDate = speechDate;
        this.teacherAndinvitedExpertsSpeechs = teacherAndinvitedExpertsSpeechs;
    }

   
    // Property accessors

    public String getIespeechId() {
        return this.iespeechId;
    }
    
    public void setIespeechId(String iespeechId) {
        this.iespeechId = iespeechId;
    }

    public Nationality getNationality() {
        return this.nationality;
    }
    
    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public ExpertType getExpertType() {
        return this.expertType;
    }
    
    public void setExpertType(ExpertType expertType) {
        this.expertType = expertType;
    }

    public String getExpertsName() {
        return this.expertsName;
    }
    
    public void setExpertsName(String expertsName) {
        this.expertsName = expertsName;
    }

    public String getLectureName() {
        return this.lectureName;
    }
    
    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
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

    public String getSpeechDate() {
        return this.speechDate;
    }
    
    public void setSpeechDate(String speechDate) {
        this.speechDate = speechDate;
    }

    public Set getTeacherAndinvitedExpertsSpeechs() {
        return this.teacherAndinvitedExpertsSpeechs;
    }
    
    public void setTeacherAndinvitedExpertsSpeechs(Set teacherAndinvitedExpertsSpeechs) {
        this.teacherAndinvitedExpertsSpeechs = teacherAndinvitedExpertsSpeechs;
    }
   








}