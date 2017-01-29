/**
 * 动态生成数据汇总明细表格
 */
function create(data){
	clear();
	var table = document.getElementById("dataTable");
//	var caption=table.createCaption();
//	caption.innerText=data.moduleName;
	document.getElementById("tableCaption").innerHTML=data.moduleName;
	var titleRow = table.insertRow(0);
	for(var i=0;i<data.field.length;i++){
		var cell = titleRow.insertCell(i);
//		cell.height=30;
		cell.innerText=data.field[i];
	}
	for(var i=0;i<data.dataArray.length;i++){
		var row = table.insertRow(table.rows.length);
		for(var j=0;j<data.dataArray[0].length;j++){
			var cell = row.insertCell(j);
//			cell.height=25;
			cell.innerText = data.dataArray[i][j];
		}
	}
}
function clear(){
	var table = document.getElementById("dataTable");
	document.getElementById("tableCaption").innerHTML="无对应记录...";
	var rowCount = table.rows.length;
	for(var i=rowCount-1;i>=0;i--){
		table.deleteRow(i);
	}
}