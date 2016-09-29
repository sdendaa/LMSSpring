<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@include file="includes.jsp"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<%
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");

	Integer totalCount = service.getBorrowerCount();
	Integer pageSize = 10;
	Integer pageCount = 0;
	if (totalCount % pageSize > 0) {
		pageCount = totalCount / pageSize + 1;
	} else {
		pageCount = totalCount / pageSize;
	}

	List<Borrower> borrList = new ArrayList<Borrower>();
	if (request.getAttribute("borrowers") != null) {
		borrList = (List<Borrower>) request.getAttribute("borrowers");
	} else {
		borrList = service.getAllBorrowers(1, null);
		
	}
%>
<html>
<head>
<title>List of available Borrower in data</title>
</head>
<body>

	<div class="input-group">
		<form action="searchBorrowers" method="post">
			<input type="text" class="form-control" placeholder="borrower Name"
				name="searchString" aria-describedby="basic-addon1">
			<button type="submit">Search!</button>
		</form>
	</div>
	<nav aria-label="Page navigation">
		<ul class="pagination">
			<%
				for (int i = 1; i <= pageCount; i++) {
			%>
			<li><a href="pageBorrowers?pageNo=<%=i%>"><%=i%></a></li>
			<%
				}
			%>
		</ul>
	</nav>
	<h2>Borrowers List</h2>
	<div class="form-group">
		<table class="table" frame="box" rules="none">
			<tr>
				<th>Index</th>
				<th>Name</th>
				<th>Address</th>
				<th>phone</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<%
				for (Borrower bor : borrList) {
			%>
			<tr>
				<td><%=borrList.indexOf(bor) %></td>
				<td><%=bor.getName()%></td>
				<td><%=bor.getAddress()%></td>
				<td><%=bor.getPhone()%></td>
				<td><button class="btn btn-sm btn-success" data-toggle="modal" data-target="#myModal1"
						href='editBorrower?cardNo=<%=bor.getCardNo()%>'>Edit</button>
				<td><button class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal1" 
				 href='deleteBorrower?cardNo=<%=bor.getCardNo()%>'>Delete</button>
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