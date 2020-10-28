/**
 * 
 */
function getAllValueByForm(formName){
	var returnObj = {};
	$("#"+formName).each(function(){
	
		var elms = $(this).find("input[type = text]")
		for (var i=0; i < elms.length; i++){
			returnObj[$(elms[i]).attr('id')] = $(elms[i]).val();
		}
		
		var elms = $(this).find("input[type = hidden]")
		for (var i=0; i < elms.length; i++){
			returnObj[$(elms[i]).attr('id')] = $(elms[i]).val();
		}

		elms = $(this).find("input[type = radio]");
		for (var i=0; i < elms.length; i++){
			if ( $(elms[i]).prop('checked') ){
				returnObj[$(elms[i]).attr('id')] = $(elms[i]).val();
			}
		}

		elms = $(this).find("input[type = checkbox]");
		var cbxVehicle = new Array();
		var cbxVehicleNM = new Array();
		var oldName = "";
		for (var i=0; i < elms.length; i++){
			if ( $(elms[i]).attr('id') != oldName ){
				returnObj[oldName] = cbxVehicle.join();
				returnObj[oldName+"NM"] = cbxVehicleNM.join();
				oldName = $(elms[i]).attr('id');
				cbxVehicle = new Array();
				cbxVehicleNM = new Array();
			}
			
			if ( $(elms[i]).prop('checked') ){
				cbxVehicle.push($(elms[i]).val());
				cbxVehicleNM.push($(elms[i]).attr('nm'));
			}
			
			if ( i === (elms.length-1) ){
				if ( oldName === $(elms[i]).attr('id') ){
					returnObj[oldName] = cbxVehicle.join();
					returnObj[oldName+"NM"] = cbxVehicleNM.join();
				}
			}
		}

		elms = $(this).find("select");
		for (var i=0; i < elms.length; i++){
			returnObj[$(elms[i]).attr('id')] = $(elms[i]).val();
			var valNm = $(elms[i]).find("option:selected").text();
			if ( valNm.indexOf("請選擇") >= 0 ){
				valNm = "";
			}
			console.log($(elms[i]).attr('id')+'NM');
			returnObj[$(elms[i]).attr('id')+'NM'] = valNm;
		}

		elms = $(this).find("textarea");
		for (var i=0; i < elms.length; i++){
			returnObj[$(elms[i]).attr('id')] = $(elms[i]).val();
		}
	});

	return returnObj;
}


/**
 * 	取得登入人員資料
 */
function getOperatorDDL() {
	var data = {};
	return sendRequest("GET", "application/json", "operator", data, "json", null);
}

/**
 * 	取得畫面要顯示的欄位json
 */
function getPageColumn(srcPage) {
	var returnValue;
	var data = {"target": srcPage};
	return sendRequest("GET", "application/json", "display", data, "json", null);
}
		
function generatePage(jsonData, formID) {
	jsonData.forEach((column) => {
		//地址
		if(column.columnType === "text" && column.detail.length>0)
			$("#"+formID+">.row").append(textsHtml(column));
		else if(column.columnType === "text" || 
			column.columnType === "number" || 
			column.columnType === "date" )
			$("#"+formID+">.row").append(textHtml(column));
		else if(column.columnType === "radio") {
			$("#"+formID+">.row").append(radioHtml(column));
		}
	});
}

function radioHtml(column) {
	var radioHtml_Head =
		"<div class='col-lg-6 col-md-6'>"
	        + "<div class='form-group'>"
				+"<label for='rdo'> {#TEXT} : &nbsp&nbsp</label>"
	var item = 
		"<div class='form-check-inline'>"
  			+ "<label class='form-check-label'>"
    			+ "<input type='radio' class='form-check-input' id='{#ID}' value='{#VALUE}' name='optradio'>{#ITEM}"
  			+ "</label>"
		+ "</div>";
	var radioHtml_Footer =
			"</div>"
		+ "</div>";
	
	var itemHtml ="";
	column.detail.forEach(detail => {
		var i = item.replace("{#ID}", detail.columnKey)
			.replace("{#ITEM}", detail.detailText)
			.replace("{#VALUE}", detail.defaultValue);
		itemHtml+=i;
	});
	
	return radioHtml_Head.replace("{#TEXT}", column.columnText) + itemHtml + radioHtml_Footer;
}
function textHtml(column) {
	var textHtml =
		"<div class='col-lg-6 col-md-6'>"
	        + "<div class='form-group'>"
				+ "<label for='usr'> {#TEXT} :</label>"
				+ "<input type='text' class='form-control' id='{#ID}' value='{#VALUE}'>"
			+ "</div>"
		+ "</div>";
		return textHtml.replace("{#TEXT}", column.columnText)
				.replace("{#ID}", column.columnKey)
				.replace("{#VALUE}", "");
}

function textsHtml(column) {
	var textHtml_Head =
		"<div class='col-lg-6 col-md-6'>"
	        + "<div class='form-group'>"
				+ "<label for='txt'> {#TEXT} :</label>"
	var text = "<input type='text' class='form-control' id='{#ID}' placeHolder='{#VALUE}'>";
	var textHtml_Footer =
			"</div>"
		+ "</div>";
		
	var itemHtml ="";
	column.detail.forEach(detail => {
		var t = text.replace("{#ID}", detail.subColumnKey)
			.replace("{#VALUE}", detail.detailText);
		itemHtml+=t;
	});
	
	return textHtml_Head.replace("{#TEXT}", column.columnText) + itemHtml + textHtml_Footer;
}
