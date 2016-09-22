/***
 * style --control // 样式控制
 * class~explain
 * class-list: .nullcheck  .openaddm .openupdatem .subcheck .carrydata
 * .nullcheck : what you need to submit //需要被提交的 input之类的
 * .openaddm: which can open add-modal-dialog //触发添加模态框的 按钮或其他标签的class
 * .openupdatem:which can open update - modal - dialog //触发更新模态框的 按钮或其他标签的class
 * .subcheck : which button can submit //提交的标签或按钮的 class
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
	// for those select need to set which option attr to be selected
	function set_selected_option(options,values) {
		for(var i=0;i<options.length;i++){
			if(options[i].value == values){
				options[i].selected = true;
			}else{
				options[i].selected = false;
			}
		}
	}
	//for add 
	function checkadds() {
		var readys = $('.addcheck');
		var cout = 0;
		for(var i=0;i<readys.length;i++){
			if(readys[i].value.trim()==""){
				cout++;
				break;
			}
		}
		if(cout>0){
			return false;
		}else{
			return true;
		}
	}
	//for update 
	function checkupdates() {
		var readys = $('.upcheck');
		var cout = 0;
		for(var i=0;i<readys.length;i++){
			if(readys[i].value.trim()==""){
				cout++;
				break;
			}
		}
		if(cout>0){
			return false;
		}else{
			return true;
		}
	}