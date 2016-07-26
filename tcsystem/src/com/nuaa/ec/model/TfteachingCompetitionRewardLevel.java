package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfteachingCompetitionRewardLevel entity. @author MyEclipse Persistence Tools
 */

public class TfteachingCompetitionRewardLevel  implements java.io.Serializable {


    // Fields    

     private String competRewardLevelId;
     private TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular;
     private String competRewardLevel;
     private Double score;
     private String spareTire;
     private Set tfteachingCompetitionPerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfteachingCompetitionRewardLevel() {
    }

	/** minimal constructor */
    public TfteachingCompetitionRewardLevel(String competRewardLevelId) {
        this.competRewardLevelId = competRewardLevelId;
    }
    
    /** full constructor */
    public TfteachingCompetitionRewardLevel(String competRewardLevelId, TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular, String competRewardLevel, Double score, String spareTire, Set tfteachingCompetitionPerformances) {
        this.competRewardLevelId = competRewardLevelId;
        this.tftechingAbilityEffectSubModular = tftechingAbilityEffectSubModular;
        this.competRewardLevel = competRewardLevel;
        this.score = score;
        this.spareTire = spareTire;
        this.tfteachingCompetitionPerformances = tfteachingCompetitionPerformances;
    }

   
    // Property accessors

    public String getCompetRewardLevelId() {
        return this.competRewardLevelId;
    }
    
    public void setCompetRewardLevelId(String competRewardLevelId) {
        this.competRewardLevelId = competRewardLevelId;
    }

    public TftechingAbilityEffectSubModular getTftechingAbilityEffectSubModular() {
        return this.tftechingAbilityEffectSubModular;
    }
    
    public void setTftechingAbilityEffectSubModular(TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular) {
        this.tftechingAbilityEffectSubModular = tftechingAbilityEffectSubModular;
    }

    public String getCompetRewardLevel() {
        return this.competRewardLevel;
    }
    
    public void setCompetRewardLevel(String competRewardLevel) {
        this.competRewardLevel = competRewardLevel;
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

    public Set getTfteachingCompetitionPerformances() {
        return this.tfteachingCompetitionPerformances;
    }
    
    public void setTfteachingCompetitionPerformances(Set tfteachingCompetitionPerformances) {
        this.tfteachingCompetitionPerformances = tfteachingCompetitionPerformances;
    }
   








}