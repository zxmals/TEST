package com.nuaa.ec.model;



/**
 * TfteachingPaperPerformance entity. @author MyEclipse Persistence Tools
 */

public class TfteachingPaperPerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private SelfUndertakeTask selfUndertakeTask;
     private TfteachingPaperRetrievalCondition tfteachingPaperRetrievalCondition;
     private Teacher teacher;
     private String teachPaperId;
     private String teachPaperName;
     private String otherAuthorJoin;
     private Double singelScore;
     private String spareTire;
     private String checkOut;
     private Double projectSumScore;

     private String termId;

    // Constructors

    /** default constructor */
    public TfteachingPaperPerformance() {
    }

	/** minimal constructor */
    public TfteachingPaperPerformance(Integer upid, String teachPaperId) {
        this.upid = upid;
        this.teachPaperId = teachPaperId;
    }
    
    /** full constructor */
    public TfteachingPaperPerformance(Integer upid, SelfUndertakeTask selfUndertakeTask, TfteachingPaperRetrievalCondition tfteachingPaperRetrievalCondition, Teacher teacher, String teachPaperId, String teachPaperName, String otherAuthorJoin, Double singelScore, String spareTire, String checkOut, Double projectSumScore) {
        this.upid = upid;
        this.selfUndertakeTask = selfUndertakeTask;
        this.tfteachingPaperRetrievalCondition = tfteachingPaperRetrievalCondition;
        this.teacher = teacher;
        this.teachPaperId = teachPaperId;
        this.teachPaperName = teachPaperName;
        this.otherAuthorJoin = otherAuthorJoin;
        this.singelScore = singelScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
        this.projectSumScore = projectSumScore;
    }

    public TfteachingPaperPerformance(
			Integer upid,
			SelfUndertakeTask selfUndertakeTask,
			TfteachingPaperRetrievalCondition tfteachingPaperRetrievalCondition,
			Teacher teacher, String teachPaperId, String teachPaperName,
			String otherAuthorJoin, Double singelScore, String spareTire,
			String checkOut, Double projectSumScore, String termId) {
		this.upid = upid;
		this.selfUndertakeTask = selfUndertakeTask;
		this.tfteachingPaperRetrievalCondition = tfteachingPaperRetrievalCondition;
		this.teacher = teacher;
		this.teachPaperId = teachPaperId;
		this.teachPaperName = teachPaperName;
		this.otherAuthorJoin = otherAuthorJoin;
		this.singelScore = singelScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.projectSumScore = projectSumScore;
		this.termId = termId;
	}

	// Property accessors

    public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public SelfUndertakeTask getSelfUndertakeTask() {
        return this.selfUndertakeTask;
    }
    
    public void setSelfUndertakeTask(SelfUndertakeTask selfUndertakeTask) {
        this.selfUndertakeTask = selfUndertakeTask;
    }

    public TfteachingPaperRetrievalCondition getTfteachingPaperRetrievalCondition() {
        return this.tfteachingPaperRetrievalCondition;
    }
    
    public void setTfteachingPaperRetrievalCondition(TfteachingPaperRetrievalCondition tfteachingPaperRetrievalCondition) {
        this.tfteachingPaperRetrievalCondition = tfteachingPaperRetrievalCondition;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getTeachPaperId() {
        return this.teachPaperId;
    }
    
    public void setTeachPaperId(String teachPaperId) {
        this.teachPaperId = teachPaperId;
    }

    public String getTeachPaperName() {
        return this.teachPaperName;
    }
    
    public void setTeachPaperName(String teachPaperName) {
        this.teachPaperName = teachPaperName;
    }

    public String getOtherAuthorJoin() {
        return this.otherAuthorJoin;
    }
    
    public void setOtherAuthorJoin(String otherAuthorJoin) {
        this.otherAuthorJoin = otherAuthorJoin;
    }

    public Double getSingelScore() {
        return this.singelScore;
    }
    
    public void setSingelScore(Double singelScore) {
        this.singelScore = singelScore;
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

    public Double getProjectSumScore() {
        return this.projectSumScore;
    }
    
    public void setProjectSumScore(Double projectSumScore) {
        this.projectSumScore = projectSumScore;
    }

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}
   



}