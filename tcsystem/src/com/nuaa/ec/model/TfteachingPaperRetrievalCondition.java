package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfteachingPaperRetrievalCondition entity. @author MyEclipse Persistence Tools
 */

public class TfteachingPaperRetrievalCondition  implements java.io.Serializable {


    // Fields    

     private String thesisRetrivalId;
     private TfteachingReformResearchSubModular tfteachingReformResearchSubModular;
     private String author;
     private String thesisRetrieval;
     private Double score;
     private String spareTire;
     private Set tfteachingPaperPerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfteachingPaperRetrievalCondition() {
    }

	/** minimal constructor */
    public TfteachingPaperRetrievalCondition(String thesisRetrivalId) {
        this.thesisRetrivalId = thesisRetrivalId;
    }
    
    /** full constructor */
    public TfteachingPaperRetrievalCondition(String thesisRetrivalId, TfteachingReformResearchSubModular tfteachingReformResearchSubModular, String author, String thesisRetrieval, Double score, String spareTire, Set tfteachingPaperPerformances) {
        this.thesisRetrivalId = thesisRetrivalId;
        this.tfteachingReformResearchSubModular = tfteachingReformResearchSubModular;
        this.author = author;
        this.thesisRetrieval = thesisRetrieval;
        this.score = score;
        this.spareTire = spareTire;
        this.tfteachingPaperPerformances = tfteachingPaperPerformances;
    }

   
    // Property accessors

    public String getThesisRetrivalId() {
        return this.thesisRetrivalId;
    }
    
    public void setThesisRetrivalId(String thesisRetrivalId) {
        this.thesisRetrivalId = thesisRetrivalId;
    }

    public TfteachingReformResearchSubModular getTfteachingReformResearchSubModular() {
        return this.tfteachingReformResearchSubModular;
    }
    
    public void setTfteachingReformResearchSubModular(TfteachingReformResearchSubModular tfteachingReformResearchSubModular) {
        this.tfteachingReformResearchSubModular = tfteachingReformResearchSubModular;
    }

    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThesisRetrieval() {
        return this.thesisRetrieval;
    }
    
    public void setThesisRetrieval(String thesisRetrieval) {
        this.thesisRetrieval = thesisRetrieval;
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

    public Set getTfteachingPaperPerformances() {
        return this.tfteachingPaperPerformances;
    }
    
    public void setTfteachingPaperPerformances(Set tfteachingPaperPerformances) {
        this.tfteachingPaperPerformances = tfteachingPaperPerformances;
    }
   








}