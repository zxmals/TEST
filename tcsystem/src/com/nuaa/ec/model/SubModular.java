package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * SubModular entity. @author MyEclipse Persistence Tools
 */

public class SubModular  implements java.io.Serializable {


    // Fields    

     private String subModularId;
     private Modular modular;
     private String subModularName;
     private String spareTire;
     private Set scientificResearchProjectScores = new HashSet(0);
     private Set periodicalPapersScores = new HashSet(0);
     private Set mainUndertakeAcademicMeetingScores = new HashSet(0);
     private Set tfteachingReformResearchSubModulars = new HashSet(0);
     private Set joinAcademicMeetingScores = new HashSet(0);
     private Set tfteachGuidanceSubModulars = new HashSet(0);
     private Set invitedExpertsSpeechScores = new HashSet(0);
     private Set academicWorkScores = new HashSet(0);
     private Set scientificResearchRewardScores = new HashSet(0);
     private Set tftechingAbilityEffectSubModulars = new HashSet(0);


    // Constructors

    /** default constructor */
    public SubModular() {
    }

	/** minimal constructor */
    public SubModular(String subModularId, Modular modular) {
        this.subModularId = subModularId;
        this.modular = modular;
    }
    
    /** full constructor */
    public SubModular(String subModularId, Modular modular, String subModularName, String spareTire, Set scientificResearchProjectScores, Set periodicalPapersScores, Set mainUndertakeAcademicMeetingScores, Set tfteachingReformResearchSubModulars, Set joinAcademicMeetingScores, Set tfteachGuidanceSubModulars, Set invitedExpertsSpeechScores, Set academicWorkScores, Set scientificResearchRewardScores, Set tftechingAbilityEffectSubModulars) {
        this.subModularId = subModularId;
        this.modular = modular;
        this.subModularName = subModularName;
        this.spareTire = spareTire;
        this.scientificResearchProjectScores = scientificResearchProjectScores;
        this.periodicalPapersScores = periodicalPapersScores;
        this.mainUndertakeAcademicMeetingScores = mainUndertakeAcademicMeetingScores;
        this.tfteachingReformResearchSubModulars = tfteachingReformResearchSubModulars;
        this.joinAcademicMeetingScores = joinAcademicMeetingScores;
        this.tfteachGuidanceSubModulars = tfteachGuidanceSubModulars;
        this.invitedExpertsSpeechScores = invitedExpertsSpeechScores;
        this.academicWorkScores = academicWorkScores;
        this.scientificResearchRewardScores = scientificResearchRewardScores;
        this.tftechingAbilityEffectSubModulars = tftechingAbilityEffectSubModulars;
    }

   
    // Property accessors

    public String getSubModularId() {
        return this.subModularId;
    }
    
    public void setSubModularId(String subModularId) {
        this.subModularId = subModularId;
    }

    public Modular getModular() {
        return this.modular;
    }
    
    public void setModular(Modular modular) {
        this.modular = modular;
    }

    public String getSubModularName() {
        return this.subModularName;
    }
    
    public void setSubModularName(String subModularName) {
        this.subModularName = subModularName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getScientificResearchProjectScores() {
        return this.scientificResearchProjectScores;
    }
    
    public void setScientificResearchProjectScores(Set scientificResearchProjectScores) {
        this.scientificResearchProjectScores = scientificResearchProjectScores;
    }

    public Set getPeriodicalPapersScores() {
        return this.periodicalPapersScores;
    }
    
    public void setPeriodicalPapersScores(Set periodicalPapersScores) {
        this.periodicalPapersScores = periodicalPapersScores;
    }

    public Set getMainUndertakeAcademicMeetingScores() {
        return this.mainUndertakeAcademicMeetingScores;
    }
    
    public void setMainUndertakeAcademicMeetingScores(Set mainUndertakeAcademicMeetingScores) {
        this.mainUndertakeAcademicMeetingScores = mainUndertakeAcademicMeetingScores;
    }

    public Set getTfteachingReformResearchSubModulars() {
        return this.tfteachingReformResearchSubModulars;
    }
    
    public void setTfteachingReformResearchSubModulars(Set tfteachingReformResearchSubModulars) {
        this.tfteachingReformResearchSubModulars = tfteachingReformResearchSubModulars;
    }

    public Set getJoinAcademicMeetingScores() {
        return this.joinAcademicMeetingScores;
    }
    
    public void setJoinAcademicMeetingScores(Set joinAcademicMeetingScores) {
        this.joinAcademicMeetingScores = joinAcademicMeetingScores;
    }

    public Set getTfteachGuidanceSubModulars() {
        return this.tfteachGuidanceSubModulars;
    }
    
    public void setTfteachGuidanceSubModulars(Set tfteachGuidanceSubModulars) {
        this.tfteachGuidanceSubModulars = tfteachGuidanceSubModulars;
    }

    public Set getInvitedExpertsSpeechScores() {
        return this.invitedExpertsSpeechScores;
    }
    
    public void setInvitedExpertsSpeechScores(Set invitedExpertsSpeechScores) {
        this.invitedExpertsSpeechScores = invitedExpertsSpeechScores;
    }

    public Set getAcademicWorkScores() {
        return this.academicWorkScores;
    }
    
    public void setAcademicWorkScores(Set academicWorkScores) {
        this.academicWorkScores = academicWorkScores;
    }

    public Set getScientificResearchRewardScores() {
        return this.scientificResearchRewardScores;
    }
    
    public void setScientificResearchRewardScores(Set scientificResearchRewardScores) {
        this.scientificResearchRewardScores = scientificResearchRewardScores;
    }

    public Set getTftechingAbilityEffectSubModulars() {
        return this.tftechingAbilityEffectSubModulars;
    }
    
    public void setTftechingAbilityEffectSubModulars(Set tftechingAbilityEffectSubModulars) {
        this.tftechingAbilityEffectSubModulars = tftechingAbilityEffectSubModulars;
    }
   








}