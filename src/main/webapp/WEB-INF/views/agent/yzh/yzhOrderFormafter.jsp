<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>云中鹤订单管理管理</title>
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

		function yzhp() {
			var productid=$("#productid").val();
            var amount=$("#amount").val();
            $("#pidNums").val(productid+"_"+amount);
        }

        function getcounty(){
            var city=$("#city").val();
            $.ajax({
                type : 'POST',
                dataType : "json",
                url : "${ctx}/yzh/yzhOrder/county",
                cache : false,
                data : {
                    code:city
                },
                success : function(json) {
                    $("#county").empty();
                    var html = '<option value="" > 选择</option>';
                    $.each(json, function (i) {
                        if (json[i]) {
                            html += '<option value="' + json[i].code + '"';
                            html += '">' + json[i].name + '</option>';
                        }
                    });
                    $("#county").html(html);
                }
            });
        }
        function getcity(){
            var province=$("#province").val();
            $.ajax({
                type : 'POST',
                dataType : "json",
                url : "${ctx}/yzh/yzhOrder/city",
                cache : false,
                data : {
                    code:province
				},
                success : function(json) {
                    $("#city").empty();
                    var html = '<option value="" > 选择</option>';
                    $.each(json, function (i) {
                        if (json[i]) {
                            html += '<option value="' + json[i].code + '"';
                            html += '">' + json[i].name + '</option>';
                        }
                    });
                    $("#city").html(html);
                }
            });
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/yzh/yzhOrder/">云中鹤订单管理列表</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="yzhOrder" action="${ctx}/yzh/yzhOrder/saveafter" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">订单号：</label>
			<div class="controls">
				<form:input path="thirdorder" htmlEscape="false" readonly="true" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">云中鹤产品id：</label>
			<div class="controls">
				<form:input path="productid" htmlEscape="false" maxlength="11" class="input-xlarge " onblur="yzhp()"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">收货人姓名：</label>
			<div class="controls">
				<form:input path="receivername" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人手机：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>



		<div class="control-group">
			<label class="control-label">收货人详细地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>

	<div class="control-group">
	</br>订单信息
		</br></br>

	</div>

		<div class="control-group">
			<label class="control-label">产品价格：</label>
			<div class="controls">
				<form:input path="orderProductPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">系统订单号：</label>
			<div class="controls">
				<form:input path="orderKey" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快递费用：</label>
			<div class="controls">
				<form:input path="orderShipmentFee" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单总金额：</label>
			<div class="controls">
				<form:input path="orderTotalPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单备注信息：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group"> 售后信息
			</br>
			</br></br>

		</div>
		<div class="control-group">
			<label class="control-label">售后退换货原因：</label>
			<div class="controls">
				<form:input path="reason" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">售后用户备注：</label>
			<div class="controls">
				<form:input path="userremark" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">售后类型</label>
				<div class="controls">
					<form:select path="type" class="input-xlarge required">
						<form:option value="" label="选择"/>
						<form:option value="niffer" label="换货"/>
						<form:option value="returned" label="退货"/>
					</form:select>
				</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="yzh:yzhOrder:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>