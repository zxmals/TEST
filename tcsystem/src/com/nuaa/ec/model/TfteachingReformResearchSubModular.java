package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfteachingReformResearchSubModular entity. @author MyEclipse Persistence Tools
 */

public class TfteachingReformResearchSubModular  implements java.io.Serializable {


    // Fields    

     private String reformResearchId;
     private SubModular subModular;
     private String reformResearchName;
     private String spareTire;
     private Set tfteachingAchievementRewardLevels = new HashSet(0);
     private Set tfteachingRearchFundlevels = new HashSet(0);
     private Set tfteachingPaperRetrievalConditions = new HashSet(0);
     private Set tfteachingRearchEvaluations = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfteachingReformResearchSubModular() {
    }

	/** minimal constructor */
    public TfteachingReformResearchSubModular(String reformResearchId, SubModular subModular) {
        this.reformResearchId = reformResearchId;
        this.subModular = subModular;
    }
    
    /** full constructor */
    public TfteachingReformResearchSubModular(String reformResearchId, SubModular subModular, String reformResearchName, String spareTire, Set tfteachingAchievementRewardLevels, Set tfteachingRearchFundlevels, Set tfteachingPaperRetrievalConditions, Set tfteachingRearchEvaluations) {
        this.reformResearchId = reformResearchId;
        this.subModular = subModular;
        this.reformResearchName = reformResearchName;
        this.spareTire = spareTire;
        this.tfteachingAchievementRewardLevels = tfteachingAchievementRewardLevels;
        this.tfteachingRearchFundlevels = tfteachingRearchFundlevels;
        this.tfteachingPaperRetrievalConditions = tfteachingPaperRetrievalConditions;
        this.tfteachingRearchEvaluations = tfteachingRearchEvaluations;
    }

   
    // Property accessors

    public String getReformResearchId() {
        return this.reformResearchId;
    }
    
    public void setReformResearchId(String reformResearchId) {
        this.reformResearchId = reformResearchId;
    }

    public SubModular getSubModular() {
        return this.subModular;
    }
    
    public void setSubModular(SubModular subModular) {
        this.subModular = subModular;
    }

    public String getReformResearchName() {
        return this.reformResearchName;
    }
    
    public void setReformResearchName(String reformResearchName) {
        this.reformResearchName = reformResearchName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTfteachingAchievementRewardLevels() {
        return this.tfteachingAchievementRewardLevels;
    }
    
    public void setTfteachingAchievementRewardLevels(Set tfteachingAchievementRewardLevels) {
        this.tfteachingAchievementRewardLevels = tfteachingAchievementRewardLevels;
    }

    public Set getTfteachingRearchFundlevels() {
        return this.tfteachingRearchFundlevels;
    }
    
    public void setTfteachingRearchFundlevels(Set tfteachingRearchFundlevels) {
        this.tfteachingRearchFundlevels = tfteachingRearchFundlevels;
    }

    public Set getTfteachingPaperRetrievalConditions() {
        return this.tfteachingPaperRetrievalConditions;
    }
    
    public void setTfteachingPaperRetrievalConditions(Set tfteachingPaperRetrievalConditions) {
        this.tfteachingPaperRetrievalConditions = tfteachingPaperRetrievalConditions;
    }

    public Set getTfteachingRearchEvaluations() {
        return this.tfteachingRearchEvaluations;
    }
    
    public void setTfteachingRearchEvaluations(Set tfteachingRearchEvaluations) {
        this.tfteachingRearchEvaluations = tfteachingRearchEvaluations;
    }
   








}