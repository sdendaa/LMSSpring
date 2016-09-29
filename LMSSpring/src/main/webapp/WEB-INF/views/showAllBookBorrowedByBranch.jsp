<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.BookLoan"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@include file="includes.jsp"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<%
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
LibrarianService libService = (LibrarianService)ac.getBean("LibrarianService");
BorrowerService borService = (BorrowerService) ac.getBean("BorrowerService");

String cardNoTemp = request.getParameter("cardNo");
Integer cardNo = Integer.parseInt(cardNoTemp);
String branchIdTemp = request.getParameter("branchId");
Integer branchId= Integer.parseInt(branchIdTemp);
List<Book> bookList = borService.getAllBorrowedBooks(branchId, cardNo);

%>
<form action="returnBook" method="post">
<table class="table" frame="box" rules="none">
	<tr>
		<th>Book Title</th>
		<th>Select Book</th>  
	</tr>
	<%for(Book b: bookList){ %>
	<tr>
		<td><%out.println(b.getTitle()); %></td>
		<td>
				<input type="hidden" name="bookId" value=<%=b.getBookId()%>>
				<input type="hidden" name="cardNo" value=<%=cardNo%>>
				<input type="hidden" name="branchId" value=<%=branchId%>>
				<input type="submit"  value ="returnBook" class="btn btn-primary" />
		</td>
	</tr>
	<%} %>
</table>
</form>