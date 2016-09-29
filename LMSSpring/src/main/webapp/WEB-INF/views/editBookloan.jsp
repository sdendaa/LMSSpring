
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.BookLoan"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%

ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("AdminService");

	int bookId = Integer.parseInt(request.getParameter("bookId"));
	int branchId = Integer.parseInt(request.getParameter("branchId"));
	int cardNo = Integer.parseInt(request.getParameter("cardNo"));
	BookLoan b = service.getBookLoanById(branchId, bookId);
%>
		<div class="modal-content">
			<form action="editBookloan" method="post">
					CheckOut Date:
					<input type="Date" style="width: 400px;" name="dateOut"
					value='<%=b.getCheckOutDate() %>' class="form-control"/><br />
					Due Date:
					<input type="Date" style="width: 400px;" name="dueDate"
					value='<%=b.getDueDate() %>' class="form-control"/><br />
				
				<input type="hidden" name="bookId" value=<%=bookId%>>
				<input type="hidden" name="branchId" value=<%=branchId%>>
				<input type="hidden" name="cardNo" value=<%=cardNo%>>
				<input type="submit" class="btn btn-primary" />
				<a href="http://localhost:8080/lms/showAllBookLoaned" >Cancel</a>
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



