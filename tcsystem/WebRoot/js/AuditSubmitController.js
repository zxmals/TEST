/**
 * 
 */
$(".check1").click(function(){
	if($(this).is(':checked')){
		$(this).parent(".c1").next().find("input").attr("disabled",'disabled');
	}else{
		$(this).parent(".c1").next().find("input").removeAttr("disabled");
	}
	
});
$(".check2").click(function(){
	if($(this).is(':checked')){
		$(this).parent(".c2").prev().find("input").attr("disabled",'disabled');
		$("#allAudit").attr("disabled",'disabled');
	}else{
		$(this).parent(".c2").prev().find("input").removeAttr("disabled");
	}
});
$("#allNotAudit").click(function(){
	if($("#allNotAudit").is(':checked')){
		$("input[name=notAudit]").prop("checked",true);
		$("#allAudit").attr("disabled",'disabled');
		$("input[name=chooseWhichToAudit]").attr("disabled",'disabled');
	}else{
		$("input[name=notAudit]").prop("checked",false);
		$("#allAudit").removeAttr("disabled");
		$("input[name=chooseWhichToAudit]").removeAttr("disabled");
	}
});
$("#allAudit").click(function(){
	if($("#allAudit").is(':checked')){
		$("input[name=chooseWhichToAudit]").prop("checked",true);
		$("#allNotAudit").attr("disabled",'disabled');
		$("input[name=notAudit]").attr("disabled",'disabled');
	}else{
		$("input[name=chooseWhichToAudit]").prop("checked",false);
		$("#allNotAudit").removeAttr("disabled");
		$("input[name=notAudit]").removeAttr("disabled");
	}
});
function getCheckOutResult(){
	var arr = "";
      $('input:checkbox[name=chooseWhichToAudit]:checked').each(function(i){
    	  arr=arr+$(this).val()+",";
      });
      alert(arr.substring(0,arr.length-1));
	  return arr.substring(0,arr.length-1); 
}
function getCheckOutNotAudit(){
	var arr="";
	$('input:checkbox[name=notAudit]:checked').each(function(i){
		arr=arr+$(this).val()+",";
	});
	alert(arr.substring(0,arr.length-1));
	return arr.substring(0,arr.length-1); 
}
function submitAudit(postUrl,refreshUrl){
	var IDs=getCheckOutResult();
	var IDsNot=getCheckOutNotAudit();
	if(IDs.length==0 && IDsNot.length==0){
		swal("请选择要通过审核的项目！", "", "warning");
		return;
	}
	swal({   title: "确定要审核吗？",
		text: "请仔细检查后进行审核",
		type: "warning",   showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "进行审核",
		cancelButtonText: "取消审核",
		closeOnConfirm: false,
		closeOnCancel: true }, 
		function(isConfirm){   
			if (isConfirm) { 
				$.post(postUrl,{
					checkOutIDs:IDs,
					checkOutIDsNot:IDsNot
					},function(data,status){
					if(status=="success"){
						if(data=="succ"){
							swal("审核成功！", "", "success");
							setTimeout(function(){
								window.location.replace(refreshUrl);
							},2000);
						}else{
							swal("审核失败！", "", "error");
						}
					}
					else{
						return;
					}
				});
				
			} 
		});
}








