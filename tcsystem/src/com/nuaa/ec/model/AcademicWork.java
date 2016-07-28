package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * AcademicWork entity. @author MyEclipse Persistence Tools
 */

public class AcademicWork  implements java.io.Serializable {


    // Fields    

     private String acaworkId;
     private PublishClub publishClub;
     private String firstAuthor;
     private String workName;
     private String publishDate;
     private String isbn;
     private String wordNumber;
     private String otherAuthorJoin;
     private String spareTire;
     private String wordId;
     private String chargePersonId;
     private String chargePerson;
     private String checkout;
     private Set teacherAndacademicWorks = new HashSet(0);


    // Constructors

    /** default constructor */
    public AcademicWork() {
    }

	/** minimal constructor */
    public AcademicWork(String acaworkId) {
        this.acaworkId = acaworkId;
    }
    
    /** full constructor */
    public AcademicWork(String acaworkId, PublishClub publishClub, String firstAuthor, String workName, String publishDate, String isbn, String wordNumber, String otherAuthorJoin, String spareTire, String wordId, String chargePersonId, String chargePerson, String checkout, Set teacherAndacademicWorks) {
        this.acaworkId = acaworkId;
        this.publishClub = publishClub;
        this.firstAuthor = firstAuthor;
        this.workName = workName;
        this.publishDate = publishDate;
        this.isbn = isbn;
        this.wordNumber = wordNumber;
        this.otherAuthorJoin = otherAuthorJoin;
        this.spareTire = spareTire;
        this.wordId = wordId;
        this.chargePersonId = chargePersonId;
        this.chargePerson = chargePerson;
        this.checkout = checkout;
        this.teacherAndacademicWorks = teacherAndacademicWorks;
    }

   
    // Property accessors

    public String getAcaworkId() {
        return this.acaworkId;
    }
    
    public void setAcaworkId(String acaworkId) {
        this.acaworkId = acaworkId;
    }

    public PublishClub getPublishClub() {
        return this.publishClub;
    }
    
    public void setPublishClub(PublishClub publishClub) {
        this.publishClub = publishClub;
    }

    public String getFirstAuthor() {
        return this.firstAuthor;
    }
    
    public void setFirstAuthor(String firstAuthor) {
        this.firstAuthor = firstAuthor;
    }

    public String getWorkName() {
        return this.workName;
    }
    
    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getPublishDate() {
        return this.publishDate;
    }
    
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getIsbn() {
        return this.isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getWordNumber() {
        return this.wordNumber;
    }
    
    public void setWordNumber(String wordNumber) {
        this.wordNumber = wordNumber;
    }

    public String getOtherAuthorJoin() {
        return this.otherAuthorJoin;
    }
    
    public void setOtherAuthorJoin(String otherAuthorJoin) {
        this.otherAuthorJoin = otherAuthorJoin;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public String getWordId() {
        return this.wordId;
    }
    
    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public String getChargePersonId() {
        return this.chargePersonId;
    }
    
    public void setChargePersonId(String chargePersonId) {
        this.chargePersonId = chargePersonId;
    }

    public String getChargePerson() {
        return this.chargePerson;
    }
    
    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getCheckout() {
        return this.checkout;
    }
    
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public Set getTeacherAndacademicWorks() {
        return this.teacherAndacademicWorks;
    }
    
    public void setTeacherAndacademicWorks(Set teacherAndacademicWorks) {
        this.teacherAndacademicWorks = teacherAndacademicWorks;
    }
   








}