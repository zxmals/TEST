package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * RewardType entity. @author MyEclipse Persistence Tools
 */

public class RewardType  implements java.io.Serializable {


    // Fields    

     private String rewardTypeId;
     private String rewardTypeName;
     private String spareTire;
     private Set scientificResearchRewards = new HashSet(0);
     private Set scientificResearchRewardScores = new HashSet(0);


    // Constructors

    /** default constructor */
    public RewardType() {
    }

	/** minimal constructor */
    public RewardType(String rewardTypeId) {
        this.rewardTypeId = rewardTypeId;
    }
    
    /** full constructor */
    public RewardType(String rewardTypeId, String rewardTypeName, String spareTire, Set scientificResearchRewards, Set scientificResearchRewardScores) {
        this.rewardTypeId = rewardTypeId;
        this.rewardTypeName = rewardTypeName;
        this.spareTire = spareTire;
        this.scientificResearchRewards = scientificResearchRewards;
        this.scientificResearchRewardScores = scientificResearchRewardScores;
    }

   
    // Property accessors

    public String getRewardTypeId() {
        return this.rewardTypeId;
    }
    
    public void setRewardTypeId(String rewardTypeId) {
        this.rewardTypeId = rewardTypeId;
    }

    public String getRewardTypeName() {
        return this.rewardTypeName;
    }
    
    public void setRewardTypeName(String rewardTypeName) {
        this.rewardTypeName = rewardTypeName;
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