
<%@ page import="java.util.List" %>
<%@ page import="domain.User" %><%--
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
        <h2 class="sub-header">User List</h2>
    </div>
    <div class="col-lg-6">
        <a href="<%=request.getContextPath()%>/user?action=add" class="btn btn-success pull-right">Add New User</a>
    </div>
    <div class="table-responsive">
        <table class="table table-responsive table-bordered table-striped tab1 ">
            <thead>
            <tr>
                <th>#</th>
                <th>User Name</th>
                <th>Password</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<User> userList = (List<User>) request.getAttribute("userlist");
                if (userList != null) {
                    int i = 1;
                    for (User user : userList) {
            %>
            <tr>

                <td><%=i++%>
                    <span><input type="hidden" value="<%= user.getId()%>"/></span>
                </td>
                <td><%=user.getUsername()%>
                </td>
                <td><%=user.getPassword()%>
                </td>
                <td><%=user.getEmail()%>
                </td>
                <td>
                    <a href="/user?action=update&id=<%=user.getId()%>" class="btn btn-primary">Update</a>
                    <a href="/user?action=delete&id=<%=user.getId()%>" class="btn btn-danger"
                       onclick="return confirm('Are you sure to delete : <%=user.getUsername()%>');">Delete</a>
                </td>

            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>

</div>
<%--page content end--%>
<%--include footer--%>
<jsp:include page="/footer.jsp"/>

