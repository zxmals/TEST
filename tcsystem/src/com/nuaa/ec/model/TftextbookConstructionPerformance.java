package com.nuaa.ec.model;



/**
 * TftextbookConstructionPerformance entity. @author MyEclipse Persistence Tools
 */

public class TftextbookConstructionPerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private SelfUndertakeTask selfUndertakeTask;
     private TftextbookConstructionTblevel tftextbookConstructionTblevel;
     private Teacher teacher;
     private String bookId;
     private String bookName;
     private Double singellScore;
     private String spareTire;
     private String checkOut;
     private String cooperator;
     private Double projectSumScore;

     private String termId;

    // Constructors

    /** default constructor */
    public TftextbookConstructionPerformance() {
    }

	/** minimal constructor */
    public TftextbookConstructionPerformance(Integer upid, String bookId) {
        this.upid = upid;
        this.bookId = bookId;
    }
    
    /** full constructor */
    public TftextbookConstructionPerformance(Integer upid, SelfUndertakeTask selfUndertakeTask, TftextbookConstructionTblevel tftextbookConstructionTblevel, Teacher teacher, String bookId, String bookName, Double singellScore, String spareTire, String checkOut, String cooperator, Double projectSumScore) {
        this.upid = upid;
        this.selfUndertakeTask = selfUndertakeTask;
        this.tftextbookConstructionTblevel = tftextbookConstructionTblevel;
        this.teacher = teacher;
        this.bookId = bookId;
        this.bookName = bookName;
        this.singellScore = singellScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
        this.cooperator = cooperator;
        this.projectSumScore = projectSumScore;
    }

   
    
    public TftextbookConstructionPerformance(Integer upid,
			SelfUndertakeTask selfUndertakeTask,
			TftextbookConstructionTblevel tftextbookConstructionTblevel,
			Teacher teacher, String bookId, String bookName,
			Double singellScore, String spareTire, String checkOut,
			String cooperator, Double projectSumScore, String termId) {
		this.upid = upid;
		this.selfUndertakeTask = selfUndertakeTask;
		this.tftextbookConstructionTblevel = tftextbookConstructionTblevel;
		this.teacher = teacher;
		this.bookId = bookId;
		this.bookName = bookName;
		this.singellScore = singellScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.cooperator = cooperator;
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

    public TftextbookConstructionTblevel getTftextbookConstructionTblevel() {
        return this.tftextbookConstructionTblevel;
    }
    
    public void setTftextbookConstructionTblevel(TftextbookConstructionTblevel tftextbookConstructionTblevel) {
        this.tftextbookConstructionTblevel = tftextbookConstructionTblevel;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getBookId() {
        return this.bookId;
    }
    
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return this.bookName;
    }
    
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getSingellScore() {
        return this.singellScore;
    }
    
    public void setSingellScore(Double singellScore) {
        this.singellScore = singellScore;
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

    public String getCooperator() {
        return this.cooperator;
    }
    
    public void setCooperator(String cooperator) {
        this.cooperator = cooperator;
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