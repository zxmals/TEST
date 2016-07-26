package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * WordsNumber entity. @author MyEclipse Persistence Tools
 */

public class WordsNumber  implements java.io.Serializable {


    // Fields    

     private String wordId;
     private String wordNumber;
     private String spareTire;
     private Set academicWorkScores = new HashSet(0);


    // Constructors

    /** default constructor */
    public WordsNumber() {
    }

	/** minimal constructor */
    public WordsNumber(String wordId) {
        this.wordId = wordId;
    }
    
    /** full constructor */
    public WordsNumber(String wordId, String wordNumber, String spareTire, Set academicWorkScores) {
        this.wordId = wordId;
        this.wordNumber = wordNumber;
        this.spareTire = spareTire;
        this.academicWorkScores = academicWorkScores;
    }

   
    // Property accessors

    public String getWordId() {
        return this.wordId;
    }
    
    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public String getWordNumber() {
        return this.wordNumber;
    }
    
    public void setWordNumber(String wordNumber) {
        this.wordNumber = wordNumber;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getAcademicWorkScores() {
        return this.academicWorkScores;
    }
    
    public void setAcademicWorkScores(Set academicWorkScores) {
        this.academicWorkScores = academicWorkScores;
    }
   








}