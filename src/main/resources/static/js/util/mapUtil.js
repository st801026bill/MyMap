const markerImg = [
	"http://maps.google.com/mapfiles/ms/icons/red-dot.png",		//red
	"http://maps.google.com/mapfiles/ms/icons/yellow-dot.png",	//yellow
	"http://maps.google.com/mapfiles/ms/icons/green-dot.png",	//green
];

let lastInfo = null;
let geocoder;
let markers = [];

//建立SearchBox
function initSearchBox(searchBoxId) {
	const input = document.getElementById(searchBoxId);
	const searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
	return searchBox;
}

function initGeocoder() {
	geocoder = new google.maps.Geocoder();
}

//取得使用者位置
function gettingPosition(){
	if(navigator.geolocation){
        return new Promise((resolve, reject) => {
            let option = {
                enableAcuracy:false, // 提高精確度
                maximumAge:0, // 設定上一次位置資訊的有效期限(毫秒)
                timeout:10000 // 逾時計時器(毫秒)
            };
            navigator.geolocation.getCurrentPosition(resolve, reject, option);
        })
    }else{
        alert("Does not support positioning!");
    }
}

function successCallback(map, position){
    console.log(position);
	let latlon = {
		lng: position.coords.longitude, 
        lat: position.coords.latitude
	};
	let marker = createMarker(map, latlon, "您的位置", 0);
	setMarkerInfo(map, marker, "您的位置");
	
	map.setCenter(latlon); //定位到目前位置
    map.setZoom(16);
}

function errorCallback(error) {
    alert(error.message); //error.code
}

function geocoderAddress(address) {
	let resultMarker;
	geocoder.geocode( { 'address': address}, function(results, status) {
    	if (status == 'OK') {
	      	map.setCenter(results[0].geometry.location);
			resultMarker = createMarker(map, results[0].geometry.location, address, 2);
			setMarkerInfo(map, resultMarker, address);
			resultMarker.formatted_address = address;
			
			if(!resultMarker) return;
			clearMarkersArray();
			pushMarkersArray(resultMarker);
			setMarkersEvent();
    	}
  	});
}

function createMarker(map, latlon, title, index) {
	let icon =  isNaN(index)? index : markerImg[index];
		
	var marker = new google.maps.Marker({
	    position: latlon,
	    map,
	    title: title.toString(),
		icon: icon,
  	});
	return marker;
}

function setMarkerInfo(map, marker, infoMsg) {
	if(!map || !marker) return;
	
	var info = new google.maps.InfoWindow({
        content: infoMsg
    });
    marker.addListener('click', function() {
		if(lastInfo !== null) lastInfo.close();
        info.open(map, marker);
		lastInfo = info;
    });
}

// Clear out the old markers.
function clearMarkersArray () {
	if(!markers) return;
	
    markers.forEach((marker) => {
      	marker.setMap(null);
    });
    markers = [];  
}

function pushMarkersArray(marker) {
	markers.push(marker);
}

function getMarkers() { return markers }