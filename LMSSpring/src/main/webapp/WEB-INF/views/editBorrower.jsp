
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%

ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");

	int cardNo = Integer.parseInt(request.getParameter("cardNo"));
	Borrower bo = service.getBorrowerById(cardNo);
%>
		<div class="modal-content">
			<form action="editBorrower" method="post">
			
				<input type="text" style="width: 400px;" name="name"
					value='<%=bo.getName()%>' class="form-control"
					placeholder="Enter Borrower's name to Edit" required autofocus /><br />
					
					<input type="text" style="width: 400px;" name="address"
					value='<%=bo.getAddress()%>' class="form-control"
					placeholder="Enter borrower's address to Edit" required autofocus /><br />
					
					<input type="text" style="width: 400px;" name="phone"
					value='<%=bo.getPhone()%>' class="form-control"
					placeholder="Enter borrower's phone to Edit" required autofocus /><br />
					
				<input type="hidden" name="cardNo" value=<%=bo.getCardNo()%>>
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



