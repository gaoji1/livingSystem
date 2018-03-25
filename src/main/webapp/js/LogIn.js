/**
 * 给LogIn页面写的提交js
 */
/**
 * 点击提交按钮后
 * 1）定位到name与password的表单位置
 * 2）取得两个表单里输入的值
 * 3）设置url
 * 4）通过ajax向服务器发送请求
 */
//var logIn = funtion(){
//	var url = "/livingSystem/LogIn_verify";
//	var inputName = $("#username");
//	var inputPsword = $("#userpwd");
//	$.post(url,{
//		uName:inputName.value,
//		passWord : inputPsword.value
//	},function(data){
//		if(data == "SUCCESS"){
//			window.navigate("/livingSystem/index.html");
//		}
//	});
//}

var logIn = function(){
	var url = "/livingSystem/LogIn_verify";
	var inputName = $("#username").val();
	var inputPsword = $("#userpwd").val();
	$.post(url,
			{uName : inputName,
			passWord : inputPsword},
			function(data){
				if(data == "SUCCESS"){
					alert(1);
				}
			})
}
$("#button").on("click",logIn);
//以下为测试js能否导入
//$(function(){
//	alert("11");
//})
