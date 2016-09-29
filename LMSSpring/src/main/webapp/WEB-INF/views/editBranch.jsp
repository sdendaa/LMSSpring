
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.LibBranch"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<%
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");
	int branchId = Integer.parseInt(request.getParameter("branchId"));
	LibBranch p = service.getBranchById(branchId);
%>
		<div class="modal-content">
			<form action="editBranch" method="post">
			
				<input type="text" style="width: 400px;" name="branchName"
					value='<%=p.getBranchName()%>' class="form-control"
					placeholder="Enter branch's name to Edit" required autofocus /><br />
					
					<input type="text" style="width: 400px;" name="branchAddress"
					value='<%=p.getBranchAddress()%>' class="form-control"
					placeholder="Enter branch's address to Edit" required autofocus /><br />
					
				<input type="hidden" name="branchId" value=<%=p.getBranchId()%>>
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