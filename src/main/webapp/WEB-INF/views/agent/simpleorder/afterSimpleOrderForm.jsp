<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单售后管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        function saveOrUpdate(){
            console.log("saveOrUpdate");
           // loading('正在提交，请稍等...');
            var url="${ctx}/msimpleorder/aftersave";
            $.ajax({
                type: 'POST',
                url : url,
                data: $('#inputForm').serialize(),             //获取表单数据
                success : function(data) {
                   //debugger;
                    //closeLoading();
                   // console.log(data);
                    //console.log(data=='ok');

                    if (data=='ok') {
                        top.$.jBox.alert("保存成功");
                        window.parent.page();                                     //调用父窗体方法，当关闭子窗体刷新父窗体
                        window.parent.window.jBox.close();            //关闭子窗体
                    } else {
                        top.$.jBox.alert("保存失败");
                        window.parent.page();
                        window.parent.window.jBox.close();
                    }
                }
            });
		}
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="aSimpleOrderAfter"     class="form-horizontal">
		<form:hidden path="id"/>

		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">订单号：</label>
			<div class="controls">
				<form:input path="orderid" readonly="true" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货号：</label>
			<div class="controls">
				<form:input path="articleno"  readonly="true" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址：</label>
			<div class="controls">
				<form:input path=""  readonly="true" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收件人：</label>
			<div class="controls">
				<form:input path="consignee"  readonly="true" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机：</label>
			<div class="controls">
				<form:input path="phone"  readonly="true" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">售后方式：</label>
			<div class="controls">
				<form:radiobuttons path="state" items="${fns:getDictList('yes_no')}" itemLabel="label"
								   itemValue="value"
								   htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="50" class="input-large "/>
			</div>
		</div>

	</form:form>
</body>
</html>