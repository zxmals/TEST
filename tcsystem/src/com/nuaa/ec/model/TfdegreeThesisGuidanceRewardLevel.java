package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfdegreeThesisGuidanceRewardLevel entity. @author MyEclipse Persistence Tools
 */

public class TfdegreeThesisGuidanceRewardLevel  implements java.io.Serializable {


    // Fields    

     private String rewardLevelId;
     private TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular;
     private String rewardLevel;
     private Double score;
     private String spareTire;
     private Set tfdegreeThesisGuidancePerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfdegreeThesisGuidanceRewardLevel() {
    }

	/** minimal constructor */
    public TfdegreeThesisGuidanceRewardLevel(String rewardLevelId) {
        this.rewardLevelId = rewardLevelId;
    }
    
    /** full constructor */
    public TfdegreeThesisGuidanceRewardLevel(String rewardLevelId, TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular, String rewardLevel, Double score, String spareTire, Set tfdegreeThesisGuidancePerformances) {
        this.rewardLevelId = rewardLevelId;
        this.tftechingAbilityEffectSubModular = tftechingAbilityEffectSubModular;
        this.rewardLevel = rewardLevel;
        this.score = score;
        this.spareTire = spareTire;
        this.tfdegreeThesisGuidancePerformances = tfdegreeThesisGuidancePerformances;
    }

   
    // Property accessors

    public String getRewardLevelId() {
        return this.rewardLevelId;
    }
    
    public void setRewardLevelId(String rewardLevelId) {
        this.rewardLevelId = rewardLevelId;
    }

    public TftechingAbilityEffectSubModular getTftechingAbilityEffectSubModular() {
        return this.tftechingAbilityEffectSubModular;
    }
    
    public void setTftechingAbilityEffectSubModular(TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular) {
        this.tftechingAbilityEffectSubModular = tftechingAbilityEffectSubModular;
    }

    public String getRewardLevel() {
        return this.rewardLevel;
    }
    
    public void setRewardLevel(String rewardLevel) {
        this.rewardLevel = rewardLevel;
    }

    public Double getScore() {
        return this.score;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTfdegreeThesisGuidancePerformances() {
        return this.tfdegreeThesisGuidancePerformances;
    }
    
    public void setTfdegreeThesisGuidancePerformances(Set tfdegreeThesisGuidancePerformances) {
        this.tfdegreeThesisGuidancePerformances = tfdegreeThesisGuidancePerformances;
    }
   








}