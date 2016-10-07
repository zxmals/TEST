package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * AcademicWorkScore entity. @author MyEclipse Persistence Tools
 */

public class AcademicWorkScore  implements java.io.Serializable {


    // Fields    

     private String acaWorkScoreId;
     private SubModular subModular;
     private WordsNumber wordsNumber;
     private Long score;
     private String spareTire;
     private Set teacherAndacademicWorks = new HashSet(0);


    // Constructors

    /** default constructor */
    public AcademicWorkScore() {
    }

	/** minimal constructor */
    public AcademicWorkScore(String acaWorkScoreId) {
        this.acaWorkScoreId = acaWorkScoreId;
    }
    
    /** full constructor */
    public AcademicWorkScore(String acaWorkScoreId, SubModular subModular, WordsNumber wordsNumber, Long score, String spareTire, Set teacherAndacademicWorks) {
        this.acaWorkScoreId = acaWorkScoreId;
        this.subModular = subModular;
        this.wordsNumber = wordsNumber;
        this.score = score;
        this.spareTire = spareTire;
        this.teacherAndacademicWorks = teacherAndacademicWorks;
    }

   
    // Property accessors

    public String getAcaWorkScoreId() {
        return this.acaWorkScoreId;
    }
    
    public void setAcaWorkScoreId(String acaWorkScoreId) {
        this.acaWorkScoreId = acaWorkScoreId;
    }

    public SubModular getSubModular() {
        return this.subModular;
    }
    
    public void setSubModular(SubModular subModular) {
        this.subModular = subModular;
    }

    public WordsNumber getWordsNumber() {
        return this.wordsNumber;
    }
    
    public void setWordsNumber(WordsNumber wordsNumber) {
        this.wordsNumber = wordsNumber;
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

    public Set getTeacherAndacademicWorks() {
        return this.teacherAndacademicWorks;
    }
    
    public void setTeacherAndacademicWorks(Set teacherAndacademicWorks) {
        this.teacherAndacademicWorks = teacherAndacademicWorks;
    }
   








}