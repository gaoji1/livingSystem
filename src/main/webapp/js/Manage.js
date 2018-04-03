/**
 * 
 */

var getUserInfo = function(){
	url = "/livingSystem/UserManage_getUserInfo";
	$.get(url,function(data) {
		var username = $("#userinfo").text();
		username = username.substring(8);
		var info = JSON.parse(data);
		$("#uName").val(username);
		$("#sName").val(info.streamName);
		$("#rName").val(info.roomName);
	})
}
$(getUserInfo);