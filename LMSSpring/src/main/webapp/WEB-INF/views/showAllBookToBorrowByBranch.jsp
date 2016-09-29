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

String cardNoTemp = request.getParameter("cardNo");
Integer cardNo = Integer.parseInt(cardNoTemp);
String branchIdTemp = (String) request.getParameter("branchId");
Integer branchId = Integer.parseInt(branchIdTemp);
List<Book> books = libService.getAllBooksFromBranch(branchId, null, null);
%>

<table class="table" frame="box" rules="none">
	<tr>
		<th>Book Title</th>
		<th>Number of copies</th>
		<th>checkout Book</th>
	</tr>
	<%for(Book b: books){ %>
	<tr>
	<%if(libService.getNoOfCopyByBranch(branchId, b.getBookId()) >0){ %>
		<td><%out.println(b.getTitle()); %></td>
		<td><%out.println(libService.getNoOfCopyByBranch(branchId, b.getBookId())); %></td>
		<td>
		<%} %>
				<button  onclick ="javascript:location.href='checkOut?bookId=<%=b.getBookId() %>&branchId=<%=branchId %>&cardNo=<%=cardNo%>'"> check out</button>
		</td>
	</tr>
	<%} %>
</table>

