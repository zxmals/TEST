package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * PublishClub entity. @author MyEclipse Persistence Tools
 */

public class PublishClub  implements java.io.Serializable {


    // Fields    

     private String publishClubId;
     private PublishClubType publishClubType;
     private String publishClubName;
     private String spareTire;
     private Set academicWorks = new HashSet(0);


    // Constructors

    /** default constructor */
    public PublishClub() {
    }

	/** minimal constructor */
    public PublishClub(String publishClubId) {
        this.publishClubId = publishClubId;
    }
    
    /** full constructor */
    public PublishClub(String publishClubId, PublishClubType publishClubType, String publishClubName, String spareTire, Set academicWorks) {
        this.publishClubId = publishClubId;
        this.publishClubType = publishClubType;
        this.publishClubName = publishClubName;
        this.spareTire = spareTire;
        this.academicWorks = academicWorks;
    }

   
    // Property accessors

    public String getPublishClubId() {
        return this.publishClubId;
    }
    
    public void setPublishClubId(String publishClubId) {
        this.publishClubId = publishClubId;
    }

    public PublishClubType getPublishClubType() {
        return this.publishClubType;
    }
    
    public void setPublishClubType(PublishClubType publishClubType) {
        this.publishClubType = publishClubType;
    }

    public String getPublishClubName() {
        return this.publishClubName;
    }
    
    public void setPublishClubName(String publishClubName) {
        this.publishClubName = publishClubName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getAcademicWorks() {
        return this.academicWorks;
    }
    
    public void setAcademicWorks(Set academicWorks) {
        this.academicWorks = academicWorks;
    }
   








}