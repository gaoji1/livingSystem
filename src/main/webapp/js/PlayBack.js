/**
 * 
 */


function getAllPlayBack(){
	url = "/livingSystem/PlaybackAction_getAllPlayBack";
	$.get(url,function(data){
		if(data != null){
			var videos = JSON.parse(data);
			for(var e in videos){
				temp_li = $('<li class="list-group-item"></li>');
				temp_span = $('<span class="li-span"></span>');
				temp_span.text(videos[e].uName);
				temp_a = $('<a></a>');
				temp_a.attr("href","index.html");
				temp_a.text(new Date(videos[e].liveTime).toLocaleDateString());
				temp_li.append(temp_span);
				temp_li.append(temp_a);
				$("#livelist").append(temp_li);
			}
			
			
		}
	})
}

$(getAllPlayBack)