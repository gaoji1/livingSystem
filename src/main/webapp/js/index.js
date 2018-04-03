/**
 * 
 */

$(function() {
	var url = "/livingSystem/LogIn_judge";
	$.get(url, function(data) {
		if (data == "no existing account") {
			$("#userinfo").text("No User");
			$("#lable1").text("Log In");
			$("#lable2").text("Sign Up");
		} else {
			$("#userinfo").text("Welcome!"+data+""+"");
			$("#lable1").text("Manage");
			$("#lable2").text("Sign out");
		}
	});
});