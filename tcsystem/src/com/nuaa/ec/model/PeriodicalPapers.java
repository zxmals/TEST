package com.nuaa.ec.model;



/**
 * PeriodicalPapers entity. @author MyEclipse Persistence Tools
 */

public class PeriodicalPapers  implements java.io.Serializable {


    // Fields    

     private Integer periodicalPid;
     private Periodical periodical;
     private String ppid;
     private String firstAuthor;
     private String secondAuthor;
     private String thesisTitle;
     private String year;
     private String file;
     private String phase;
     private String describe;
     private String spareTire;
     private String chargePersonId;
     private String chargePerson;
     private String checkout;


    // Constructors

    /** default constructor */
    public PeriodicalPapers() {
    }

	/** minimal constructor */
    public PeriodicalPapers(Integer periodicalPid) {
        this.periodicalPid = periodicalPid;
    }
    
    /** full constructor */
    public PeriodicalPapers(Integer periodicalPid, Periodical periodical, String ppid, String firstAuthor, String secondAuthor, String thesisTitle, String year, String file, String phase, String describe, String spareTire, String chargePersonId, String chargePerson, String checkout) {
        this.periodicalPid = periodicalPid;
        this.periodical = periodical;
        this.ppid = ppid;
        this.firstAuthor = firstAuthor;
        this.secondAuthor = secondAuthor;
        this.thesisTitle = thesisTitle;
        this.year = year;
        this.file = file;
        this.phase = phase;
        this.describe = describe;
        this.spareTire = spareTire;
        this.chargePersonId = chargePersonId;
        this.chargePerson = chargePerson;
        this.checkout = checkout;
    }

   
    // Property accessors

    public Integer getPeriodicalPid() {
        return this.periodicalPid;
    }
    
    public void setPeriodicalPid(Integer periodicalPid) {
        this.periodicalPid = periodicalPid;
    }

    public Periodical getPeriodical() {
        return this.periodical;
    }
    
    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }

    public String getPpid() {
        return this.ppid;
    }
    
    public void setPpid(String ppid) {
        this.ppid = ppid;
    }

    public String getFirstAuthor() {
        return this.firstAuthor;
    }
    
    public void setFirstAuthor(String firstAuthor) {
        this.firstAuthor = firstAuthor;
    }

    public String getSecondAuthor() {
        return this.secondAuthor;
    }
    
    public void setSecondAuthor(String secondAuthor) {
        this.secondAuthor = secondAuthor;
    }

    public String getThesisTitle() {
        return this.thesisTitle;
    }
    
    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    public String getYear() {
        return this.year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }

    public String getFile() {
        return this.file;
    }
    
    public void setFile(String file) {
        this.file = file;
    }

    public String getPhase() {
        return this.phase;
    }
    
    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getDescribe() {
        return this.describe;
    }
    
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
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
   








}