<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        $(document).ready(function() {

        });

	</script>
</head>
<body>

	<form:form id="searchForm"     class="breadcrumb form-search">

		<ul class="ul-form">
			<li><label>商品货号：</label>
				<input type="text" name="articlenos" id="articlenos"  value="${stock.articleno}" placeholder="请填写货号">

			</li>

			<li><label>品牌：</label>
				<input type="text" name="brandnames" id="brandnames"  value="${stock.brandname}" placeholder="请填写品牌">

			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	 <div style="padding: 1px;" id="data">
		 <table id="contentTable" class="table table-striped table-bordered table-condensed">
			 <thead>
			 <tr><th>品牌</th>
				 <th>尺码/性别</th>
				 <th>库存</th>
				 <th>市场</th>
				 <th>折扣</th>

			 </tr>
			 </thead>

		 </table>
	</div>
	<script type="text/javascript">
        $(function () {
            $("#searchForm").submit(function(){
                if ($('#articlenos').val() == ''){
                    showToast('请填写货号', 'info');
                    return false;
                }
                else{

                    $.post("${ctx}/stock/stock/data", {articleno:$('#articlenos').val(),brandname:$('#brandnames').val()}, function(data){

                        $('#data').html(data);
                    });
                }
                return false;
            });
        });
	</script>
</body>
</html>