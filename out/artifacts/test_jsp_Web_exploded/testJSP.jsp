<%--
  Created by IntelliJ IDEA.
  User: longxingjian
  Date: 2020/7/31
  Time: 3:37 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>c:forTokens 标签实例</title>
</head>
<body>
<c:forTokens items="google,runoob,taobao" delims="," var="name">
<c:out value="${name}"/><p>
    </c:forTokens>
</body>

<h4>c：set</h4>

<c:set var="key" value="pagehehe" scope="page"></c:set>

<c:set var="key" value="requesthehe" scope="request" ></c:set>

<c:set var="key" value="sessionhehe" scope="session"></c:set>

<c:set var="key" value="applicationhehe" scope="application"></c:set>


pageScope: ${pageScope.key }<br/>

requestScope:${requestScope.key }<br/>

sessionScope: ${sessionScope.key }<br/>

applicationScope: ${applicationScope.key }<br/>
</html>
