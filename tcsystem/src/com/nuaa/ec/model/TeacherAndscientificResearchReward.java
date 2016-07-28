package com.nuaa.ec.model;



/**
 * TeacherAndscientificResearchReward entity. @author MyEclipse Persistence Tools
 */

public class TeacherAndscientificResearchReward  implements java.io.Serializable {


    // Fields    

     private Integer teacherAsrrid;
     private ScientificResearchRewardScore scientificResearchRewardScore;
     private ScientificResearchReward scientificResearchReward;
     private Teacher teacher;
     private String rewardDate;
     private String selfRanking;
     private Double finalScore;
     private String spareTire;
     private String checkOut;


    // Constructors

    /** default constructor */
    public TeacherAndscientificResearchReward() {
    }

	/** minimal constructor */
    public TeacherAndscientificResearchReward(Integer teacherAsrrid) {
        this.teacherAsrrid = teacherAsrrid;
    }
    
    /** full constructor */
    public TeacherAndscientificResearchReward(Integer teacherAsrrid, ScientificResearchRewardScore scientificResearchRewardScore, ScientificResearchReward scientificResearchReward, Teacher teacher, String rewardDate, String selfRanking, Double finalScore, String spareTire, String checkOut) {
        this.teacherAsrrid = teacherAsrrid;
        this.scientificResearchRewardScore = scientificResearchRewardScore;
        this.scientificResearchReward = scientificResearchReward;
        this.teacher = teacher;
        this.rewardDate = rewardDate;
        this.selfRanking = selfRanking;
        this.finalScore = finalScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
    }

   
    // Property accessors

    public Integer getTeacherAsrrid() {
        return this.teacherAsrrid;
    }
    
    public void setTeacherAsrrid(Integer teacherAsrrid) {
        this.teacherAsrrid = teacherAsrrid;
    }

    public ScientificResearchRewardScore getScientificResearchRewardScore() {
        return this.scientificResearchRewardScore;
    }
    
    public void setScientificResearchRewardScore(ScientificResearchRewardScore scientificResearchRewardScore) {
        this.scientificResearchRewardScore = scientificResearchRewardScore;
    }

    public ScientificResearchReward getScientificResearchReward() {
        return this.scientificResearchReward;
    }
    
    public void setScientificResearchReward(ScientificResearchReward scientificResearchReward) {
        this.scientificResearchReward = scientificResearchReward;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getRewardDate() {
        return this.rewardDate;
    }
    
    public void setRewardDate(String rewardDate) {
        this.rewardDate = rewardDate;
    }

    public String getSelfRanking() {
        return this.selfRanking;
    }
    
    public void setSelfRanking(String selfRanking) {
        this.selfRanking = selfRanking;
    }

    public Double getFinalScore() {
        return this.finalScore;
    }
    
    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public String getCheckOut() {
        return this.checkOut;
    }
    
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
   








}