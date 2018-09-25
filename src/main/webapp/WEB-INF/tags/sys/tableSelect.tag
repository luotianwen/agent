<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="数据地址"%>
<%@ attribute name="checked" type="java.lang.String" required="false" description="0查看 1单选框 2多选"%>
<%@ attribute name="allowInput" type="java.lang.Boolean" required="false" description="文本框可填写"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="smallBtn" type="java.lang.Boolean" required="false" description="缩小按钮显示"%>
<%@ attribute name="hideBtn" type="java.lang.Boolean" required="false" description="是否显示按钮"%>
<%@ attribute name="disabled" type="java.lang.String" required="false" description="是否限制选择，如果限制，设置为disabled"%>
<%@ attribute name="dataMsgRequired" type="java.lang.String" required="false" description=""%>
<div class="input-append">
    <input id="${id}Id" name="${name}" class="${cssClass}" type="hidden" value="${value}"/>
    <input id="${id}Name" name="${labelName}" ${allowInput?'':'readonly="readonly"'} type="text" value="${labelValue}" data-msg-required="${dataMsgRequired}"
           class="${cssClass}" style="${cssStyle}"/><a id="${id}Button" href="javascript:" class="btn ${disabled} ${hideBtn ? 'hide' : ''}" style="${smallBtn?'padding:4px 2px;':''}">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
</div>
<script type="text/javascript">
    $("#${id}Button, #${id}Name").click(function(){
        // 是否限制选择，如果限制，设置为disabled
        if ($("#${id}Button").hasClass("disabled")){
            return true;
        }
        // 正常打开
        top.$.jBox.open("iframe:${url}&1=1&checked=${checked}", "选择${title}", 810,$(top.document).height()-240, {
             buttons:{"确定":"ok", "清除":"clear", "关闭":true}, submit:function(v, h, f){
                if (v=="ok"){
                   var data= h.find("iframe")[0].contentWindow.getIds();
                   <c:if test="${checked=='1'}">
                     if(data.length>1||data.length==0){
                         top.$.jBox.tip("请选择一条数据", 'error');
                         return false;
                     }
                    $("#${id}Id").val(data[0].id);
                    $("#${id}Name").val(data[0].name);
                    </c:if>
                    <c:if test="${checked=='2'}">
                    var _ids=[],_names=[];
                    data.forEach(function (d) {
                        _ids.push(d.id);
                        _names.push(d.name);
                        $("#${id}Id").val(_ids.join(","));
                        $("#${id}Name").val(_names.join(","));
                    });
                    </c:if>
                }
                else if (v=="clear"){
                    $("#${id}Id").val("");
                    $("#${id}Name").val("");
                }
                if(typeof ${id}TableselectCallBack == 'function'){
                    ${id}TableselectCallBack(v, h, f,nodes);
                }
            }, loaded:function(h){
                $(".jbox-content", top.document).css("overflow-y","hidden");
            }
        });
    });
</script>