<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.LibBranch"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<%
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");

	List<Author> authorList = new ArrayList<Author>();
			authorList = service.getAllAuthors(1, null);
	List<Publisher> pubList =new ArrayList<Publisher>();
			pubList = service.getAllPublishers(1, null);
	List<LibBranch> branchList =new ArrayList<LibBranch>();
			branchList = service.getAllBranchs(1, null);		
	%>	
	
<html>
<head>
</head>
<h2>Add Book</h2>
<form action="addBook" method="post">
	Title: <input type="text" name="title" /><br />
	 Select Author:
	  <select name="authorId" id="selectAuthor"  multiple>
		<% for(Author a : authorList) { %>
		<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
		<% } %>
	</select><br /> 
	Select Publisher: 
	<select name="pubId" id="selectPub">
		<% for(Publisher p : pubList) { %>
		<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%></option>
		<%} %>
	</select><br /> 
	Select Branch: 
	<select name="branchId" id="selectBr">
		<% for(LibBranch br : branchList) { %>
		<option value="<%=br.getBranchId()%>"><%=br.getBranchName()%></option>
		<% } %>
	</select><br />
	<%--   <input type="hidden" name="authorId" value=<%=authorId%>>
	<input type="hidden" name="publisherId" value=<%=publisherId%>> 
	<input type="hidden" name="branchId" value=<%=branchId%>>   --%>
	<input type="submit" value="AddBook" class="btn btn-primary" />
</form>
</html>