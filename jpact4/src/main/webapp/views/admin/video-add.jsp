<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <form action="<c:url value="/admin/video/insert"/>" method="post" 
		enctype="multipart/form-data">
  	
  	<label for="lname">Images:</label><br> 
  	<input type="file" onchange="chooseFile(this)" id="images" name="images"><br>
  	
  	<label for="fname">Video ID:</label><br> 
  	<input type="text" id="videoid" name="videoid"><br>
  	
  	<label for="fname">Video Title:</label><br> 
  	<input type="text" id="videoid" name="videoid"><br>
  	
  	<label for="category">Category:</label><br>
    <select id="category" name="category">
        <option value="">-- Chọn danh mục --</option>
        <c:forEach items="${categories}" var="cate">
            <option value="${cate.categoryId}">${cate.categoryname}</option>
        </c:forEach>
    </select><br>  	
  	  	
  	<label for="html">Status:</label><br> 
  	<input type="radio" id="ston" name="status" value="1">
  	<label for="css">Hoạt động</label><br>
  	<input type="radio" id="stoff" name="status" value="0">
  	<label for="javascript">Khóa</label><br>
  	
  	<br> <input type="submit" value="Insert">
</form> 