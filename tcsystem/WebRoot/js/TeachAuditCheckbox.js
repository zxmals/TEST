function modualchange() {
			var obj = document.getElementById("modualSelect").valueOf();
			var index=obj.selectedIndex; 
      		var val = obj.options[index].value; 
			if(val!="null"){
				if(val==0){
					document.getElementById("case1").style.display="none";
					document.getElementById("case2").style.display="none";
					for(var i=1;i<=18;i++){
						document.getElementById("zxma"+i+"l").style.display="none";
					}
				}
				if(val==1){
					document.getElementById("case0").style.display="none";
					document.getElementById("case2").style.display="none";
					for(var i=1;i<=18;i++){
						document.getElementById("zxma"+i+"l").style.display="none";
					}
				}	
				if(val==2){
					document.getElementById("case0").style.display="none";
					document.getElementById("case1").style.display="none";
					for(var i=1;i<=18;i++){
						document.getElementById("zxma"+i+"l").style.display="none";
					}
				}	
				document.getElementById("case"+val).style.display="block";
			}
			else{
				document.getElementById("case0").style.display="none";
				document.getElementById("case1").style.display="none";
				document.getElementById("case2").style.display="none";
			}	
		}
  		
  		function choosepreAudit(name) {
  			var selectobj = document.getElementById("departSelect");
      		var selectvalue = selectobj.options[selectobj.selectedIndex].value; 
      		window.location.replace('TeahingAuditpreview?oints='+selectvalue+'&AuditpartID='+name);
		}
  		
  		function nullChange(name,allCheck) {
  	    	var jud = document.getElementById(allCheck);  	    	
  	    	var readly = document.getElementById(name);
  	    	if(jud.checked&&!(readly.checked))
  	    		jud.checked = false;
  		}
  		
  		function allcheckchange(divid,count,name) {
  	    	 var jud = document.getElementById(name);
  	    	 var i = 0;
  	    	 var tmp = document.getElementById(divid+i);
  	    	 while(i<count){  	    		
  	    		 if(jud.checked){
  	    			 tmp.checked = true;
  	    		 }
  	    		 else{
  	    			tmp.checked = false;
  	    		 }
  	    		 i++;
  	    		 tmp = document.getElementById(divid+i);
  	    	 }
  		}
  		/**
  		 * 
  		 * @param divid 所在层ID
  		 * @param count   层内数据obj个数
  		 * @param actionAudit	 数据所在层所在 from 的ID 
  		 */
  		function Submitdata(divid,count,actionAudit) {
  			var i = 0;
    	    var che = "";
 	    	var tmp = document.getElementById(divid+i);
 	    	while(i<count){
 	    		 if(tmp.checked)
 	    			che += tmp.value+","; 
 	    		 i++;
 	    		 tmp = document.getElementById(divid+i);
 	    	 }
 	    	var res = che.substring(0, che.length-1);
 	    	var selectobj = document.getElementById("departSelect");
      		var selectvalue = selectobj.options[selectobj.selectedIndex].value; 
      		var act = document.getElementById(actionAudit);
      		switch (divid)
      		{
      		case "zxma1l":
      			act.action = 'TeahingAuditpreview!auditClassteach?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma2l":
      			act.action = 'TeahingAuditpreview!auditDegreeePaperGuide?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma3l":
      			act.action = 'TeahingAuditpreview!auditTeachingCompetition?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma4l":
      			act.action = 'TeahingAuditpreview!auditTeachingAbilityImprove?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma5l":
      			act.action = 'TeahingAuditpreview!auditFamousTeacherTeam?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma6l":
      			act.action = 'TeahingAuditpreview!auditTeaching_research?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma7l":
      			act.action = 'TeahingAuditpreview!auditTeachingPaper?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma8l":
      			act.action = 'TeahingAuditpreview!auditTeaching_achievement?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma9l":
      			act.action = 'TeahingAuditpreview!auditTextbookConstruction?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma10l":
      			act.action = 'TeahingAuditpreview!auditFineCourse?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma11l":
      			act.action = 'TeahingAuditpreview!auditProfessionalProject?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma12l":
      			act.action = 'TeahingAuditpreview!auditEnterpriseWorkstation?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma13l":
      			act.action = 'TeahingAuditpreview!auditSummerCourse?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma14l":
      			act.action = 'TeahingAuditpreview!auditPracticeInnovation?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma15l":
      			act.action = 'TeahingAuditpreview!auditStudentCompetitionGuide?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma16l":
      			act.action = 'TeahingAuditpreview!auditJoinStudentActivity?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma17l":
      			act.action = 'TeahingAuditpreview!auditUndergraduateTutorGuidance?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		case "zxma18l":
      			act.action = 'TeahingAuditpreview!auditOff_campusPractice?AuditResult='+res+'&oints='+selectvalue+'&AuditpartID='+divid.substring(0, divid.length-1);
      		  break;
      		}
      		act.submit();
  	    }