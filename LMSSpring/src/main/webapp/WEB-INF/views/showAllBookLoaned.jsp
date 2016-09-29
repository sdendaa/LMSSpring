<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.BookLoan"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@include file="includes.jsp"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%

ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");
	List<BookLoan> loanList = new ArrayList<BookLoan>();
	loanList =service.getAllBooksLoaned(1, null);
%>
<html>
<head>
<title>List of  Books loaned in data</title>
</head>
<body>

	<div class="form-group">
		<table class="table" frame="box" rules="none">
			<tr>
				<th>Index</th>
				<th>Borrower Name</th>
				<th>Branch Name</th>
				<th>Book Title</th>
				<th>CheckOut Date</th>
				<th>Due Date</th>
				<th>Edit</th>
			</tr>
			<%for (BookLoan bl : loanList) {%>
			<tr>
				<td><%=loanList.indexOf(bl)%></td>
				<td><%=service.getBorrowerById(bl.getBorrower().getCardNo()).getName()%></td>
				<td><%=service.getBranchById(bl.getBranch().getBranchId()).getBranchName()%></td>
				<td><%=service.getBookByID(bl.getBook().getBookId()).getTitle()%></td>
				<td><%=bl.getCheckOutDate()%></td>
				<td><%=bl.getDueDate()%></td>
				<td><button class="btn btn-sm btn-success" data-toggle="modal"
						data-target="#myModal1"
						href='editBookloan?bookId=<%=bl.getBook().getBookId()%>&branchId=<%=bl.getBranch().getBranchId()%>&cardNo=<%=bl.getBorrower().getCardNo()%>'>Over-Ride</button></td>

			</tr>
			<%
				}
			%>
		</table>
	</div>

	<div id="myModal1" class="modal fade bs-example-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content"></div>
		</div>
	</div>

</body>
</html>