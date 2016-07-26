package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfstudentCompetitionGuidanceScore entity. @author MyEclipse Persistence Tools
 */

public class TfstudentCompetitionGuidanceScore  implements java.io.Serializable {


    // Fields    

     private String scguidaceScoreId;
     private TfstudentCompetitionGuidanceCompetitionType tfstudentCompetitionGuidanceCompetitionType;
     private TfstudentCompetitionGuidanceRewardLevel tfstudentCompetitionGuidanceRewardLevel;
     private Double baseScore;
     private String spareTire;
     private Set tfstudentCompetitionGuidancePerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfstudentCompetitionGuidanceScore() {
    }

	/** minimal constructor */
    public TfstudentCompetitionGuidanceScore(String scguidaceScoreId) {
        this.scguidaceScoreId = scguidaceScoreId;
    }
    
    /** full constructor */
    public TfstudentCompetitionGuidanceScore(String scguidaceScoreId, TfstudentCompetitionGuidanceCompetitionType tfstudentCompetitionGuidanceCompetitionType, TfstudentCompetitionGuidanceRewardLevel tfstudentCompetitionGuidanceRewardLevel, Double baseScore, String spareTire, Set tfstudentCompetitionGuidancePerformances) {
        this.scguidaceScoreId = scguidaceScoreId;
        this.tfstudentCompetitionGuidanceCompetitionType = tfstudentCompetitionGuidanceCompetitionType;
        this.tfstudentCompetitionGuidanceRewardLevel = tfstudentCompetitionGuidanceRewardLevel;
        this.baseScore = baseScore;
        this.spareTire = spareTire;
        this.tfstudentCompetitionGuidancePerformances = tfstudentCompetitionGuidancePerformances;
    }

   
    // Property accessors

    public String getScguidaceScoreId() {
        return this.scguidaceScoreId;
    }
    
    public void setScguidaceScoreId(String scguidaceScoreId) {
        this.scguidaceScoreId = scguidaceScoreId;
    }

    public TfstudentCompetitionGuidanceCompetitionType getTfstudentCompetitionGuidanceCompetitionType() {
        return this.tfstudentCompetitionGuidanceCompetitionType;
    }
    
    public void setTfstudentCompetitionGuidanceCompetitionType(TfstudentCompetitionGuidanceCompetitionType tfstudentCompetitionGuidanceCompetitionType) {
        this.tfstudentCompetitionGuidanceCompetitionType = tfstudentCompetitionGuidanceCompetitionType;
    }

    public TfstudentCompetitionGuidanceRewardLevel getTfstudentCompetitionGuidanceRewardLevel() {
        return this.tfstudentCompetitionGuidanceRewardLevel;
    }
    
    public void setTfstudentCompetitionGuidanceRewardLevel(TfstudentCompetitionGuidanceRewardLevel tfstudentCompetitionGuidanceRewardLevel) {
        this.tfstudentCompetitionGuidanceRewardLevel = tfstudentCompetitionGuidanceRewardLevel;
    }

    public Double getBaseScore() {
        return this.baseScore;
    }
    
    public void setBaseScore(Double baseScore) {
        this.baseScore = baseScore;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTfstudentCompetitionGuidancePerformances() {
        return this.tfstudentCompetitionGuidancePerformances;
    }
    
    public void setTfstudentCompetitionGuidancePerformances(Set tfstudentCompetitionGuidancePerformances) {
        this.tfstudentCompetitionGuidancePerformances = tfstudentCompetitionGuidancePerformances;
    }
   








}