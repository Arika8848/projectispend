
<%@ page import="java.util.List" %>
<%@ page import="domain.Expense" %><%--
  Created by IntelliJ IDEA.
  User: stha
  Date: 4/2/18
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--include header--%>
<jsp:include page="/header.jsp"/>

<%--page content--%>
<div class="container">
    <div class="col-lg-6">
        <h2 class="sub-header">Expense List</h2>
    </div>
    <div class="col-lg-6">
        <a href="<%=request.getContextPath()%>/expense?action=add" class="btn btn-success pull-right">Add New Expense</a>
    </div>
    <div class="table-responsive">
        <table class="table table-responsive table-bordered table-striped tab1 ">
            <thead>
            <tr>
                <th>S.No</th>
                <th>Name</th>

                <th>Price</th>
                <th>Expense Date</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Expense> expenseList = (List<Expense>) request.getAttribute("expenseList");
                int total=0;
                if (expenseList != null) {
                    int i = 1;
                    for (Expense expense : expenseList) {

            %>
            <tr>

                <td><%=i++%>
                    <span><input type="hidden" value="<%= expense.getId()%>"/></span>
                </td>
                <td><%=expense.getName()%>
                </td>
                <td><%=expense.getPrice()%>
                </td>
                <td><%=expense.getExpense_date()%>
                </td>
                <td>
                    <a href="/expense?action=update&id=<%=expense.getId()%>" class="btn btn-primary">Update</a>
                    <a href="/expense?action=delete&id=<%=expense.getId()%>" class="btn btn-danger"
                       onclick="return confirm('Are you sure to delete : <%=expense.getName()%>');">Delete</a>
                </td>

            </tr>
            <%
                        total += Integer.parseInt(expense.getPrice());
                    }
                }
            %><tr>
                <td>
                    Total
                </td>
                <td></td>
                <td rowspan="3">
                    <% out.print(total);%>
                </td>
            </tr>
            </tbody>
        </table>

    </div>

</div>
<%--page content end--%>
<%--include footer--%>
<jsp:include page="/footer.jsp"/>

