package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * PublishClubType entity. @author MyEclipse Persistence Tools
 */

public class PublishClubType  implements java.io.Serializable {


    // Fields    

     private String pculbTypeId;
     private String publishType;
     private String spareTire;
     private Set publishClubs = new HashSet(0);


    // Constructors

    /** default constructor */
    public PublishClubType() {
    }

	/** minimal constructor */
    public PublishClubType(String pculbTypeId) {
        this.pculbTypeId = pculbTypeId;
    }
    
    /** full constructor */
    public PublishClubType(String pculbTypeId, String publishType, String spareTire, Set publishClubs) {
        this.pculbTypeId = pculbTypeId;
        this.publishType = publishType;
        this.spareTire = spareTire;
        this.publishClubs = publishClubs;
    }

   
    // Property accessors

    public String getPculbTypeId() {
        return this.pculbTypeId;
    }
    
    public void setPculbTypeId(String pculbTypeId) {
        this.pculbTypeId = pculbTypeId;
    }

    public String getPublishType() {
        return this.publishType;
    }
    
    public void setPublishType(String publishType) {
        this.publishType = publishType;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getPublishClubs() {
        return this.publishClubs;
    }
    
    public void setPublishClubs(Set publishClubs) {
        this.publishClubs = publishClubs;
    }
   








}