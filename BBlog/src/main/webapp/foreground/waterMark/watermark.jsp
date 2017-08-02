<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

	<%-- <table width="99%" align="center">

  <tr>
  	<td width="50%" align="center">
  		<img src=${pageContext.request.contextPath }<s:property value="picInfo.imageURL"/> width="550" />
  	</td>
  	<td width="50%" align="center">
  		<img src=${pageContext.request.contextPath }<s:property value="picInfo.logoImageURL"/> width="550" />
  	</td>
  </tr>

	<s:iterator value="picInfo">
		<tr>
			<td width="50%" align="center">
				<img src=${pageContext.request.contextPath }<s:property value="imageURL"/> width="550" />
			</td>
			<td width="50%" align="center">
				<img src=${pageContext.request.contextPath }<s:property value="logoImageURL"/> width="550" />
			</td>
		</tr>
	</s:iterator>

	</table> --%>
	<img src="${fileUrl }">
