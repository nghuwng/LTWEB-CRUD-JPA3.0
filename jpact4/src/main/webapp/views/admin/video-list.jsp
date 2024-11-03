<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <a href="<c:url value="/admin/video/add"></c:url>">Add Video</a>
<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>Video ID</th>
        <th>Video Title</th>
        <th>Views</th>
        <th>Category</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
	<%-- Debug số lượng video --%>
	<c:out value="${listVideos.size()}" />
	
    <c:forEach items="${listvideo}" var="video" varStatus="STT">
        <tr>
            <td>${STT.index+1 }</td>
            <td>${video.videoId }</td>
            <td>${video.videoTitle }</td>
            <td>${video.views }</td>
            <td>${video.category.categoryname }</td>
            <td>
                <c:if test="${video.status == 1 }"> 
                    Hoạt động
                </c:if>
                <c:if test="${video.status != 1 }"> 
                    Khóa
                </c:if>
            </td>
            <td>
                <a href="<c:url value='/admin/video/edit?id=${video.videoId }'/>">Sửa</a>
                | <a href="<c:url value='/admin/video/delete?id=${video.videoId }'/>">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>