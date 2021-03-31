<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/31 0031
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="permListTemplate" type="x-tmpl-mustache">
{{#permList}}
 <tr role="row" class="perm-name odd" data-id="{{id}}"><!--even -->
    <td>{{showRoleName}}</td>
   	<td>{{name}}</td>
	<td>{{describes}}</td>
	<td>{{#bold}}{{showStatus}}{{/bold}}</td>
	<td>
		<div class="hidden-sm hidden-xs action-buttons">
			 <a class="blue perm-edit" href="#" data-id="{{id}}">
				  <i class="ace-icon fa fa-pencil bigger-100"></i>
			</a>
			 <a class="red perm-del" href="#" data-id="{{id}}" data-name="{{name}}">
				  <i class="ace-icon fa fa-trash bigger-100"></i>
			</a>
		</div>
	</td>
</tr>
{{/permList}}
</script>
