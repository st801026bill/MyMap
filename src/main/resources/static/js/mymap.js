let map;
let resultMarkers;

$(document).ready(function(){
	init();
	initMap();
	initButton();
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
		
	queryMarkers("A", "A");
}

function initButton() {
	$('.markerList').bind("click", function() {
		let result = resultMarkers.filter(marker => marker.SN == this.id);
		let latlon = {
			lng: parseFloat(result[0].LONGITUDE), 
	        lat: parseFloat(result[0].LATITUDE)
		};

		map.panTo(latlon);
		map.setZoom(15);
	});
	
	$('.markerKindList a').unbind("click").bind("click", function() {
		queryMarkers($(this).closest('.markerKindList').attr('id'), this.id);
	});
}

function queryMarkers(countryId, cityId) {
	//query markers
	var data = {};
	data.DATA={};
	data.DATA.COUNTRY_ID=countryId;
	data.DATA.CITY_ID=cityId;
	var jsonData = JSON.stringify(data);
	var result = sendRequest("POST", "application/json", "/marker/queryByKind", jsonData, "json", null);
	resultMarkers = result.DATA.MARKERS;
	
	clearMarkersArray();
	resultMarkers.forEach(marker => {
		let latlon = {
			lng: marker.LONGITUDE, 
	        lat: marker.LATITUDE
		};
		let resultMark = setMarker(map, latlon, marker.SN, 1);
		let infoMsg = "<div align='left'><b>名稱："+ marker.NAME +"</div>";
		setMarkerInfo(map, resultMark, infoMsg);
		
		pushMarkersArray(resultMark);
	});
	
	let content;
	const bounds = new google.maps.LatLngBounds();
	let latlon;
	
	$('.list-group').empty();
	resultMarkers.forEach(marker => {
		let latlon = {
			lng: parseFloat(marker.LONGITUDE), 
	        lat: parseFloat(marker.LATITUDE)
		};
		bounds.extend(latlon);
		content = "名稱："+ marker.NAME +"<br>"+ marker.COMMENT;
		$('.list-group').append("<a class='list-group-item list-group-item-action markerList' id='"+ marker.SN +"' target='_blank'>"+ content +"</a>");
	});
	map.fitBounds(bounds);

	initButton();
}

