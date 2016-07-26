package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfteachingAbilityImproveLevel entity. @author MyEclipse Persistence Tools
 */

public class TfteachingAbilityImproveLevel  implements java.io.Serializable {


    // Fields    

     private String improveLevelId;
     private TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular;
     private String improveLevel;
     private Double ratio;
     private String spareTire;
     private String unit;
     private Set tfteachingAbilityImprovePerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfteachingAbilityImproveLevel() {
    }

	/** minimal constructor */
    public TfteachingAbilityImproveLevel(String improveLevelId) {
        this.improveLevelId = improveLevelId;
    }
    
    /** full constructor */
    public TfteachingAbilityImproveLevel(String improveLevelId, TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular, String improveLevel, Double ratio, String spareTire, String unit, Set tfteachingAbilityImprovePerformances) {
        this.improveLevelId = improveLevelId;
        this.tftechingAbilityEffectSubModular = tftechingAbilityEffectSubModular;
        this.improveLevel = improveLevel;
        this.ratio = ratio;
        this.spareTire = spareTire;
        this.unit = unit;
        this.tfteachingAbilityImprovePerformances = tfteachingAbilityImprovePerformances;
    }

   
    // Property accessors

    public String getImproveLevelId() {
        return this.improveLevelId;
    }
    
    public void setImproveLevelId(String improveLevelId) {
        this.improveLevelId = improveLevelId;
    }

    public TftechingAbilityEffectSubModular getTftechingAbilityEffectSubModular() {
        return this.tftechingAbilityEffectSubModular;
    }
    
    public void setTftechingAbilityEffectSubModular(TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular) {
        this.tftechingAbilityEffectSubModular = tftechingAbilityEffectSubModular;
    }

    public String getImproveLevel() {
        return this.improveLevel;
    }
    
    public void setImproveLevel(String improveLevel) {
        this.improveLevel = improveLevel;
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

    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Set getTfteachingAbilityImprovePerformances() {
        return this.tfteachingAbilityImprovePerformances;
    }
    
    public void setTfteachingAbilityImprovePerformances(Set tfteachingAbilityImprovePerformances) {
        this.tfteachingAbilityImprovePerformances = tfteachingAbilityImprovePerformances;
    }
   








}