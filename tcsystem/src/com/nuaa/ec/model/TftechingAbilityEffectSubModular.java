package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TftechingAbilityEffectSubModular entity. @author MyEclipse Persistence Tools
 */

public class TftechingAbilityEffectSubModular  implements java.io.Serializable {


    // Fields    

     private String abilityEffectId;
     private SubModular subModular;
     private String abilityEffectName;
     private String spareTire;
     private Set tfclassTeachEvaluations = new HashSet(0);
     private Set tfteachingAbilityImproveLevels = new HashSet(0);
     private Set tfclassTeachTimes = new HashSet(0);
     private Set tfdegreeThesisGuidanceRewardLevels = new HashSet(0);
     private Set tffamousTeacherTeamRewadLevels = new HashSet(0);
     private Set tfteachingCompetitionRewardLevels = new HashSet(0);


    // Constructors

    /** default constructor */
    public TftechingAbilityEffectSubModular() {
    }

	/** minimal constructor */
    public TftechingAbilityEffectSubModular(String abilityEffectId) {
        this.abilityEffectId = abilityEffectId;
    }
    
    /** full constructor */
    public TftechingAbilityEffectSubModular(String abilityEffectId, SubModular subModular, String abilityEffectName, String spareTire, Set tfclassTeachEvaluations, Set tfteachingAbilityImproveLevels, Set tfclassTeachTimes, Set tfdegreeThesisGuidanceRewardLevels, Set tffamousTeacherTeamRewadLevels, Set tfteachingCompetitionRewardLevels) {
        this.abilityEffectId = abilityEffectId;
        this.subModular = subModular;
        this.abilityEffectName = abilityEffectName;
        this.spareTire = spareTire;
        this.tfclassTeachEvaluations = tfclassTeachEvaluations;
        this.tfteachingAbilityImproveLevels = tfteachingAbilityImproveLevels;
        this.tfclassTeachTimes = tfclassTeachTimes;
        this.tfdegreeThesisGuidanceRewardLevels = tfdegreeThesisGuidanceRewardLevels;
        this.tffamousTeacherTeamRewadLevels = tffamousTeacherTeamRewadLevels;
        this.tfteachingCompetitionRewardLevels = tfteachingCompetitionRewardLevels;
    }

   
    // Property accessors

    public String getAbilityEffectId() {
        return this.abilityEffectId;
    }
    
    public void setAbilityEffectId(String abilityEffectId) {
        this.abilityEffectId = abilityEffectId;
    }

    public SubModular getSubModular() {
        return this.subModular;
    }
    
    public void setSubModular(SubModular subModular) {
        this.subModular = subModular;
    }

    public String getAbilityEffectName() {
        return this.abilityEffectName;
    }
    
    public void setAbilityEffectName(String abilityEffectName) {
        this.abilityEffectName = abilityEffectName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTfclassTeachEvaluations() {
        return this.tfclassTeachEvaluations;
    }
    
    public void setTfclassTeachEvaluations(Set tfclassTeachEvaluations) {
        this.tfclassTeachEvaluations = tfclassTeachEvaluations;
    }

    public Set getTfteachingAbilityImproveLevels() {
        return this.tfteachingAbilityImproveLevels;
    }
    
    public void setTfteachingAbilityImproveLevels(Set tfteachingAbilityImproveLevels) {
        this.tfteachingAbilityImproveLevels = tfteachingAbilityImproveLevels;
    }

    public Set getTfclassTeachTimes() {
        return this.tfclassTeachTimes;
    }
    
    public void setTfclassTeachTimes(Set tfclassTeachTimes) {
        this.tfclassTeachTimes = tfclassTeachTimes;
    }

    public Set getTfdegreeThesisGuidanceRewardLevels() {
        return this.tfdegreeThesisGuidanceRewardLevels;
    }
    
    public void setTfdegreeThesisGuidanceRewardLevels(Set tfdegreeThesisGuidanceRewardLevels) {
        this.tfdegreeThesisGuidanceRewardLevels = tfdegreeThesisGuidanceRewardLevels;
    }

    public Set getTffamousTeacherTeamRewadLevels() {
        return this.tffamousTeacherTeamRewadLevels;
    }
    
    public void setTffamousTeacherTeamRewadLevels(Set tffamousTeacherTeamRewadLevels) {
        this.tffamousTeacherTeamRewadLevels = tffamousTeacherTeamRewadLevels;
    }

    public Set getTfteachingCompetitionRewardLevels() {
        return this.tfteachingCompetitionRewardLevels;
    }
    
    public void setTfteachingCompetitionRewardLevels(Set tfteachingCompetitionRewardLevels) {
        this.tfteachingCompetitionRewardLevels = tfteachingCompetitionRewardLevels;
    }
   








}