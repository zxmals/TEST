package com.nuaa.ec.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class VaCommonweal implements Serializable{
	// Fields
	
	private String spareTire;
	private String VaAdminID;
	private String VaAdmin;
	private Set teachers = new HashSet(0);
	
	// Constructors
	
	/** default constructor */
	public VaCommonweal(){
		
	}
	
	/** minimal constructor */
	
    public VaCommonweal(String spareTire){
    	this.spareTire = spareTire;
}
    
    /** full constructor */
    
    public VaCommonweal(String spareTire,String CommonwealAdminID,String CommonwealAdmin,Set teachers){
    	this.spareTire = spareTire;
    	this.VaAdminID = CommonwealAdminID;
    	this.VaAdmin = CommonwealAdmin;
    	this.teachers = teachers;
    }   
    
    // Property accessors
    
	
	public String getSpareTire() {
		return spareTire;
	}
	public void setSpareTire(String spareTire) {
		this.spareTire = spareTire;
	}
	
	public String getVaAdmin() {
		return VaAdmin;
	}
	public void setVaAdmin(String vaAdmin) {
		VaAdmin = vaAdmin;
	}
	
	public String getVaAdminID() {
		return VaAdminID;
	}
	public void setVaAdminID(String vaAdminID) {
		VaAdminID = vaAdminID;
	}
	
	
	public Set getTeachers() {
		return teachers;
	}
	public void setTeachers(Set teachers) {
		this.teachers = teachers;
	}
}
