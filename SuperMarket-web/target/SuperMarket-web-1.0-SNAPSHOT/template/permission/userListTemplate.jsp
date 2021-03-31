<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/23 0023
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="userListTemplate" type="x-tmpl-mustache">
{{#userList}}
 <tr role="row" class="user-name odd" data-id="{{id}}"><!--even -->
   	<td>{{username}}</td>
	<td>{{showRoleId}}</td>
	<td>{{telephone}}</td>
	<td>{{mail}}</td>
	<td>{{#create_time}}{{/create_time}}</td>
	<td>{{#update_time}}{{/update_time}}</td>
	<td>{{#bold}}{{showStatus}}{{/bold}}</td>
	<td>
		<div class="hidden-sm hidden-xs action-buttons">
			 <a class="blue user-edit" href="#" data-id="{{id}}">
				  <i class="ace-icon fa fa-pencil bigger-100"></i>
			</a>
		</div>
	</td>
</tr>
{{/userList}}
</script>


