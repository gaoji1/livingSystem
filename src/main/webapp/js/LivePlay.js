/**
 * 
 */
/**
 * 获取当前的流名称，用来给播放器设置地址
 * @returns
 */
function getStreamName(){
	var host = window.location.href;
	var i = 0;
	while(host.charAt(i) != "#"){
		i++;
	}
	var out = host.substring(i+1);
	return out;
}
var out = getStreamName();
var videoObject = {
			container: '#video',//“#”代表容器的ID，“.”或“”代表容器的class
			variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
			autoplay:true,//自动播放
			live:true,
			video:'rtmp://localhost/myApp/'+out//视频地址
		};
var player=new ckplayer(videoObject);




