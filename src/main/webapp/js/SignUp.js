/**
 * 
 */


var func = function(){
	var username =$("#username").val();
	var password = $("#password").val();
	var url = "/livingSystem/SignUpAction_verify";
	var confirm = prompt("enter your password again:","******");
	if(confirm != password){
		alert("wrong password!");
		return;
	}
	$.post(url,
			{uName : username,
			passWord : password},
			function(data){
//				alert(data);
				if(data == "success"){
					alert(data);
					$(location).attr('href', '/livingSystem/LogIn.html');
				}else if(data == "existing username"){
					alert(data);
				}
			})
}


$("#submit").on("click",func);