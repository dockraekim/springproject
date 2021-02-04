<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
<style>
  #map {
    height: 100%;
  }
  html, body {
    height: 100%;
    margin: 0;
    padding: 0;
  }
  button {
  width:100px;
  height:100px;
  z-index:100;
  position:absolute;
  display:block;
  
  }
</style>
</head>
<body>
	<div id="map"></div>
	<button onclick="panToBounds" data-lat="37.584" data-lng="126.969" style="display:hidden">DataSource</button>
	
	<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
	<script defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDoOvnNELexDg0t_oPNpMeZ6rYtatviBHY&callback=getLocation">
	</script>
	<script type="text/javascript">
	
	posArr = [];
	markerArr = [];
	
	/* 
	document 로딩되고 나서 실행될 메소드
	 */
	$(function(){
	initlat = 37.58461179555119;
	initlng = 126.9699817327947;
	
	let locadata = document.getElementsByTagName("button")[0];
	let datalat = Number(locadata.dataset["lat"]);
	let datalng = Number(locadata.dataset["lng"]);
	
	initloca = { lat: initlat, lng: initlng };
	locadata = { lat: datalat, lng: datalng };
	
	map = new google.maps.Map(document.getElementById("map"), {
	   		zoom: 18,
	    	center: initloca,
		});
	
	addMarker(initlat,initlng);
	addMarker(locadata);
	})
		
	
	function addMarker(...arguments) {
		if(arguments.length==1){
			let datalat= arguments[0]["lat"];
			console.log(arguments[0]["lat"]);
			let datalng= arguments[0]["lng"];
			console.log(arguments[0]["lng"]);
			let marker = new google.maps.Marker({
			    position: {lat:datalat, lng:datalng},
			    map: map,
			});
			
			google.maps.event.addListener(marker, "click", () => {
				panTo(marker.getPosition());
			  });
			
			markerArr.push(marker);
			return marker;
		}
		
		else {
			console.log(arguments[0]);
			console.log(arguments[1]);
			let marker = new google.maps.Marker({
			    position: {lat:arguments[0], lng:arguments[1]},
			    map: map,
			});
			
			google.maps.event.addListener(marker, "click", () => {
				panTo(marker.getPosition());
			  });
			
			markerArr.push(marker);
			return marker;
		}
	}
	
		
	
	
	/* 
	현재의 gps 좌표를 반환하는 메소드
	*/
	function getLocation() {
		  if (navigator.geolocation) { // GPS를 지원하면
		    navigator.geolocation.getCurrentPosition(function(position) {
		      alert(position.coords.latitude+ " : " +position.coords.longitude)
		      
		      let myloca = { lat: position.coords.latitude, lng: position.coords.longitude };
		    
		    }, function(error) {
		      console.error(error);
		    }, {
		      enableHighAccuracy: false,
		      maximumAge: 0,
		      timeout: Infinity
		    });
		  } else {
		    alert('GPS를 지원하지 않습니다');
		  }
	}
	
	
	function panTo(position) {
		map.panTo(position);
	}
	</script>
	
</body>
</html>