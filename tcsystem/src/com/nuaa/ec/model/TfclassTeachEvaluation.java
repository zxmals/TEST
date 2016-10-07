package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfclassTeachEvaluation entity. @author MyEclipse Persistence Tools
 */

public class TfclassTeachEvaluation  implements java.io.Serializable {


    // Fields    

     private String evaluationId;
     private TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular;
     private String evaluResult;
     private Double ratio;
     private String spareTire;
     private Set tfclassTeachPefromances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfclassTeachEvaluation() {
    }

	/** minimal constructor */
    public TfclassTeachEvaluation(String evaluationId) {
        this.evaluationId = evaluationId;
    }
    
    /** full constructor */
    public TfclassTeachEvaluation(String evaluationId, TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular, String evaluResult, Double ratio, String spareTire, Set tfclassTeachPefromances) {
        this.evaluationId = evaluationId;
        this.tftechingAbilityEffectSubModular = tftechingAbilityEffectSubModular;
        this.evaluResult = evaluResult;
        this.ratio = ratio;
        this.spareTire = spareTire;
        this.tfclassTeachPefromances = tfclassTeachPefromances;
    }

   
    // Property accessors

    public String getEvaluationId() {
        return this.evaluationId;
    }
    
    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
    }

    public TftechingAbilityEffectSubModular getTftechingAbilityEffectSubModular() {
        return this.tftechingAbilityEffectSubModular;
    }
    
    public void setTftechingAbilityEffectSubModular(TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular) {
        this.tftechingAbilityEffectSubModular = tftechingAbilityEffectSubModular;
    }

    public String getEvaluResult() {
        return this.evaluResult;
    }
    
    public void setEvaluResult(String evaluResult) {
        this.evaluResult = evaluResult;
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

    public Set getTfclassTeachPefromances() {
        return this.tfclassTeachPefromances;
    }
    
    public void setTfclassTeachPefromances(Set tfclassTeachPefromances) {
        this.tfclassTeachPefromances = tfclassTeachPefromances;
    }
   








}