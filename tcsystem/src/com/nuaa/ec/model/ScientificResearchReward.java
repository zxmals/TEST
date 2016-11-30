package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * ScientificResearchReward entity. @author MyEclipse Persistence Tools
 */

public class ScientificResearchReward  implements java.io.Serializable {


    // Fields    

     private String srrewardId;
     private RewardLevel rewardLevel;
     private RewardType rewardType;
     private String srrewardName;
     private String rewardDate;
     private String awardDepartment;
     private String rewardTotalPeople;
     private String spareTire;
     private String chargePersonId;
     private String chargePerson;
     private String checkout;
     private String researchLabId;
     private Set teacherAndscientificResearchRewards = new HashSet(0);
     public ScientificResearchReward(String srrewardId, RewardLevel rewardLevel,
			RewardType rewardType, String srrewardName, String rewardDate,
			String awardDepartment, String rewardTotalPeople, String spareTire,
			String chargePersonId, String chargePerson, String checkout,
			String researchLabId, Set teacherAndscientificResearchRewards) {
		this.srrewardId = srrewardId;
		this.rewardLevel = rewardLevel;
		this.rewardType = rewardType;
		this.srrewardName = srrewardName;
		this.rewardDate = rewardDate;
		this.awardDepartment = awardDepartment;
		this.rewardTotalPeople = rewardTotalPeople;
		this.spareTire = spareTire;
		this.chargePersonId = chargePersonId;
		this.chargePerson = chargePerson;
		this.checkout = checkout;
		this.researchLabId = researchLabId;
		this.teacherAndscientificResearchRewards = teacherAndscientificResearchRewards;
	}



    // Constructors

    /** default constructor */
    public ScientificResearchReward() {
    }

	/** minimal constructor */
    public ScientificResearchReward(String srrewardId) {
        this.srrewardId = srrewardId;
    }
    
    /** full constructor */
    public ScientificResearchReward(String srrewardId, RewardLevel rewardLevel, RewardType rewardType, String srrewardName, String rewardDate, String awardDepartment, String rewardTotalPeople, String spareTire, String chargePersonId, String chargePerson, String checkout, Set teacherAndscientificResearchRewards) {
        this.srrewardId = srrewardId;
        this.rewardLevel = rewardLevel;
        this.rewardType = rewardType;
        this.srrewardName = srrewardName;
        this.rewardDate = rewardDate;
        this.awardDepartment = awardDepartment;
        this.rewardTotalPeople = rewardTotalPeople;
        this.spareTire = spareTire;
        this.chargePersonId = chargePersonId;
        this.chargePerson = chargePerson;
        this.checkout = checkout;
        this.teacherAndscientificResearchRewards = teacherAndscientificResearchRewards;
    }

   
    // Property accessors

    public String getSrrewardId() {
        return this.srrewardId;
    }
    
    public void setSrrewardId(String srrewardId) {
        this.srrewardId = srrewardId;
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

    public String getSrrewardName() {
        return this.srrewardName;
    }
    
    public void setSrrewardName(String srrewardName) {
        this.srrewardName = srrewardName;
    }

    public String getRewardDate() {
        return this.rewardDate;
    }
    
    public void setRewardDate(String rewardDate) {
        this.rewardDate = rewardDate;
    }

    public String getAwardDepartment() {
        return this.awardDepartment;
    }
    
    public void setAwardDepartment(String awardDepartment) {
        this.awardDepartment = awardDepartment;
    }

    public String getRewardTotalPeople() {
        return this.rewardTotalPeople;
    }
    
    public void setRewardTotalPeople(String rewardTotalPeople) {
        this.rewardTotalPeople = rewardTotalPeople;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public String getChargePersonId() {
        return this.chargePersonId;
    }
    
    public void setChargePersonId(String chargePersonId) {
        this.chargePersonId = chargePersonId;
    }

    public String getChargePerson() {
        return this.chargePerson;
    }
    
    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getCheckout() {
        return this.checkout;
    }
    
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public Set getTeacherAndscientificResearchRewards() {
        return this.teacherAndscientificResearchRewards;
    }
    
    public void setTeacherAndscientificResearchRewards(Set teacherAndscientificResearchRewards) {
        this.teacherAndscientificResearchRewards = teacherAndscientificResearchRewards;
    }

	public String getResearchLabId() {
		return researchLabId;
	}

	public void setResearchLabId(String researchLabId) {
		this.researchLabId = researchLabId;
	}
   








}