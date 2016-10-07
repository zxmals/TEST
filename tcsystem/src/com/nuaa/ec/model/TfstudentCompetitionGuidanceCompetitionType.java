package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfstudentCompetitionGuidanceCompetitionType entity. @author MyEclipse Persistence Tools
 */

public class TfstudentCompetitionGuidanceCompetitionType  implements java.io.Serializable {


    // Fields    

     private String competitionTypeId;
     private String competitionType;
     private String spareTire;
     private Set tfstudentCompetitionGuidanceScores = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfstudentCompetitionGuidanceCompetitionType() {
    }

	/** minimal constructor */
    public TfstudentCompetitionGuidanceCompetitionType(String competitionTypeId) {
        this.competitionTypeId = competitionTypeId;
    }
    
    /** full constructor */
    public TfstudentCompetitionGuidanceCompetitionType(String competitionTypeId, String competitionType, String spareTire, Set tfstudentCompetitionGuidanceScores) {
        this.competitionTypeId = competitionTypeId;
        this.competitionType = competitionType;
        this.spareTire = spareTire;
        this.tfstudentCompetitionGuidanceScores = tfstudentCompetitionGuidanceScores;
    }

   
    // Property accessors

    public String getCompetitionTypeId() {
        return this.competitionTypeId;
    }
    
    public void setCompetitionTypeId(String competitionTypeId) {
        this.competitionTypeId = competitionTypeId;
    }

    public String getCompetitionType() {
        return this.competitionType;
    }
    
    public void setCompetitionType(String competitionType) {
        this.competitionType = competitionType;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTfstudentCompetitionGuidanceScores() {
        return this.tfstudentCompetitionGuidanceScores;
    }
    
    public void setTfstudentCompetitionGuidanceScores(Set tfstudentCompetitionGuidanceScores) {
        this.tfstudentCompetitionGuidanceScores = tfstudentCompetitionGuidanceScores;
    }
   








}