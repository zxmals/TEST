package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfstudentCompetitionGuidanceRewardLevel entity. @author MyEclipse Persistence Tools
 */

public class TfstudentCompetitionGuidanceRewardLevel  implements java.io.Serializable {


    // Fields    

     private String rewardLevelId;
     private String rewardLevel;
     private String spareTire;
     private Set tfstudentCompetitionGuidanceScores = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfstudentCompetitionGuidanceRewardLevel() {
    }

	/** minimal constructor */
    public TfstudentCompetitionGuidanceRewardLevel(String rewardLevelId) {
        this.rewardLevelId = rewardLevelId;
    }
    
    /** full constructor */
    public TfstudentCompetitionGuidanceRewardLevel(String rewardLevelId, String rewardLevel, String spareTire, Set tfstudentCompetitionGuidanceScores) {
        this.rewardLevelId = rewardLevelId;
        this.rewardLevel = rewardLevel;
        this.spareTire = spareTire;
        this.tfstudentCompetitionGuidanceScores = tfstudentCompetitionGuidanceScores;
    }

   
    // Property accessors

    public String getRewardLevelId() {
        return this.rewardLevelId;
    }
    
    public void setRewardLevelId(String rewardLevelId) {
        this.rewardLevelId = rewardLevelId;
    }

    public String getRewardLevel() {
        return this.rewardLevel;
    }
    
    public void setRewardLevel(String rewardLevel) {
        this.rewardLevel = rewardLevel;
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