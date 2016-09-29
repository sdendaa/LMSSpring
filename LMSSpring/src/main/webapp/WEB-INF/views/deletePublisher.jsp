
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<%
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");

	int pubId = Integer.parseInt(request.getParameter("publihserId"));
	Publisher p = service.getPublisherById(pubId);
%>
		<div class="modal-content">
			<form action="deletePublisher" method="post">
				<input type="text" style="width: 600px;" name="publisherName"
					value=' You are about to delete <%=p.getPublisherName()%>. Do you want to submit?'  /><br />
				<input type="hidden" name="publisherId" value=<%=p.getPublisherId()%>>
				<input type="submit" class="btn btn-primary" />
				<a href="http://localhost:8080/lms/showAllPublishers" >Cancel</a>
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
