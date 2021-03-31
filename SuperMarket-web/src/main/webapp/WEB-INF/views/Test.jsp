<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/27 0027
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/imageUpload" method="post" enctype="multipart/form-data">
    <input type="file" name="fileName" id="filename">
    <input type="submit" id="submitBtn" value="上传"/>
</form>

</body>
</html>
