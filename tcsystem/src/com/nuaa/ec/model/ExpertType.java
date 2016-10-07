package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * ExpertType entity. @author MyEclipse Persistence Tools
 */

public class ExpertType  implements java.io.Serializable {


    // Fields    

     private String expertTypeId;
     private String expertTypeName;
     private String spareTire;
     private Set invitedExpertsSpeechScores = new HashSet(0);
     private Set invitedExpertsSpeechs = new HashSet(0);


    // Constructors

    /** default constructor */
    public ExpertType() {
    }

	/** minimal constructor */
    public ExpertType(String expertTypeId) {
        this.expertTypeId = expertTypeId;
    }
    
    /** full constructor */
    public ExpertType(String expertTypeId, String expertTypeName, String spareTire, Set invitedExpertsSpeechScores, Set invitedExpertsSpeechs) {
        this.expertTypeId = expertTypeId;
        this.expertTypeName = expertTypeName;
        this.spareTire = spareTire;
        this.invitedExpertsSpeechScores = invitedExpertsSpeechScores;
        this.invitedExpertsSpeechs = invitedExpertsSpeechs;
    }

   
    // Property accessors

    public String getExpertTypeId() {
        return this.expertTypeId;
    }
    
    public void setExpertTypeId(String expertTypeId) {
        this.expertTypeId = expertTypeId;
    }

    public String getExpertTypeName() {
        return this.expertTypeName;
    }
    
    public void setExpertTypeName(String expertTypeName) {
        this.expertTypeName = expertTypeName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getInvitedExpertsSpeechScores() {
        return this.invitedExpertsSpeechScores;
    }
    
    public void setInvitedExpertsSpeechScores(Set invitedExpertsSpeechScores) {
        this.invitedExpertsSpeechScores = invitedExpertsSpeechScores;
    }

    public Set getInvitedExpertsSpeechs() {
        return this.invitedExpertsSpeechs;
    }
    
    public void setInvitedExpertsSpeechs(Set invitedExpertsSpeechs) {
        this.invitedExpertsSpeechs = invitedExpertsSpeechs;
    }
   








}