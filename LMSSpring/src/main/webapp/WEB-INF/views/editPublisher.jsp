
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<%
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");

	int publisherId = Integer.parseInt(request.getParameter("publisherId"));
	Publisher p = service.getPublisherById(publisherId);
%>
		<div class="modal-content">
			<form action="editPublisher" method="post">
			
				<input type="text" style="width: 400px;" name="publisherName"
					value='<%=p.getPublisherName()%>' class="form-control"
					placeholder="Enter publisher's name to Edit" required autofocus /><br />
					
					<input type="text" style="width: 400px;" name="publisherAddress"
					value='<%=p.getPublisherAddress()%>' class="form-control"
					placeholder="Enter publisher's address to Edit" required autofocus /><br />
					
					<input type="text" style="width: 400px;" name="publisherPhone"
					value='<%=p.getPublisherPhone()%>' class="form-control"
					placeholder="Enter publisher's phone to Edit" required autofocus /><br />
					
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



