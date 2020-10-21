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

function successCallback(position){
    console.log(position);
}

function errorCallback(error) {
    alert(error.message); //error.code
}