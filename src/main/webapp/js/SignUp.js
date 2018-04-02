/**
 * 
 */


var func = function(){
	var username =$("#username").val();
	var password = $("#password").val();
	var url = "/livingSystem/SignUpAction_verify";
	$.post(url,
			{uName : username,
			passWord : password},
			function(data){
				if(data == "success"){
					$(location).attr('href', '/livingSystem/LogIn.html');
				}else if(data == "wrong password"){
					alert(data);
				}else if(data == "username does not exist"){
					alert(data);
				}
			})
}


$("#submit").on("click",func);