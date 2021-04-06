<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/2 0002
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="personalListTemplate" type="x-tmpl-mustache">
{{#personalList}}
 <tr role="row">
   	<td>{{showUserName}}</td>
	<td>{{showStatus}}</td>
	<td>{{showTime}}</td>
 </tr>
{{/personalList}}
</script>
