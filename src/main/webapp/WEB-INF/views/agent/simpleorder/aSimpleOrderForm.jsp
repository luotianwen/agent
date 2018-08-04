<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>下单管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
				    var address =$("#address").val().split(",");
				    if(address.length!=4){
                        top.$.jBox.alert("地址信息请填详细 注意逗号是英文输入法下的");
                        return false;
					}
                    else{
						loading('正在提交，请稍等...');
						form.submit();
                    }
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/msimpleorder/">下单管理列表</a></li>
		<li class="active"><a href="${ctx}/msimpleorder/form?id=${simpleOrder.id}">下单管理 ${not empty simpleOrder.id?'修改':'添加'}   </a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="simpleOrder" action="${ctx}/msimpleorder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="tradeId"/>
		<sys:message content="${message}"/>		

		<div class="control-group">
			<label class="control-label">货号：</label>
			<div class="controls">
				<form:input path="articleno" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">颜色：</label>
			<div class="controls">
				<form:input path="colour" htmlEscape="false" maxlength="200" class="input-xlarge  "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格尺码：</label>
			<div class="controls">
				<form:input path="spec" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:select path="num" class="input-medium required">
					<c:forEach var="x" begin="1" end="50" step="1">
						<form:option value="${x}"    htmlEscape="false"/>
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收件人：</label>
			<div class="controls">
				<form:input path="consignee" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址:(北京市,北京市,通州区,北京市通州区xxxx室)</label>
			<div class="controls">
				<form:textarea path="address" htmlEscape="false"    maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<%--<div class="control-group">
			<label class="control-label">收件信息：</label>
			<div class="controls">
				<form:textarea path="deliverinfo" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">金额：</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			 <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>