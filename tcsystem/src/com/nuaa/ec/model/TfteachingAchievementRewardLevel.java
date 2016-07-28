package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfteachingAchievementRewardLevel entity. @author MyEclipse Persistence Tools
 */

public class TfteachingAchievementRewardLevel  implements java.io.Serializable {


    // Fields    

     private String rewardId;
     private TfteachingReformResearchSubModular tfteachingReformResearchSubModular;
     private String rewardName;
     private Double score;
     private String spareTire;
     private Set tfteachingAchievementPerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfteachingAchievementRewardLevel() {
    }

	/** minimal constructor */
    public TfteachingAchievementRewardLevel(String rewardId) {
        this.rewardId = rewardId;
    }
    
    /** full constructor */
    public TfteachingAchievementRewardLevel(String rewardId, TfteachingReformResearchSubModular tfteachingReformResearchSubModular, String rewardName, Double score, String spareTire, Set tfteachingAchievementPerformances) {
        this.rewardId = rewardId;
        this.tfteachingReformResearchSubModular = tfteachingReformResearchSubModular;
        this.rewardName = rewardName;
        this.score = score;
        this.spareTire = spareTire;
        this.tfteachingAchievementPerformances = tfteachingAchievementPerformances;
    }

   
    // Property accessors

    public String getRewardId() {
        return this.rewardId;
    }
    
    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public TfteachingReformResearchSubModular getTfteachingReformResearchSubModular() {
        return this.tfteachingReformResearchSubModular;
    }
    
    public void setTfteachingReformResearchSubModular(TfteachingReformResearchSubModular tfteachingReformResearchSubModular) {
        this.tfteachingReformResearchSubModular = tfteachingReformResearchSubModular;
    }

    public String getRewardName() {
        return this.rewardName;
    }
    
    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
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

    public Set getTfteachingAchievementPerformances() {
        return this.tfteachingAchievementPerformances;
    }
    
    public void setTfteachingAchievementPerformances(Set tfteachingAchievementPerformances) {
        this.tfteachingAchievementPerformances = tfteachingAchievementPerformances;
    }
   








}