package com.nuaa.ec.model;

import java.sql.Timestamp;


/**
 * DataExportRecord entity. @author MyEclipse Persistence Tools
 */

public class DataExportRecord  implements java.io.Serializable {


    // Fields    

     private String flieName;
     private Timestamp exportDate;
     private String exist;
     private String type;


    // Constructors

    /** default constructor */
    public DataExportRecord() {
    }

	/** minimal constructor */
    public DataExportRecord(String flieName) {
        this.flieName = flieName;
    }
    
    /** full constructor */
    public DataExportRecord(String flieName, Timestamp exportDate, String exist, String type) {
        this.flieName = flieName;
        this.exportDate = exportDate;
        this.exist = exist;
        this.type = type;
    }

   
    // Property accessors

    public String getFlieName() {
        return this.flieName;
    }
    
    public void setFlieName(String flieName) {
        this.flieName = flieName;
    }

    public Timestamp getExportDate() {
        return this.exportDate;
    }
    
    public void setExportDate(Timestamp exportDate) {
        this.exportDate = exportDate;
    }

    public String getExist() {
        return this.exist;
    }
    
    public void setExist(String exist) {
        this.exist = exist;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
   








}