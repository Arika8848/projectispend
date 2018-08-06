<%--<%@ page import="domain.Book" %>--%>
<%@ page import="domain.Expense" %><%--
  Created by IntelliJ IDEA.
  User: stha
  Date: 4/9/18
  Time: 1:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--jsp scriplet section to get data from controller, in any--%>

<%--add and update will be done via same page--%>
<%
    Expense expenseInfo = null;
    if (request.getAttribute("expenseInfo") != null) {
        expenseInfo = (Expense) request.getAttribute("expenseInfo");
    }
%>
<%--include header--%>
<jsp:include page="/header.jsp"/>

<%--page content--%>

<%--just have <div> </div> with class container section , other <html> <body> tags will be included via header.jsp and footer.jsp--%>

<div class="container">
    <div class="col-lg-offset-12">
        <span class="pull-right"><a href="/expense" class="btn btn-primary">Expense List</a> </span>
    </div>
    <div class="col-md-3">
        <a href="#">
            <img alt="Logo" src="${pageContext.request.contextPath}/includes/images/logo.png" class="img-responsive">
        </a>
    </div>
    <div class="col-md-6">
        <form class="form-horizontal" role="form"
              action="${pageContext.request.contextPath}/expense?action=<%=expenseInfo!=null?"updateExpense":"addNew"%>"
              method="post">
            <fieldset>
                <legend><%=expenseInfo != null ? "Update" : "Add"%> Expense</legend>
                <%if (expenseInfo != null) { %>
                <%--if only we have data in bookInfo set hidden input--%>
                <input type="hidden" name="id" value="<%=expenseInfo.getId()%>">
                <input type="hidden" name="expense_date" value="<%=expenseInfo.getExpense_date()%>">
                <% }%>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon"></div>
                        <input type="text" name="name" class="form-control" placeholder="Name"
                               value="<%=expenseInfo!=null?expenseInfo.getName():""%>" required autofocus>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon"></div>
                        <input type="number" step="0.01" name="price" class="form-control" placeholder="Price"
                               value="<%=expenseInfo!=null?expenseInfo.getPrice():""%>" required>

                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-6">
                        <button class="btn btn-primary form-control"
                                type="submit"><%=expenseInfo != null ? "Update" : "Add"%>
                        </button>
                    </div>
                    <div class="col-lg-6">
                        <%=expenseInfo != null ? "<a href=\"/expense\" class=\"btn btn-default form-control\">Cancel</a>" : "<button class=\"btn btn-default form-control\" type=\"reset\">Reset</button>"%>
                        <%--<button class="btn btn-default form-control" type="reset">Reset</button>--%>
                        <%--<a href="users" class="btn btn-default form-control">Cancel</a>--%>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>

</div>
<!-- /.container -->
<%--page content end--%>
<%--include footer--%>
<jsp:include page="/footer.jsp"/>
