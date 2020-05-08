<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>下单管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function sumtotalmoney(){
            var num=parseFloat($("#num").val());
            var money=parseFloat($("#money").val());
            var delivermoney=parseFloat($("#delivermoney").val());
            var a=parseFloat(money*num+delivermoney).toFixed(2);
            $("#totalmoney").val(a);
		}
		$(document).ready(function() {

			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
                    var num=parseFloat($("#num").val());
                    var money=parseFloat($("#money").val());
                    var delivermoney=parseFloat($("#delivermoney").val());
                    var totalmoney=parseFloat($("#totalmoney").val()).toFixed(2);
                    var kpje=parseFloat($("#kpje").val()).toFixed(2);
                    var a=parseFloat(money*num).toFixed(2);
                    var address =$("#address").val().split(",");
                    if(address.length!=4){
                        top.$.jBox.alert("地址信息请填详细 注意逗号是英文输入法下的");
                        return false;
                    }

                    if((a-delivermoney)!=kpje){
                        top.$.jBox.alert((a)+"开票金额输入有误，请先更正。");
                        return false;
                    }
                    if(a!=totalmoney){
                        top.$.jBox.alert((a)+"总价输入有误，请先更正。");
                        return false;
					}
                       else {
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
		<li><a href="${ctx}/simpleorder/simpleOrder/">下单管理列表</a></li>
		<li class="active"> 发货</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="simpleOrder" action="${ctx}/simpleorder/simpleOrder/deliver" method="post" class="form-horizontal" >
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
			<label class="control-label">市场价：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="state" class="input-xlarge required"  >
					<form:option value="" label="默认"/>
					<form:options items="${fns:getDictList('a_simple_order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			<label class="control-label">地址：</label>
			<div class="controls">
				<form:textarea path="address" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单号：</label>
			<div class="controls">
				<form:input path="orderId" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易号：</label>
			<div class="controls">
				<form:input path="tradeId" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false"    maxlength="11" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">颜色：</label>
			<div class="controls">
				<form:input path="colour" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货号：</label>
			<div class="controls">
				<form:input path="articleno" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">规格尺码：</label>
			<div class="controls">
				<form:input path="spec" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额：</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" readonly="true"  class="input-xlarge required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">快递公司：</label>
			<div class="controls">
				<form:input path="courier" htmlEscape="false" maxlength="100" class="input-xlarge  "/>
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
				<form:input path="delivermoney" htmlEscape="false" class="input-xlarge " onblur="sumtotalmoney()"/>
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
			<label class="control-label">天马仓库：</label>
			<div class="controls">
				<form:input path="warehouse" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">天马货号：</label>
			<div class="controls">
				<form:input path="tmarticleno" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">天马规格：</label>
			<div class="controls">
				<form:input path="tmspec" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="simpleorder:simpleOrder:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>