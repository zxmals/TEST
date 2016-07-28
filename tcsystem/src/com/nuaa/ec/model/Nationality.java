package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * Nationality entity. @author MyEclipse Persistence Tools
 */

public class Nationality  implements java.io.Serializable {


    // Fields    

     private String countryId;
     private String countryName;
     private String spareTire;
     private Set invitedExpertsSpeechs = new HashSet(0);


    // Constructors

    /** default constructor */
    public Nationality() {
    }

	/** minimal constructor */
    public Nationality(String countryId) {
        this.countryId = countryId;
    }
    
    /** full constructor */
    public Nationality(String countryId, String countryName, String spareTire, Set invitedExpertsSpeechs) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.spareTire = spareTire;
        this.invitedExpertsSpeechs = invitedExpertsSpeechs;
    }

   
    // Property accessors

    public String getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return this.countryName;
    }
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getInvitedExpertsSpeechs() {
        return this.invitedExpertsSpeechs;
    }
    
    public void setInvitedExpertsSpeechs(Set invitedExpertsSpeechs) {
        this.invitedExpertsSpeechs = invitedExpertsSpeechs;
    }
   








}