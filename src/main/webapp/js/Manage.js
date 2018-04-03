/**
 * 
 */
//获取用户信息
/**
 * 向服务器端请求获取用户的信息
 * 接受到的data为json
 * 将json转化成对象
 * 将对象的对应字段输出到对应栏位
 * 这个函数会在页面加载完成后就立即执行
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

//更改用户信息
/**
 * 更改用户信息，分别从输入栏中采集用户的信息
 * 将用户信息上传到服务器
 * 服务器根据上传的信息对用户的相关信息做出更改
 * 同时服务器也必须检查更改后数据的合法性
 * 如果出现不合法的数据，则通过返回的data告知浏览器
 * 修改成功则返回sunccess
 * 此函数会在用户按下修改按钮的时候创建
 * @returns
 */
function changeUserInfo(){
	var username = $("#uName").val();
	var streamname = $("#sName").val();
	var roomname = $("#rName").val();
	
}
$("#save").on("click",changeUserInfo);