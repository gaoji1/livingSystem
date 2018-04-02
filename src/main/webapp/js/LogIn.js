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
				if(data == "success"){
					$(location).attr('href', '/livingSystem/index.html');
				}else if(data == "wrong password"){
					alert(data);
				}else if(data == "username does not exist"){
					alert(data);
				}
			})
}
$("#button").on("click",logIn);
$(function(){
	var url = "/livingSystem/LogIn_judge";
	$.get(url,function(data){
		if(data == "no existing account"){
			return;
		}else if(data == "user has logged in"){
			alert("please do not log in again!");
			$(location).attr('href', '/livingSystem/index.html');
		}else{
			alert(data);
		}
	});
})
//以下为测试js能否导入
//$(function(){
//	alert("11");
//})
