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
			console.log(($(elms[i]).attr('id')).replace("ID", "NAME"));
			returnObj[($(elms[i]).attr('id')).replace("ID", "NAME")] = valNm;
		}

		elms = $(this).find("textarea");
		for (var i=0; i < elms.length; i++){
			returnObj[$(elms[i]).attr('id')] = $(elms[i]).val();
		}
	});

	return returnObj;
}