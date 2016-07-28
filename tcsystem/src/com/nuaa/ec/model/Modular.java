package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * Modular entity. @author MyEclipse Persistence Tools
 */

public class Modular  implements java.io.Serializable {


    // Fields    

     private String modularId;
     private String modularName;
     private String spareTire;
     private Set subModulars = new HashSet(0);


    // Constructors

    /** default constructor */
    public Modular() {
    }

	/** minimal constructor */
    public Modular(String modularId) {
        this.modularId = modularId;
    }
    
    /** full constructor */
    public Modular(String modularId, String modularName, String spareTire, Set subModulars) {
        this.modularId = modularId;
        this.modularName = modularName;
        this.spareTire = spareTire;
        this.subModulars = subModulars;
    }

   
    // Property accessors

    public String getModularId() {
        return this.modularId;
    }
    
    public void setModularId(String modularId) {
        this.modularId = modularId;
    }

    public String getModularName() {
        return this.modularName;
    }
    
    public void setModularName(String modularName) {
        this.modularName = modularName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getSubModulars() {
        return this.subModulars;
    }
    
    public void setSubModulars(Set subModulars) {
        this.subModulars = subModulars;
    }
   








}