/**
 * 
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