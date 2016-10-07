package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * ScientificResearchRewardScore entity. @author MyEclipse Persistence Tools
 */

public class ScientificResearchRewardScore  implements java.io.Serializable {


    // Fields    

     private String srrscoreId;
     private SubModular subModular;
     private RewardLevel rewardLevel;
     private RewardType rewardType;
     private Long score;
     private String spareTire;
     private Set teacherAndscientificResearchRewards = new HashSet(0);


    // Constructors

    /** default constructor */
    public ScientificResearchRewardScore() {
    }

	/** minimal constructor */
    public ScientificResearchRewardScore(String srrscoreId) {
        this.srrscoreId = srrscoreId;
    }
    
    /** full constructor */
    public ScientificResearchRewardScore(String srrscoreId, SubModular subModular, RewardLevel rewardLevel, RewardType rewardType, Long score, String spareTire, Set teacherAndscientificResearchRewards) {
        this.srrscoreId = srrscoreId;
        this.subModular = subModular;
        this.rewardLevel = rewardLevel;
        this.rewardType = rewardType;
        this.score = score;
        this.spareTire = spareTire;
        this.teacherAndscientificResearchRewards = teacherAndscientificResearchRewards;
    }

   
    // Property accessors

    public String getSrrscoreId() {
        return this.srrscoreId;
    }
    
    public void setSrrscoreId(String srrscoreId) {
        this.srrscoreId = srrscoreId;
    }

    public SubModular getSubModular() {
        return this.subModular;
    }
    
    public void setSubModular(SubModular subModular) {
        this.subModular = subModular;
    }

    public RewardLevel getRewardLevel() {
        return this.rewardLevel;
    }
    
    public void setRewardLevel(RewardLevel rewardLevel) {
        this.rewardLevel = rewardLevel;
    }

    public RewardType getRewardType() {
        return this.rewardType;
    }
    
    public void setRewardType(RewardType rewardType) {
        this.rewardType = rewardType;
    }

    public Long getScore() {
        return this.score;
    }
    
    public void setScore(Long score) {
        this.score = score;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTeacherAndscientificResearchRewards() {
        return this.teacherAndscientificResearchRewards;
    }
    
    public void setTeacherAndscientificResearchRewards(Set teacherAndscientificResearchRewards) {
        this.teacherAndscientificResearchRewards = teacherAndscientificResearchRewards;
    }
   








}