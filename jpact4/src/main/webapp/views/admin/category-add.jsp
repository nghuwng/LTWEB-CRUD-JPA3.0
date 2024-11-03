<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="<c:url value="/admin/category/insert"/>" method="post" 
		enctype="multipart/form-data">
  	<label for="fname">Category Name:</label><br> 
  	<input type="text" id="categoryname" name="categoryname"><br> 
  	
  	<label for="lname">Images:</label><br> 
  		<img height="150" width="200" src="" id="imagess" />
  	<input type="file" onchange="chooseFile(this)" id="images" name="images"><br>
  	  	
  	<label for="html">Status:</label><br> 
  	<input type="radio" id="ston" name="status" value="1">
  	<label for="css">Hoạt động</label><br>
  	<input type="radio" id="stoff" name="status" value="0">
  	<label for="javascript">Khóa</label><br>
  	
  	<br> <input type="submit" value="Insert">
</form> 