package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfclassTeachTime entity. @author MyEclipse Persistence Tools
 */

public class TfclassTeachTime  implements java.io.Serializable {


    // Fields    

     private String sumtimeId;
     private TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular;
     private String sumtimeScope;
     private Double ratio;
     private String spareTire;
     private Set tfclassTeachPefromances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfclassTeachTime() {
    }

	/** minimal constructor */
    public TfclassTeachTime(String sumtimeId) {
        this.sumtimeId = sumtimeId;
    }
    
    /** full constructor */
    public TfclassTeachTime(String sumtimeId, TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular, String sumtimeScope, Double ratio, String spareTire, Set tfclassTeachPefromances) {
        this.sumtimeId = sumtimeId;
        this.tftechingAbilityEffectSubModular = tftechingAbilityEffectSubModular;
        this.sumtimeScope = sumtimeScope;
        this.ratio = ratio;
        this.spareTire = spareTire;
        this.tfclassTeachPefromances = tfclassTeachPefromances;
    }

   
    // Property accessors

    public String getSumtimeId() {
        return this.sumtimeId;
    }
    
    public void setSumtimeId(String sumtimeId) {
        this.sumtimeId = sumtimeId;
    }

    public TftechingAbilityEffectSubModular getTftechingAbilityEffectSubModular() {
        return this.tftechingAbilityEffectSubModular;
    }
    
    public void setTftechingAbilityEffectSubModular(TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular) {
        this.tftechingAbilityEffectSubModular = tftechingAbilityEffectSubModular;
    }

    public String getSumtimeScope() {
        return this.sumtimeScope;
    }
    
    public void setSumtimeScope(String sumtimeScope) {
        this.sumtimeScope = sumtimeScope;
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