<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/22 0022
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="roleListTemplate2" type="x-tmpl-mustache">
    <ul class="dd-list">
        {{#roleList}}
            <li class="dd-item dd2-item role-name" id="role_{{id}}" href="javascript:void(0)" data-id="{{id}}">
                <div class="dd2-content" style="cursor:pointer;">{{name}}</div>
            </li>
        {{/roleList}}
    </ul>
</script>