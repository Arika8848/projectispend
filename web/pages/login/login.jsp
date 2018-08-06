<%--
  Created by IntelliJ IDEA.
  User: raman
  Date: 4/24/18
  Time: 12:59 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--include header--%>
<jsp:include page="/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            ${message}
            <form method="post" action="/login"/>
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">
                        <label>Username</label>

                    </div>
                    <input type="text" name="username" id="username" placeholder="Username" class="form-control" required="" autofocus="" />
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">
                        <label>Password</label>
                    </div>

                    <input type="password" name="password" id="password" placeholder="password" class="form-control" required="" autofocus="" />
                </div>
            </div>
                <a href="/user?action=add">Register</a>
            <%--<div class="form-group">--%>
            <%--<div class="input-group">--%>
            <%--<div class="input-group-addon">--%>
            <%--<label>Email</label>--%>

            <%--</div>--%>

            <%--<input type="email" name="email" id="email" placeholder="example@gmail.com"  class="form-control" required="" autofocus="" />--%>

            <%--</div>--%>
            <%--</div>--%>
                <input type="submit" name="Login" class=" btn btn-primary btn-lg text-center" value="Login      ">

            </form>

        </div>
        <div class="col-lg-4"></div>
    </div>
</div>

<jsp:include page="/footer.jsp"/>
