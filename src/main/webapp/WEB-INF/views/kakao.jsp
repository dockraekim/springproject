<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>지도 생성하기</title>
    
</head>
<body>
<!-- 지도를 표시할 div 입니다 -->
<div id="map" style="width:100%;height:1000px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=324a277ee30d62459f8ffbc5edbd415b"></script>
<script>
	var center = new kakao.maps.LatLng(36.957475, 127.060148)

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: center, // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
	
	// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	
	// 기본 position을 가져오는 marker 만약 실제값이 들어오게 되면 marker는 사라져도 된다
	
	/* ------------------------------------------------------------------------- */
	/* 전역 객체 선언부 ------------------------------------------------------------- */
	
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: center
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
	drawCircle();
	
	/* 
	//지도에 표시할 원을 생성합니다
	중심좌표로부터 radius만큼 떨어진 원을 그리는 메서드
	 */
	function drawCircle() {
		let circle = new kakao.maps.Circle({
		    center : center,  // 원의 중심좌표 입니다 
		    radius: 100, // 미터 단위의 원의 반지름입니다 
		    fillColor: '#ff0000', // 채움 색
			fillOpacity: 0.5, // 채움 불투명도
			strokeColor: '#ffffff', // 선 색
			strokeOpacity: 0 // 선 투명도 
		}); 
		// 지도에 원을 표시합니다 
		circle.setMap(map);
	}
	
	var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwPosition = center; //인포윈도우 표시 위치입니다
    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

 	// 인포윈도우를 생성합니다
 	var infowindow = new kakao.maps.InfoWindow({
     content : iwContent,
     removable : iwRemoveable
 	});

 	// 마커에 클릭이벤트를 등록합니다
 	kakao.maps.event.addListener(marker, 'click', function() {
       // 마커 위에 인포윈도우를 표시합니다
       infowindow.open(map, marker);  
 	});
	
	//polygon style settings
	/* strokeWeight: 4, // 선의 두께입니다
	strokeColor: '#ffffff', // 선의 색깔입니다
	strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
	strokeStyle: 'shortdashdot', // 선의 스타일입니다
	fillColor: '#ff0000', // 채우기 색깔입니다
	fillOpacity: 0.8 // 채우기 불투명도 입니다 */
	
</script>
</body>
</html>