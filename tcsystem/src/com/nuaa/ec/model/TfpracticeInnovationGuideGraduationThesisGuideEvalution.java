package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfpracticeInnovationGuideGraduationThesisGuideEvalution entity. @author MyEclipse Persistence Tools
 */

public class TfpracticeInnovationGuideGraduationThesisGuideEvalution  implements java.io.Serializable {


    // Fields    

     private String thesisEvaluationLevelId;
     private TfteachGuidanceSubModular tfteachGuidanceSubModular;
     private String thesisEvaluationLevel;
     private Double ratio;
     private String spareTire;
     private Set tfpracticeInnovationGuidePerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfpracticeInnovationGuideGraduationThesisGuideEvalution() {
    }

	/** minimal constructor */
    public TfpracticeInnovationGuideGraduationThesisGuideEvalution(String thesisEvaluationLevelId) {
        this.thesisEvaluationLevelId = thesisEvaluationLevelId;
    }
    
    /** full constructor */
    public TfpracticeInnovationGuideGraduationThesisGuideEvalution(String thesisEvaluationLevelId, TfteachGuidanceSubModular tfteachGuidanceSubModular, String thesisEvaluationLevel, Double ratio, String spareTire, Set tfpracticeInnovationGuidePerformances) {
        this.thesisEvaluationLevelId = thesisEvaluationLevelId;
        this.tfteachGuidanceSubModular = tfteachGuidanceSubModular;
        this.thesisEvaluationLevel = thesisEvaluationLevel;
        this.ratio = ratio;
        this.spareTire = spareTire;
        this.tfpracticeInnovationGuidePerformances = tfpracticeInnovationGuidePerformances;
    }

   
    // Property accessors

    public String getThesisEvaluationLevelId() {
        return this.thesisEvaluationLevelId;
    }
    
    public void setThesisEvaluationLevelId(String thesisEvaluationLevelId) {
        this.thesisEvaluationLevelId = thesisEvaluationLevelId;
    }

    public TfteachGuidanceSubModular getTfteachGuidanceSubModular() {
        return this.tfteachGuidanceSubModular;
    }
    
    public void setTfteachGuidanceSubModular(TfteachGuidanceSubModular tfteachGuidanceSubModular) {
        this.tfteachGuidanceSubModular = tfteachGuidanceSubModular;
    }

    public String getThesisEvaluationLevel() {
        return this.thesisEvaluationLevel;
    }
    
    public void setThesisEvaluationLevel(String thesisEvaluationLevel) {
        this.thesisEvaluationLevel = thesisEvaluationLevel;
    }

    public Double getRatio() {
        return this.ratio;
    }
    
    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTfpracticeInnovationGuidePerformances() {
        return this.tfpracticeInnovationGuidePerformances;
    }
    
    public void setTfpracticeInnovationGuidePerformances(Set tfpracticeInnovationGuidePerformances) {
        this.tfpracticeInnovationGuidePerformances = tfpracticeInnovationGuidePerformances;
    }
   








}