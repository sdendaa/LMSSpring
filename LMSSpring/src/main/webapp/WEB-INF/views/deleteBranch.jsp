
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.LibBranch"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<%
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");

	int branchId = Integer.parseInt(request.getParameter("branchId"));
	LibBranch a = service.getBranchById(branchId);
%>
		<div class="modal-content">
			<form action="deleteBranch" method="post">
				<input type="text" style="width: 600px;" name="branchName"
					value=' You are about to delete <%=a.getBranchName()%> branch. Do you want to submit?'  /><br />
				<input type="hidden" name="branchId" value=<%=a.getBranchId()%>>
				<input type="submit" class="btn btn-primary" />
				<a href="http://localhost:8080/lms/showAllBranchs" >Cancel</a>
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
