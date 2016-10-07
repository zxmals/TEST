package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * Department entity. @author MyEclipse Persistence Tools
 */

public class Department  implements java.io.Serializable {


    // Fields    

     private String departmentId;
     private String departmentName;
     private String spareTire;
     private String departAdminId;
     private String departAdmin;
     private Set teachers = new HashSet(0);


    // Constructors

    /** default constructor */
    public Department() {
    }

	/** minimal constructor */
    public Department(String departmentId) {
        this.departmentId = departmentId;
    }
    
    /** full constructor */
    public Department(String departmentId, String departmentName, String spareTire, String departAdminId, String departAdmin, Set teachers) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.spareTire = spareTire;
        this.departAdminId = departAdminId;
        this.departAdmin = departAdmin;
        this.teachers = teachers;
    }

   
    // Property accessors

    public String getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public String getDepartAdminId() {
        return this.departAdminId;
    }
    
    public void setDepartAdminId(String departAdminId) {
        this.departAdminId = departAdminId;
    }

    public String getDepartAdmin() {
        return this.departAdmin;
    }
    
    public void setDepartAdmin(String departAdmin) {
        this.departAdmin = departAdmin;
    }

    public Set getTeachers() {
        return this.teachers;
    }
    
    public void setTeachers(Set teachers) {
        this.teachers = teachers;
    }
   








}