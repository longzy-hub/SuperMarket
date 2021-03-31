<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/24 0024
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="categoryListTemplate" type="x-tmpl-mustache">
{{#categoryList}}
 <tr role="row" class="category-name odd" data-id="{{id}}"><!--even -->
    <td class="center">
        <label>
            <input type="checkbox" class="ace  batchDel-check">
            <span class="lbl"></span>
         </label>
    </td>
   	<td>{{name}}</td>
	<td>{{describes}}</td>
	<td>{{creator}}</td>
	<td>{{#create_time}}{{/create_time}}</td>
	<td>{{#update_time}}{{/update_time}}</td>
	<td>{{#bold}}{{showStatus}}{{/bold}}</td>
	<td>
		<div class="hidden-sm hidden-xs action-buttons">
			 <a class="blue category-edit" href="#" data-id="{{id}}">
				  <i class="ace-icon fa fa-pencil bigger-100"></i>
			</a>
			 <a class="red category-del" href="#" data-id="{{id}}" data-name="{{name}}">
				  <i class="ace-icon fa fa-trash bigger-100"></i>
			</a>
		</div>
	</td>
</tr>
{{/categoryList}}
</script>