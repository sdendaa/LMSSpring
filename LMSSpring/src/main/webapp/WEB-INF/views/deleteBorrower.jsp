
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%

ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");
	int cardNo = Integer.parseInt(request.getParameter("cardNo"));
	Borrower b = service.getBorrowerById(cardNo);
%>
		<div class="modal-content">
			<form action="deleteBorrower" method="post">
				<input type="text" style="width: 600px;" name="publisherName"
					value=' You are about to delete <%=b.getName()%>. Do you want to submit?'  /><br />
				<input type="hidden" name="cardNo" value=<%=b.getCardNo()%>>
				<input type="submit" class="btn btn-primary" />
				<a href="http://localhost:8080/lms/showAllBorrowers" >Cancel</a>
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
