/***
 * style --control // 样式控制
 * class~explain
 * class-list: .nullcheck  .openaddm .openupdatem
 * .nullcheck : what you need to submit
 * .openaddm: which can open add-modal-dialog
 * .openupdatem:which can open update - modal - dialog 
 */
//check dialog isNull and execute    
function nullcheck() {
		var readys = $('.nullcheck');
		for(var i=0;i<readys.length;i++){
			if(readys[i].value.trim()==""){
				readys[i].style.backgroundColor = "green";
				readys[i].placeholder = "请填充空白";
				readys[i].value = "";
			}
		}
	}
    //in-it input dialog when user open it 
	function initoper() {
		var readys = $('.nullcheck');
		for(var i=0;i<readys.length;i++){
				readys[i].style.backgroundColor = "white";
				readys[i].placeholder = "";
				readys[i].value = "";
		}
	}
	//change -background-color to white/ when user click on this
	$('.nullcheck').click(function() {
		$(this).css("background-color","white");
	});
	//change -background-color to white/ when user type in
	$('.nullcheck').keyup(function() {
		$(this).css("background-color","white");
	});
	// open add modal - dialog
	$('.openaddm').click(function() {
		initoper();
	});
	// open update modal - dialog
	$('.openupdatem').click(function() {
		initoper();
	});
	// before submit check null exception(contain add and update) 
	$('.subcheck').click(function() {
		nullcheck();
	});