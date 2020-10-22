const markerImg = [
	"http://maps.google.com/mapfiles/ms/icons/red-dot.png",		//red
	"http://maps.google.com/mapfiles/ms/icons/yellow-dot.png",	//yellow
	"http://maps.google.com/mapfiles/ms/icons/green-dot.png",	//green
];

let lastInfo = null;

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
	addMarker(map, latlon, "您的位置", 0);
	
	map.setCenter(latlon); //定位到目前位置
    map.setZoom(16);
}

function errorCallback(error) {
    alert(error.message); //error.code
}

function addMarker(map, latlon, title, index) {
	var marker = new google.maps.Marker({
	    position: latlon,
	    map,
	    title: title.toString(),
		icon: markerImg[index],
  	});
	return marker;
}

function setMarkerInfo(map, marker, infoMsg) {
	var info = new google.maps.InfoWindow({
        content: infoMsg
    });
    marker.addListener('click', function() {
		if(lastInfo !== null) lastInfo.close();
        info.open(map, marker);
		lastInfo = info;
    });
}
