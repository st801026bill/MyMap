let map;

$(document).ready(function(){
	init();
});

function init() {
	initView();
	initMap();
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
	
	//initial dialog update marker
	$("#dialog_update_marker").dialog({
 		autoOpen: false,
		resizable: false,
		draggable: false,
		modal: true,
		
		width: "60%",
 		show: "blind",
	 	hide: "explode"
		
 	});

	//設定分類DDL
	setKindDDL("");
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

function queryMarkers(parentId, sonId) {	
	//query markers
	var data = {};
	data.DATA={};
	
	if(parentId === "AllCountry") {
		parentId = sonId;
		sonId = "A";
	}
	data.DATA.COUNTRY_ID=parentId;
	data.DATA.CITY_ID=sonId;
	var jsonData = JSON.stringify(data);
	var result = sendRequest("POST", "application/json", "/marker/queryByKind", jsonData, "json", null);
	resultMarkers = result.DATA.MARKERS;
	
	clearMarkersArray();
	resultMarkers.forEach(marker => {
		let latlon = {
			lng: marker.LONGITUDE, 
	        lat: marker.LATITUDE
		};
		let resultMark = createMarker(map, latlon, marker.SN, 1);
		
		//add marker click event
		resultMark.addListener('click',function(){
			$("#dialog_update_marker").dialog("open");
			$('#SN').val(marker.SN);
			$('#NAME').val(marker.NAME);
			$('#COUNTRY_ID').val(marker.COUNTRY_ID);
			setKindDDL($('#COUNTRY_ID').val());
			$('#CITY_ID').val(marker.CITY_ID);
			$('#ADDRESS').val(marker.ADDRESS);
			$('#LONGITUDE').val(marker.LONGITUDE);
			$('#LATITUDE').val(marker.LATITUDE);
			$('#COMMENT').val(marker.COMMENT);
			$('#URL').val(marker.URL);
	  	});
		
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
	
	$('#COUNTRY_ID').unbind("change").bind("change", function() {
		setKindDDL($(this).val());
	});
	
	$('#saveBtn').unbind("click").bind("click", function() {
		updateMarker();
		$("#dialog_update_marker").dialog("close");
		init();
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

function updateMarker() {
	var data = {};
	data.DATA=getAllValueByForm("markerForm");
	var jsonData = JSON.stringify(data);
	var result = sendRequest("POST", "application/json", "/marker/save", jsonData, "json", null);
	alert("修改成功");
}

