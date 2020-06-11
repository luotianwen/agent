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
					loading('正在提交，请稍等...');
					form.submit();
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
		<li><a href="${ctx}/simpleorder/simpleOrder/">下单管理列表</a></li>
		<li class="active"><a href="${ctx}/simpleorder/simpleOrder/form?id=${simpleOrder.id}">下单管理<shiro:hasPermission name="simpleorder:simpleOrder:edit">${not empty simpleOrder.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="simpleorder:simpleOrder:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="simpleOrder" action="${ctx}/simpleorder/simpleOrder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="agentid"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">代理：</label>
			<div class="controls">
				<form:input path="agentName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">订单号：</label>
			<div class="controls">
				<form:input path="orderId" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货号：</label>
			<div class="controls">
				<form:input path="articleno" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">市场价：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="state" class="input-xlarge required">
					<form:option value="" label="默认"/>
					<form:options items="${fns:getDictList('a_simple_order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类别：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge required">
					<form:option value="" label="默认"/>
					<form:options items="${fns:getDictList('a_simple_order_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	<div class="control-group">
		<label class="control-label">分类：</label>
		<div class="controls">
		<select id="classs" name="classs" class="input-xlarge required">
			<option value="">-请选择-</option>
			<option value="跑步鞋">跑步鞋</option>
			<option value="篮球鞋">篮球鞋</option>
			<option value="足球鞋">足球鞋</option>
			<option value="网球鞋">网球鞋</option>
			<option value="户外鞋">户外鞋</option>
			<option value="拖鞋">拖鞋</option>
			<option value="凉鞋">凉鞋</option>
			<option value="羽毛球鞋 ">羽毛球鞋 </option>
			<option value="多用途训练鞋">多用途训练鞋</option>
			<option value="运动包">运动包</option>
			<option value="户外包">户外包</option>
			<option value="T恤">T恤</option>
			<option value="PO">POLO </option>
			<option value="背心">背心</option>
			<option value="衬衫">衬衫</option>
			<option value="短裤">短裤</option>
			<option value="中长裤">中长裤</option>
			<option value="长裤">长裤</option>
			<option value="短裙">短裙</option>
			<option value="内衣">内衣</option>
			<option value="羽绒服">羽绒服</option>
			<option value="卫衣">卫衣</option>
			<option value="外套">外套</option>
			<option value="篮球">篮球</option>
			<option value="足球">足球</option>
			<option value="羽毛">羽毛球</option>
			<option value="护具">护具</option>
			<option value="帽子">帽子</option>
			<option value="袜子">袜子</option>
			<option value="网球">网球</option>
			<option value="器材">器材</option>
			<option value="厨房用具">厨房用具</option>
			<option value="家纺">家纺</option>
			<option value="家居用品">家居用品</option>
			<option value="商旅用品">商旅用品</option>
			<option value="生活家电">生活家电</option>
			<option value="健康膳食">健康膳食</option>

		</select>
		</div>
	</div>
		<div class="control-group">
			<label class="control-label">售后状态：</label>
			<div class="controls">
				<form:select path="afterstate" class="input-xlarge">
					<form:option value="" label="默认"/>
					<form:options items="${fns:getDictList('a_simple_order_afterstate')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收件人：</label>
			<div class="controls">
				<form:input path="consignee" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">颜色：</label>
			<div class="controls">
				<form:input path="colour" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格尺码：</label>
			<div class="controls">
				<form:input path="spec" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址：</label>
			<div class="controls">
				<form:textarea path="address" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额：</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易号：</label>
			<div class="controls">
				<form:input path="tradeId" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快递公司：</label>
			<div class="controls">
				<form:input path="courier" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快递单号：</label>
			<div class="controls">
				<form:input path="delivernumber" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快递费：</label>
			<div class="controls">
				<form:input path="delivermoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">总金额：</label>
			<div class="controls">
				<form:input path="totalmoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开票金额：</label>
			<div class="controls">
				<form:input path="kpje" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否对账：</label>
			<div class="controls">
				<form:select path="isaccount" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">仓库信息：</label>
			<div class="controls">
				<form:textarea path="three" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="simpleorder:simpleOrder:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>