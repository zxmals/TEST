function changeCheckboxValueAndSubmit(name,oints) {
      var obj = document.getElementsByTagName('input');
      var jud = document.getElementById("allCheck");
      var che = "";
      for(var i=0;i<obj.length;i++){
     	 if(obj[i].type=='checkbox')
              if (obj[i].checked&&obj[i]!=jud){
          	     che += obj[i].value+",";      
              }
     }
      var res = che.substring(0, che.length-1);
      document.Audit.action = 'ScientificAudit!audit'+name+'?oints='+oints+'&AuditResult='+res;
      document.Audit.submit();
    }
    
    function allAlowOrNot() {
    	 var obj = document.getElementsByTagName('input');
    	 var jud = document.getElementById("allCheck");
    	 if(jud.checked)
    		   for(x in obj){
    		          if(obj[x].type=='checkbox')
    		            	if (!obj[x].checked&&obj[x]!=jud)
           	     				 	obj[x].checked = true;
       	    	}
    	 else
    		 for(x in obj){
		          if(obj[x].type=='checkbox')
		            	if (obj[x].checked&&obj[x]!=jud)
      	     				 	obj[x].checked = false;
  	    	}
	}
    
    function nullChange(name) {
    	var jud = document.getElementById("allCheck");
    	var readly = document.getElementById(name);
    	if(jud.checked&&!(readly.checked))
    		jud.checked = false;
	}
    
	function confirms(num) {
		var tab = document.getElementById("tab");
		var tb = document.getElementById("tb");
		var obj = document.getElementsByTagName('input');
        var che = "";
        var dates = "";
        var j = 0;
        var c = 1;
        for(var i=0;i<num;i++){
        	tab.rows[0].cells[i].innerHTML = "";
        }
        
        for(var x=0; x<obj.length;x++){
            if(obj[x].type=='checkbox')
              if (obj[x].checked){
          	     che += obj[x].value+",";
          	   	  tab.rows[0].cells[j].innerHTML = tb.rows[1].cells[obj[x].value - 1].innerHTML;
          	   	  j++;
              }
              if(obj[x].type=='text'&&c<=3){
            	  dates += obj[x].value+",";
            	  tab.rows[2].cells[c].innerHTML = obj[x].value;
            	  c += 2;
              }
        }
        var res = che.substring(0, che.length-1);
        var res1 = dates.substring(0, dates.length-1);
        document.getElementById("values").value = res;
        document.getElementById("Datevalues").value = res1;
	}