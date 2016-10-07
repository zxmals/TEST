package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * RewardLevel entity. @author MyEclipse Persistence Tools
 */

public class RewardLevel  implements java.io.Serializable {


    // Fields    

     private String rewardLevelId;
     private String rewardLevelName;
     private String spareTire;
     private Set scientificResearchRewards = new HashSet(0);
     private Set scientificResearchRewardScores = new HashSet(0);


    // Constructors

    /** default constructor */
    public RewardLevel() {
    }

	/** minimal constructor */
    public RewardLevel(String rewardLevelId) {
        this.rewardLevelId = rewardLevelId;
    }
    
    /** full constructor */
    public RewardLevel(String rewardLevelId, String rewardLevelName, String spareTire, Set scientificResearchRewards, Set scientificResearchRewardScores) {
        this.rewardLevelId = rewardLevelId;
        this.rewardLevelName = rewardLevelName;
        this.spareTire = spareTire;
        this.scientificResearchRewards = scientificResearchRewards;
        this.scientificResearchRewardScores = scientificResearchRewardScores;
    }

   
    // Property accessors

    public String getRewardLevelId() {
        return this.rewardLevelId;
    }
    
    public void setRewardLevelId(String rewardLevelId) {
        this.rewardLevelId = rewardLevelId;
    }

    public String getRewardLevelName() {
        return this.rewardLevelName;
    }
    
    public void setRewardLevelName(String rewardLevelName) {
        this.rewardLevelName = rewardLevelName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getScientificResearchRewards() {
        return this.scientificResearchRewards;
    }
    
    public void setScientificResearchRewards(Set scientificResearchRewards) {
        this.scientificResearchRewards = scientificResearchRewards;
    }

    public Set getScientificResearchRewardScores() {
        return this.scientificResearchRewardScores;
    }
    
    public void setScientificResearchRewardScores(Set scientificResearchRewardScores) {
        this.scientificResearchRewardScores = scientificResearchRewardScores;
    }
   








}