<%@ page contentType="text/html;charset=UTF-8" %>
<head>
    <meta charset="utf-8">
    <title>${fns:getConfig('productName')}</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
    <!-- meta使用viewport以确保页面可自由缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 引入 jQuery Mobile 样式 -->
    <link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <!-- 引入 jQuery 库 -->
    <script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
    <!-- 引入 jQuery Mobile 库 -->
    <script src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script type="text/javascript">
        var ctx = '${ctx}', ctxStatic='${ctxStatic}';
        function showToast(mgs,info){
            $("#content").html(mgs);
            $("#popupBasic").popup('open');
        }
        /**
         * jQueryMobile 弹出提示框
         * @param text：提示内容
         * @param callback:点击确定要执行的函数
         */
        function confirmJQM(text, callback) {
            var popupDialogId = 'popupDialogC';
            $('<div data-role="popup" id="' + popupDialogId + '" data-confirmed="no" data-position-to="window" data-transition="pop" data-theme="b" data-dismissible="false" style="max-width:500px;">'+
                '<div role="main" class="ui-content" style="text-align: center;">'+
                '<h3 class="ui-title">' + text + '</h3>'+
                '<p></p>'+
                '<a data-role="button" data-theme="b" class="optionCancel" data-mini="true" data-inline="true" onclick="$(\'#popupDialogC\').popup(\'close\');" >取消</a>'+
                '<a data-role="button" data-theme="b" class="optionConfirm" data-transition="flow" data-inline="true" data-mini="true">确定</a>'+
                '</div>'+
                '</div>').appendTo($.mobile.pageContainer);
            var popupDialogObj = $('#' + popupDialogId);
            popupDialogObj.trigger('create');  //动态加载时 需要重新刷新下 也就是给popup赋上jqm对应的css

            //初始化popup
            popupDialogObj.popup({
                //关闭时 绑定的事件
                afterclose: function (event, ui) {
                    popupDialogObj.find(".optionConfirm").first().off('click'); //关闭时 需要清除确定按钮上 绑定的事件
                    $(event.target).remove();//删除 创建的 popup
                },

                //显示时 绑定的事件
                afteropen: function (event, ui) {
                    popupDialogObj.find(".optionConfirm").first().on('click', function () {
                        popupDialogObj.attr('data-confirmed', 'no');
                        $('#popupDialogC').popup('close');
                        if(callback && callback instanceof Function ){
                            callback();
                        }
                    });
                }
            });
            //打开
            popupDialogObj.popup('open');
        }
    </script>
</head>


