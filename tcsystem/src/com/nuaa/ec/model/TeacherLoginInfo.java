package com.nuaa.ec.model;

import java.sql.Timestamp;


/**
 * TeacherLoginInfo entity. @author MyEclipse Persistence Tools
 */

public class TeacherLoginInfo  implements java.io.Serializable {


    // Fields    

     private Integer teacherLoginid;
     private Teacher teacher;
     private String password;
     private String level;
     private String spareTire;
     private Integer loginTime;
     private Timestamp lastLoginDate;


    // Constructors

    /** default constructor */
    public TeacherLoginInfo() {
    }

	/** minimal constructor */
    public TeacherLoginInfo(Integer teacherLoginid) {
        this.teacherLoginid = teacherLoginid;
    }
    
    /** full constructor */
    public TeacherLoginInfo(Integer teacherLoginid, Teacher teacher, String password, String level, String spareTire, Integer loginTime, Timestamp lastLoginDate) {
        this.teacherLoginid = teacherLoginid;
        this.teacher = teacher;
        this.password = password;
        this.level = level;
        this.spareTire = spareTire;
        this.loginTime = loginTime;
        this.lastLoginDate = lastLoginDate;
    }

   
    // Property accessors

    public Integer getTeacherLoginid() {
        return this.teacherLoginid;
    }
    
    public void setTeacherLoginid(Integer teacherLoginid) {
        this.teacherLoginid = teacherLoginid;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return this.level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Integer getLoginTime() {
        return this.loginTime;
    }
    
    public void setLoginTime(Integer loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLastLoginDate() {
        return this.lastLoginDate;
    }
    
    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
   








}