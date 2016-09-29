
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<%
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");
	int bookId = Integer.parseInt(request.getParameter("bookId"));
	Book b = service.getBookByID(bookId);
%>
		<div class="modal-content">
			<form action="deleteBook" method="post">
				<input type="text" style="width: 600px;" name="tilte"
					value=' You are about to delete <%=b.getTitle()%>. Do you want to submit?'  /><br />
				<input type="hidden" name="bookId" value=<%=b.getBookId()%>>
				<input type="submit" class="btn btn-primary" />
				<a href="http://localhost:8080/LibraryWebApp/index.jsp" >Cancel</a>
			</form>
		</div>
		
<script type="text/javascript">
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>
