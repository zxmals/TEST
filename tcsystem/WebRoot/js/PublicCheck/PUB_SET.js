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
		var checkeds = $('.checkattr');
		if(checkeds.length>0){
			for(var i=0;i<checkeds.length;i++){
				checkeds[i].checked = false;
			}
			$('#isbn10').css("display","none");
			$('#isbn13').css("display","none");
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
		nullcheck();
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
	//解析链接 返回二维数组
	function getParamArray() {
        var url = location.search;
        url = url.substring(1,url.length);
        var array = url.split("&");
        var aarray = new Array();
        for(var i=0;i<array.length;i++){
            aarray[i] = array[i].split("=");
        }
        return aarray;
//        for(var i=0;i<array.length;i++){
//            for(var j=0;j<2;j++){
//                if(aarray[i][j]=="userid")
//                    userid = aarray[i][++j];
//                if(aarray[i][j]=="username")
//                    username = aarray[i][++j];
//                if(aarray[i][j]=="num")
//                    num = aarray[i][++j];
//            }
//        }
    }
	//通过参数名name获取链接中对应的参数值
	function getParameters(name) {
		var arrays = getParamArray();
		var value = "5";
		for(var i=0;i<arrays.length;i++){
			for(var j=0;j<2;j++){
				if(arrays[i][j]==name){
					value = arrays[i][++j];
				}
			}
		}
		return value;
	}
	//ISBN 合法性验证
    function checkISBN(isbnv) {
    	var isbn = isbnv.replace(new RegExp("-","gm"),"");
    	//ISBN-10
    	if(!(isbn.length==10|isbn.length==13)){
    		return false;
    	}
		if(isbn.length==10){
			var tmp = 10;
			var sum = 0;
			for(var i=0;i<9;i++){
				sum += isbn.charAt(i)*(tmp--);
			}
			var N = 11-sum%11;
			if(N==10){
				if(!(isbn.charAt(i)=='X'|isbn.charAt(i)=='x')){
					return false;
				}else return true;
			}else{
				if(N==11){
					if(isbn.charAt(i)!='0'){
						return false;
					}else return true;
				}else{
					if(isbn.charAt(i)!=N){
						return false;
					}else return true;
				}
			}
		}
    	//ISBN-13
		if(isbn.length==13){
			var sum = 0;
			for(var i=0;i<12;i++){
				if((i+1)%2==0){
					sum += parseInt(isbn.charAt(i))*3;
				}else{
					sum += parseInt(isbn.charAt(i));
				}
			}
			var N = 10 - sum%10;
			if(N==0){
				if(isbn.charAt(i)!='0'){
					return false;
				}else return true;
			}else{
				if(isbn.charAt(i)!=N){
					return false;
				}else return true;
			}
		}
	}
    function checkNUll(classname){
    	var readys = $(classname);
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