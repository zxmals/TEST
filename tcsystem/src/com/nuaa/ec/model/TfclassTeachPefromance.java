package com.nuaa.ec.model;



/**
 * TfclassTeachPefromance entity. @author MyEclipse Persistence Tools
 */

public class TfclassTeachPefromance  implements java.io.Serializable {


    // Fields    

     private String classPefromanceId;
     private TfclassTeachEvaluation tfclassTeachEvaluation;
     private TfclassTeachTime tfclassTeachTime;
     private Teacher teacher;
     private String sumtime;
     private Double finalScore;
     private String checkOut;
     private String spareTire;
     private String termId;

    // Constructors

    /** default constructor */
    public TfclassTeachPefromance() {
    }

	/** minimal constructor */
    public TfclassTeachPefromance(String classPefromanceId) {
        this.classPefromanceId = classPefromanceId;
    }
    
    /** full constructor */
    public TfclassTeachPefromance(String classPefromanceId, TfclassTeachEvaluation tfclassTeachEvaluation, TfclassTeachTime tfclassTeachTime, Teacher teacher, String sumtime, Double finalScore, String checkOut, String spareTire) {
    	this.classPefromanceId = classPefromanceId;
    	this.tfclassTeachEvaluation = tfclassTeachEvaluation;
    	this.tfclassTeachTime = tfclassTeachTime;
    	this.teacher = teacher;
    	this.sumtime = sumtime;
    	this.finalScore = finalScore;
    	this.checkOut = checkOut;
    	this.spareTire = spareTire;
    }
    public TfclassTeachPefromance(String classPefromanceId, TfclassTeachEvaluation tfclassTeachEvaluation, TfclassTeachTime tfclassTeachTime, Teacher teacher, String sumtime, Double finalScore, String checkOut, String spareTire,String termId) {
        this.classPefromanceId = classPefromanceId;
        this.tfclassTeachEvaluation = tfclassTeachEvaluation;
        this.tfclassTeachTime = tfclassTeachTime;
        this.teacher = teacher;
        this.sumtime = sumtime;
        this.finalScore = finalScore;
        this.checkOut = checkOut;
        this.spareTire = spareTire;
        this.termId=termId;
    }

   
    // Property accessors

    public String getClassPefromanceId() {
        return this.classPefromanceId;
    }
    
    public void setClassPefromanceId(String classPefromanceId) {
        this.classPefromanceId = classPefromanceId;
    }

    public TfclassTeachEvaluation getTfclassTeachEvaluation() {
        return this.tfclassTeachEvaluation;
    }
    
    public void setTfclassTeachEvaluation(TfclassTeachEvaluation tfclassTeachEvaluation) {
        this.tfclassTeachEvaluation = tfclassTeachEvaluation;
    }

    public TfclassTeachTime getTfclassTeachTime() {
        return this.tfclassTeachTime;
    }
    
    public void setTfclassTeachTime(TfclassTeachTime tfclassTeachTime) {
        this.tfclassTeachTime = tfclassTeachTime;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getSumtime() {
        return this.sumtime;
    }
    
    public void setSumtime(String sumtime) {
        this.sumtime = sumtime;
    }

    public Double getFinalScore() {
        return this.finalScore;
    }
    
    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public String getCheckOut() {
        return this.checkOut;
    }
    
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}
   








}