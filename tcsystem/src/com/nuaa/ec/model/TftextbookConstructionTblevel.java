package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TftextbookConstructionTblevel entity. @author MyEclipse Persistence Tools
 */

public class TftextbookConstructionTblevel  implements java.io.Serializable {


    // Fields    

     private String tblevelId;
     private String tblevel;
     private Double score;
     private String reformResearchId;
     private String spareTire;
     private Set tftextbookConstructionPerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TftextbookConstructionTblevel() {
    }

	/** minimal constructor */
    public TftextbookConstructionTblevel(String tblevelId) {
        this.tblevelId = tblevelId;
    }
    
    /** full constructor */
    public TftextbookConstructionTblevel(String tblevelId, String tblevel, Double score, String reformResearchId, String spareTire, Set tftextbookConstructionPerformances) {
        this.tblevelId = tblevelId;
        this.tblevel = tblevel;
        this.score = score;
        this.reformResearchId = reformResearchId;
        this.spareTire = spareTire;
        this.tftextbookConstructionPerformances = tftextbookConstructionPerformances;
    }

   
    // Property accessors

    public String getTblevelId() {
        return this.tblevelId;
    }
    
    public void setTblevelId(String tblevelId) {
        this.tblevelId = tblevelId;
    }

    public String getTblevel() {
        return this.tblevel;
    }
    
    public void setTblevel(String tblevel) {
        this.tblevel = tblevel;
    }

    public Double getScore() {
        return this.score;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }

    public String getReformResearchId() {
        return this.reformResearchId;
    }
    
    public void setReformResearchId(String reformResearchId) {
        this.reformResearchId = reformResearchId;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTftextbookConstructionPerformances() {
        return this.tftextbookConstructionPerformances;
    }
    
    public void setTftextbookConstructionPerformances(Set tftextbookConstructionPerformances) {
        this.tftextbookConstructionPerformances = tftextbookConstructionPerformances;
    }
   








}