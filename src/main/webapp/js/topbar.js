/**
 * 
 */

$(function() {
	var url = "/livingSystem/LogIn_judge";
	$.get(url, function(data) {
		if (data == "no existing account") {
			$("#userinfo").text("No User");
			$("#lable1").text("Log In");
			$("#lable1").attr("href","LogIn.html");
			$("#lable2").text("Sign Up");
			$("#lable2").attr("href","signUp.html");
		} else {
			$("#userinfo").text("Welcome!"+data);
			$("#lable1").text("Manage");
			$("#lable1").attr("href","Manage.html");
			$("#lable2").text("Sign out");
			$("#lable2").attr("href","SignOut.html");
		}
	});
});