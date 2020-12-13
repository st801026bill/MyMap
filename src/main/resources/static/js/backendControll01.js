let map;

$(document).ready(function(){
	init();
});

function init() {
	initView();
	initMap();
	initMapEvent();
	initValidate();
	initButton();
}

function initView() {
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
	
	//設定分類DDL
	setKindDDL("");
}


function initMap() {
	var taiwan = new google.maps.LatLng(23.64550, 120.99705);	
	var mapOptions = {
        zoom: 7,
        center: taiwan,       //中心座標
        scaleControl: true,    //比例尺

        //MapType位置
        mapTypeControl: false,
        mapTypeControlOptions: {
            style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
            position: google.maps.ControlPosition.TOP_LEFT
        },

        //Zoom位置
        zoomControl: false,
        zoomControlOptions: {
            position: google.maps.ControlPosition.TOP_RIGHT
        },

        //小人位置
        streetViewControl: false,
        streetViewControlOptions: {
            position: google.maps.ControlPosition.TOP_RIGHT
        }
    };
	map = new google.maps.Map(document.getElementById("map"), mapOptions);
	
	//取得使用者位置
	gettingPosition()
		.then(position => successCallback(map, position))
		.catch(error => errorCallback(error));
}

function initMapEvent() {
	//搜尋方塊
	let searchBox = initSearchBox("searchbox-input");
	searchBox.addListener("places_changed", () => {
		const places = searchBox.getPlaces();
		
		if (places.length == 0) return;
		
		// Clear out the old markers.
	    clearMarkersArray();
		
		// For each place, get the icon, name and location.
	    const bounds = new google.maps.LatLngBounds();
	    places.forEach((place) => {
	      	if (!place.geometry) {
	        	console.log("Returned place contains no geometry");
	        	return;
	      	}
	      	const icon = {
	        	url: place.icon,
	        	size: new google.maps.Size(71, 71),
	        	origin: new google.maps.Point(0, 0),
	        	anchor: new google.maps.Point(17, 34),
	        	scaledSize: new google.maps.Size(25, 25),
	      	};
			
	      	// Create a marker for each place.
			let marker = createMarker(map, place.geometry.location, place.name, 1);
			setMarkerInfo(map, marker, place.name);
			marker.formatted_address = place.formatted_address;
			pushMarkersArray(marker);

	      	if (place.geometry.viewport) {
	        	// Only geocodes have viewport.
	        	bounds.union(place.geometry.viewport);
	      	} else {
	        	bounds.extend(place.geometry.location);
	      	}
	    });
	    map.fitBounds(bounds);
		setMarkersEvent();
		setMarkerDetail();
	});
	
	//初始化地址搜尋元件
	initGeocoder();
}

function initValidate() {
	$("#markerForm").validate({
	    rules: {
	      NAME:			"required",
	      COUNTRY_ID:	"required",
	      CITY_ID:		"required",
	      ADDRESS:		"required",
	      LONGITUDE:	"required",
	      LATITUDE:		"required"
	    },
	    messages: {
	      NAME: 		"請輸入必填欄位",
	      COUNTRY_ID: 	"請輸入必填欄位",
	      CITY_ID: 		"請輸入必填欄位",
	      ADDRESS: 		"請輸入必填欄位",
	      LONGITUDE: 	"請輸入必填欄位",
	      LATITUDE: 	"請輸入必填欄位",
    	}
	});
}

function initButton() {
	$('#addMarker').bind("click", function() {
		if(!$("#markerForm").valid()) return;
	
		var data = {};
		data.DATA=getAllValueByForm("markerForm");
		var jsonData = JSON.stringify(data);
		var result = sendRequest("POST", "application/json", "/marker/add", jsonData, "json", null);
		alert(result);
	});
	
	$('#searchAddress').bind("click", function() {
		geocoderAddress($('#ADDRESS').val());
		setMarkerDetail();
	});
	
	$('#COUNTRY_ID').unbind("change").bind("change", function(){
		setKindDDL($(this).val());
	});
}

function setMarkerDetail() {
	let markers = getMarkers();
	if(markers.length === 1) {
		$('#NAME').val(markers[0].title);
		$('#ADDRESS').val(markers[0].formatted_address);
		//$('#LONGITUDE').val(markers[0].position.lng);
		//$('#LATITUDE').val(markers[0].position.lat);
	} else {
		$('#NAME').val("");
		$('#ADDRESS').val("");
		$('#LONGITUDE').val("");
		$('#LATITUDE').val("");
	}	
}


function setMarkersEvent() {
	let markers = getMarkers();
	
	markers.forEach((marker) => {
		marker.addListener('click',function(){
			map.setCenter(marker.getPosition());
			
			$('#NAME').val(marker.title);
			$('#ADDRESS').val(marker.formatted_address);
			$('#LONGITUDE').val(marker.position.lng);
			$('#LATITUDE').val(marker.position.lat);
			
			marker.setAnimation(google.maps.Animation.BOUNCE);
			marker.setDraggable(true);
			google.maps.event.addListener(marker, 'dragend', function() {
		        $('#NAME').val(marker.title);
				$('#ADDRESS').val(marker.formatted_address);
				$('#LONGITUDE').val(marker.position.lng);
				$('#LATITUDE').val(marker.position.lat);
				
				marker.setAnimation(null);
				marker.setDraggable(false);
	      	});
		});
	});
}

function setKindDDL(countryId) {
	//設定分類DDL
	var data = {};
	data.DATA = {};
	data.DATA.COUNTRY_ID = countryId;
	let jsonData = JSON.stringify(data);
	let result = sendRequest("POST", "application/json", "/marker/kindDDL", jsonData, "json", null);
	let resultDDL = result.DATA.KIND;
	
	if(countryId === "") {
		$('#COUNTRY_ID').empty();
		$('#COUNTRY_ID').append("<option value=''>請選擇...</option>");
		resultDDL.forEach(kind => {
			$('#COUNTRY_ID').append("<option value='"+ kind.COUNTRY_ID +"'>"+ kind.COUNTRY_NAME +"</option>");
		});
		$('#CITY_ID').empty();
		$('#CITY_ID').append("<option value=''>請選擇...</option>");
	} else {
		$('#CITY_ID').empty();
		$('#CITY_ID').append("<option value=''>請選擇...</option>");
		resultDDL.forEach(kind => {
			$('#CITY_ID').append("<option value='"+ kind.CITY_ID +"'>"+ kind.CITY_NAME +"</option>");
		});
	}
}
