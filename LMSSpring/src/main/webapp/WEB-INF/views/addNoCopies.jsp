<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<%
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");
 String bookId = request.getParameter("bookId");
 String branchId = request.getParameter("branchId");
 Book book = service.getBookByID(Integer.parseInt(bookId));
%>
<div class="modal-body">
<form action="addNoCopies" method="post">
			Enter Number of Copies: <input type="text" name="noOfCopies">
			<input type="hidden" name="bookId" value=<%=bookId %>>
			<input type="hidden" name="branchId" value=<%=branchId%>>
		<input type="submit"/>
</form>
</div>
