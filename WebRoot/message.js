/**
 * 
 */


function tishi(){
	
	var row = document.sizeForm.row.value;
	var column = document.sizeForm.column.value;
	
	if( row=="" && column==""){
		alert("行、列不可以为空");
		return false;
	}
	else if( column == ""){
		alert("列不可以为空");
		return false;
	}
	else if( row == ""){
		alert("行不可以为空");
		return false;
	}
	
	if(isNaN(row)){
		alert("行输入包含非数字字符！");
		return false;
	}
	
	if(isNaN(column)){
		alert("列输入包含非数字字符！");
		return false;
	}
	
	var message = "行数："+row+"\r\n";
	var message2 = "列数："+column+"\r\n";

	var retVal = window.confirm("您确定要提交：\r\n"+message+message2+"这些参数吗？");
	
	return retVal;
}