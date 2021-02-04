<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Sensor Page</title>
</head>
<body>
	<h1>sensor</h1>
	<table>
     <tr>
        <td>Sensor ID</td>
        <td>�µ�</td>
        <td>����</td>
        <td>�̼�����</td>
        <td>�ʹ̼�����</td>
        <td>���ʹ̼�����</td>
        <td>�̻�ȭź��</td>
        <td>TVOC</td>
        <td>Date</td>
    </tr>
	<c:forEach items="${sensorList}" var="sensorList" varStatus="vs">
    <tr>
        <td>${sensorList.sensorId}</td>
        <td>${sensorList.WP001}</td>
        <td>${sensorList.WP002}</td>
        <td>${sensorList.WP007}</td>
        <td>${sensorList.WP008}</td>
        <td>${sensorList.WP023}</td>
        <td>${sensorList.WP028}</td>
        <td>${sensorList.WP031}</td>
        <td>${sensorList.createTime}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>