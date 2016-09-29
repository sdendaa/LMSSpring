<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.LibBranch"%>
<%@include file="includes.jsp"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<%
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");
LibrarianService libService = (LibrarianService)ac.getBean("LibrarianService");

String branchIdTemp = request.getParameter("branchId");
Integer branchId = Integer.parseInt(branchIdTemp);
LibBranch br = service.getBranchById(branchId);
List<Book> books = libService.getAllBooksFromBranch(branchId, null, null);
%>

<table class="table" frame="box" rules="none">
	<tr>
		<th>Book Title</th>
		<th>Number of copies</th>  
		<th>Select Book</th>
		
	</tr>
	<%for(Book b: books){ 
	%>
	<tr>
	<%if(libService.getNoOfCopyByBranch(branchId, b.getBookId())>=0){ %>
		<td><%out.println(b.getTitle()); %></td>
		<td><%out.println(libService.getNoOfCopyByBranch(branchId, b.getBookId())); %></td>
		<td>
		<input type="button"  class="btn btn-md btn-success" value="Select" onclick="javascript:location.href='addNoCopies?bookId=<%=b.getBookId()%>&branchId=<%=branchId%>'">
		</td>
		<%} %>
	</tr>
	<%} %>
</table>

<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>