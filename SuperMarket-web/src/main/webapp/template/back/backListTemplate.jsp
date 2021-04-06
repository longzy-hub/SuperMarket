<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/24 0024
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="backListTemplate" type="x-tmpl-mustache">
{{#backList}}
 <tr role="row" class="back-name odd" data-id="{{id}}"><!--even -->
    <td class="center">
        <label>
            <input type="checkbox" class="ace  batchDel-check">
            <span class="lbl"></span>
         </label>
    </td>
	<td>{{address}}</td>
	<td>{{creator}}</td>
	<td>{{#create_time}}{{/create_time}}</td>
	<td>{{#update_time}}{{/update_time}}</td>
	<td>{{#bold}}{{showStatus}}{{/bold}}</td>
	<td>
		<div class="hidden-sm hidden-xs action-buttons">
			 <a class="blue back-edit" href="#" data-id="{{id}}">
				  <i class="ace-icon fa fa-pencil bigger-100"></i>
			</a>
			 <a class="red back-del" href="#" data-id="{{id}}" data-name="{{address}}">
				  <i class="ace-icon fa fa-trash bigger-100"></i>
			</a>
		</div>
	</td>
</tr>
{{/backList}}
</script>