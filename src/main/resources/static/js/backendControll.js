let map;

$(document).ready(function(){
	init();
	initMap();
});

function init() {
	$("#menu-toggle").click(function(e) {
     	e.preventDefault();
     	$("#wrapper").toggleClass("toggled");
 	});

 	$(window).resize(function(e) {
   		if($(window).width()<=768){
     		$("#wrapper").removeClass("toggled");
   		}else{
     		$("#wrapper").addClass("toggled");
   	}
 	});
}


function initMap() {
	var mapOption = {
		center: { lat: 0, lng: 0 },
		zoom: 10,
	}
	map = new google.maps.Map(document.getElementById("map"), mapOption);
	
	//取得使用者位置
	gettingPosition()
		.then(position => successCallback(map, position))
		.catch(error => errorCallback(error));
	
	//query all markers
	var data = {};
	data.DATA={};
	var jsonData = JSON.stringify(data);
	var result = sendRequest("POST", "application/json", "/marker/queryAll", jsonData, "json", null);
	var markers = result.DATA.MARKERS;
	markers.forEach(marker => {
		let latlon = {
			lng: marker.LONGITUDE, 
	        lat: marker.LATITUDE
		};
		let resultMark = addMarker(map, latlon, marker.SN, 1);
		let infoMsg = "<div align='left'><b>名稱："+ marker.NAME +"</div>";
		setMarkerInfo(map, resultMark, infoMsg)
	});
	
	markers.forEach(marker => {
		$('.list-group').append("<a class='list-group-item list-group-item-action' href='"+ marker.URL +"' target='_blank'>"+ marker.NAME +"<br>"+ marker.COMMENT +"</a>");
	});
	
}

