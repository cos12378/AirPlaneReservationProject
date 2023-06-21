<%--
  Created by IntelliJ IDEA.
  User: khdra
  Date: 2023-06-20
  Time: 오후 5:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>메인페이지</h1>
    <%
        boolean isLogin = false;
        String loginID = "";
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("LoginID")) {
                isLogin = true;
                loginID = cookies[i].getValue();
            }
        }

        if (isLogin) {%>
            <a href="/reservation">예약</a>
            <br>
    <%
        }
        else if (isLogin == true && loginID.isEmpty() == false
                && loginID.equals("admin")) { %>
            <a href="/edit">개발자 페이지</a>
    <% } else if (isLogin == false) { %>
            <a href="/login">로그인</a>
            <br>
            <a href="/signup">회원가입</a>
    <%
        }
    %>
</body>
</html>
