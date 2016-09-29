
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
			<form action="editBook" method="post">
			
				<input type="text" style="width: 400px;" name="title"
					value='<%=b.getTitle() %>' class="form-control"
					placeholder="Enter book title to Edit" required autofocus /><br />
				
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



