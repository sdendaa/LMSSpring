<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@include file="includes.jsp"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>

<%
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");
	
	Integer totalCount = service.getBookCount(null);
	Integer pageSize = 10;
	Integer pageCount = 0;        
	if (totalCount % pageSize > 0) {
		pageCount = totalCount / pageSize + 1;
	} else {
		pageCount = totalCount / pageSize;
	}
	List<Book> bookList = new ArrayList<Book>();
	if (request.getAttribute("books") != null) {
		bookList = (List<Book>) request.getAttribute("books");
	} else {
		bookList = service.getAllBooks(1, null);
	}
%>
<html>
<head>
<title>List of available Books in data</title>
</head>
<body>

	<div class="input-group">
		<form action="searchBooks" method="post">
			<input type="text" class="form-control" placeholder="Book Name"
				name="searchString" aria-describedby="basic-addon1">
			<button type="submit">Search!</button>
		</form>
	</div>
	<nav aria-label="Page navigation">
		<ul class="pagination">
			<%
				for (int i = 1; i <= pageCount; i++) {
			%>
			<li><a href="pageBooks?pageNo=<%=i%>"><%=i%></a></li>
			<%
				}
			%>
		</ul>
	</nav>

	<h2>Book List</h2>
	<div class="form-group">
		<table class="table" frame="box" rules="none">
			<tr>
				<th>Index</th>
				<th>Book Title</th>
				<th>Author Name</th> 
				<th>Publisher Name</th>
				<th>Edit</th>
				<th>Delete</th>

			</tr>
			<%
				for (Book b : bookList) {
			%>
			<tr>
				<td><%=bookList.indexOf(b) %></td>
				<td><%=b.getTitle()%></td>
			 <td>
					<%
							List<Author>authors =service.getAllAuthorsByBook(b.getBookId());	
									if(authors!=null && !authors.isEmpty()){
 												for (Author a : authors) {
 													%> <%=a.getAuthorName()+", "%> 
 										<%	}
 								  }
 								%> 
				</td>  
				<td><%=service.getPublisherById(b.getPublisher().getPublisherId()).getPublisherName()%></td>
				<td><button class="btn btn-sm btn-success" data-toggle="modal"
						data-target="#myModal1" href='editBook?bookId=<%=b.getBookId()%>'>Edit</button>
				<td><button class="btn btn-sm btn-danger" data-toggle="modal"
						data-target="#myModal1"
						href='deleteBook?bookId=<%=b.getBookId()%>'>Delete</button>
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