let map;

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

function initButton() {
	$('#addMarker').click(function(){
		
	});
}

