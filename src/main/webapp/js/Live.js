/**
 * 
 */
//获取直播间信息
/**
 * 获取直播间信息，该函数在页面加载完成后调用，从服务器端获取当前在播的直播间
 * 不需要上传任何变量，所以使用get请求
 * 取回直播间信息为json格式，把他做转化后输出到列表中
 * @returns
 */
function getLiveInfo(){
	url = "/livingSystem/LiveAction_getLiveInfo";
	$.get(url,function(data){
		if(data != null){
			var rooms = JSON.parse(data);
			for(var e in rooms){
				temp_room = $('<li class="list-group-item"><a class="li-span">'+rooms[e].uName+'</a>'+'<a>'+rooms[e].roomName+'</a>'+'</li>'); 
				temp_li = $('<li class="list-group-item"></li>');
				temp_span = $('<span class="li-span"></span>');
				temp_a = $('<a></a>');
				$("#livelist").append(temp_room);
			}
			
			
		}
	})
}



$(getLiveInfo);