package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfteachGuidanceSubModular entity. @author MyEclipse Persistence Tools
 */

public class TfteachGuidanceSubModular  implements java.io.Serializable {


    // Fields    

     private String teachGuideId;
     private SubModular subModular;
     private String teachGuideName;
     private String spareTire;
     private Set tfundergraduateTutorGuidanceCaches = new HashSet(0);
     private Set tfjoinStudentActivityTimes = new HashSet(0);
     private Set tfpracticeInnovationGuideGraduationThesisGuideEvalutions = new HashSet(0);
     private Set tfpracticeInnovationGuideLevels = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfteachGuidanceSubModular() {
    }

	/** minimal constructor */
    public TfteachGuidanceSubModular(String teachGuideId, SubModular subModular) {
        this.teachGuideId = teachGuideId;
        this.subModular = subModular;
    }
    
    /** full constructor */
    public TfteachGuidanceSubModular(String teachGuideId, SubModular subModular, String teachGuideName, String spareTire, Set tfundergraduateTutorGuidanceCaches, Set tfjoinStudentActivityTimes, Set tfpracticeInnovationGuideGraduationThesisGuideEvalutions, Set tfpracticeInnovationGuideLevels) {
        this.teachGuideId = teachGuideId;
        this.subModular = subModular;
        this.teachGuideName = teachGuideName;
        this.spareTire = spareTire;
        this.tfundergraduateTutorGuidanceCaches = tfundergraduateTutorGuidanceCaches;
        this.tfjoinStudentActivityTimes = tfjoinStudentActivityTimes;
        this.tfpracticeInnovationGuideGraduationThesisGuideEvalutions = tfpracticeInnovationGuideGraduationThesisGuideEvalutions;
        this.tfpracticeInnovationGuideLevels = tfpracticeInnovationGuideLevels;
    }

   
    // Property accessors

    public String getTeachGuideId() {
        return this.teachGuideId;
    }
    
    public void setTeachGuideId(String teachGuideId) {
        this.teachGuideId = teachGuideId;
    }

    public SubModular getSubModular() {
        return this.subModular;
    }
    
    public void setSubModular(SubModular subModular) {
        this.subModular = subModular;
    }

    public String getTeachGuideName() {
        return this.teachGuideName;
    }
    
    public void setTeachGuideName(String teachGuideName) {
        this.teachGuideName = teachGuideName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTfundergraduateTutorGuidanceCaches() {
        return this.tfundergraduateTutorGuidanceCaches;
    }
    
    public void setTfundergraduateTutorGuidanceCaches(Set tfundergraduateTutorGuidanceCaches) {
        this.tfundergraduateTutorGuidanceCaches = tfundergraduateTutorGuidanceCaches;
    }

    public Set getTfjoinStudentActivityTimes() {
        return this.tfjoinStudentActivityTimes;
    }
    
    public void setTfjoinStudentActivityTimes(Set tfjoinStudentActivityTimes) {
        this.tfjoinStudentActivityTimes = tfjoinStudentActivityTimes;
    }

    public Set getTfpracticeInnovationGuideGraduationThesisGuideEvalutions() {
        return this.tfpracticeInnovationGuideGraduationThesisGuideEvalutions;
    }
    
    public void setTfpracticeInnovationGuideGraduationThesisGuideEvalutions(Set tfpracticeInnovationGuideGraduationThesisGuideEvalutions) {
        this.tfpracticeInnovationGuideGraduationThesisGuideEvalutions = tfpracticeInnovationGuideGraduationThesisGuideEvalutions;
    }

    public Set getTfpracticeInnovationGuideLevels() {
        return this.tfpracticeInnovationGuideLevels;
    }
    
    public void setTfpracticeInnovationGuideLevels(Set tfpracticeInnovationGuideLevels) {
        this.tfpracticeInnovationGuideLevels = tfpracticeInnovationGuideLevels;
    }
   








}