function sendRequest(method, contentType, uri, data, dataType, callback) {
	//var baseUrl = "http://10.11.101.121:8080/";
	var baseUrl = "http://localhost:8080/";

	var returnValue;
	var processData = true;
	if(null == callback) callback = defaultCallback;
	if(Object.prototype.toString.call(data) === "[object FormData]") processData = false;
	
	$.ajax({
        type: method,
        contentType: contentType,
        url: baseUrl + uri,
        data: data,
        dataType: dataType,
        cache: false,
        timeout: 60000,
		async: false,
		processData: processData,
        success: function (result) {
           	returnValue = result;
        },
        error: function (e) {
        	console.error(e.toString());
        	callback(e);
        }
    });
	return returnValue;  
}

function defaultCallback (e) {
    $("#result").val("伺服器錯誤");
}