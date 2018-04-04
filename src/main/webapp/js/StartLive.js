/**
 * 
 */
//开始发布直播流函数
/**
 * 用户按照网页提示步骤进行操作后，按下start按钮激发此功能
 * 会向服务器查询用户直播间是否在线
 * 在线则返回index
 * 不在线则继续引导用户
 * @returns
 */
function StartLive(){
	var url = "/livingSystem/LiveAction_beginLive";
	$.get(url,function(data){
		if(data!="false"){
			alert("live start");
			$(location).attr('href', '/livingSystem/index.html');
		}else{
			alert("your live broadcast  can not start correctly,please follow the recommend and try again");
		}
	})
}
$("#start").on("click",StartLive);

//用户登陆状态检测
/**
 * 若当前没有用户登陆，则禁止用户访问此界面，因为没有登陆无法发布直播流
 * @returns
 */
$(function() {
	var url = "/livingSystem/LogIn_judge";
	$.get(url, function(data) {
		if (data == "no existing account") {
			alert("you must log in before start live!");
			$(location).attr('href', '/livingSystem/LogIn.html');
		} else {
		}
	});
});