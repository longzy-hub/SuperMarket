<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script id="roleListTemplate" type="x-tmpl-mustache">
{{#roleList}}
 <tr role="row" class="role-name odd" data-id="{{id}}"><!--even -->
   	<td>{{name}}</td>
	<td>{{describes}}</td>
	<td>{{creator}}</td>
	<td>{{#create_time}}{{/create_time}}</td>
	<td>{{#update_time}}{{/update_time}}</td>
	<td>{{#bold}}{{showStatus}}{{/bold}}</td>
	<td>
		<div class="hidden-sm hidden-xs action-buttons">
			 <a class="blue role-edit" href="#" data-id="{{id}}">
				  <i class="ace-icon fa fa-pencil bigger-100"></i>
			</a>
			 <a class="red role-del" href="#" data-id="{{id}}" data-name="{{name}}">
				  <i class="ace-icon fa fa-trash bigger-100"></i>
			</a>
		</div>
	</td>
</tr>
{{/roleList}}
</script>