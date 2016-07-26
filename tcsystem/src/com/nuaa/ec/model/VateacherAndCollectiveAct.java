package com.nuaa.ec.model;



/**
 * VateacherAndCollectiveAct entity. @author MyEclipse Persistence Tools
 */

public class VateacherAndCollectiveAct  implements java.io.Serializable {


    // Fields    

     private VateacherAndCollectiveActId id;
     private Double score;
     private String spareTire;
     private String aspareTire;


    // Constructors

    /** default constructor */
    public VateacherAndCollectiveAct() {
    }

	/** minimal constructor */
    public VateacherAndCollectiveAct(VateacherAndCollectiveActId id) {
        this.id = id;
    }
    
    /** full constructor */
    public VateacherAndCollectiveAct(VateacherAndCollectiveActId id, Double score, String spareTire, String aspareTire) {
        this.id = id;
        this.score = score;
        this.spareTire = spareTire;
        this.aspareTire = aspareTire;
    }

   
    // Property accessors

    public VateacherAndCollectiveActId getId() {
        return this.id;
    }
    
    public void setId(VateacherAndCollectiveActId id) {
        this.id = id;
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

    public String getAspareTire() {
        return this.aspareTire;
    }
    
    public void setAspareTire(String aspareTire) {
        this.aspareTire = aspareTire;
    }
   








}