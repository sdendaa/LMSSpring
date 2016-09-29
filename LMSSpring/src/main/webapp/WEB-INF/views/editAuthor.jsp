
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%

ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");
	int authorId = Integer.parseInt(request.getParameter("authorId"));
	Author a = service.getAuthorByID(authorId);

%>
		<div class="modal-content">
			<form action="editAuthor" method="post">
				<input type="text" style="width: 400px;" name="authorName"
					value='<%=a.getAuthorName()%>' class="form-control"
					placeholder="Enter Author's name to Edit" required autofocus /><br />
				<input type="hidden" name="authorId" value=<%=a.getAuthorId()%>>
				<%-- <input type="hidden" name="authorName" value=<%=a.getAuthorName()%>> --%>
				<input type="submit" class="btn btn-primary" />
				<a href="http://localhost:8080/lms/showAllAuthors" >Cancel</a>
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



