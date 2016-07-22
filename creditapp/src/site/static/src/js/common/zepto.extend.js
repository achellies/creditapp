    //实例扩展
    $.extend($, {
        /*loading begin*/
        showBlockLoading: function(msg) {
            var _loading = $("#loading-block");
            if (!_loading[0]) {
                if (!msg) {
                    msg = '正在加载中...';
                }
                _loading = $("<div></div>").attr("id", "loading-block").addClass("wd-block-loading ui-loading-block").html("<div class='ui-loading-cnt'><i class='ui-loading-bright'></i><p>" + msg + "</p></div>").appendTo($(document.body));
            } else {
                _loading.find('p').html(msg);
            }
            _loading.addClass('show');
        },
        hideBlockLoading: function() {
            $("#loading-block").remove();
        },
        //dialogConfig{
        // @title:'抬头'
        // @content:'提示内容'
        // @buttons:['@按钮1','@按钮2'],按钮数组 序号从0开始
        // @titleStyle:{attr1:attr,attr2:attr2} 样式重置
        // @contentStyle:{attr1:attr,attr2:attr2} 样式重置
        // }
        //actionCallback @传入参数e 按钮点击时触发
        //dismissCallback @传入参数e 对话框dismiss时触发
        //TODO 还有几个方法未支持
        showDialog: function(dialogConfig, actionCallback, dismissCallback) {
            //TODO 注意:button点击事件绑定在tap事件上.在PC浏览器中非decivemode下 不触发click
            var defaultsConfig = {
                title: '',
                content: '',
                button: ['确认', '取消'],
                select: 0,
                allowScroll: false,
                callback: function() {},
                animation: 'pop'
            };
            if (dialogConfig.titleStyle) {
                _titleReset = $("<div></div>").html(dialogConfig.title).css(dialogConfig.titleStyle);
                dialogConfig.title = _titleReset[0].outerHTML;
            }
            if (dialogConfig.contentStyle) {
                _contentReset = $("<div></div>").html(dialogConfig.content).css(dialogConfig.contentStyle);
                dialogConfig.content = _contentReset[0].outerHTML;
            }
            this.option = $.extend(defaultsConfig, dialogConfig);
            var dia = $.dialog(this.option);
            var curDialogId = "dialog_id_" + Math.floor(Math.random() * 10) + "_x";
            dia.attr({ id: curDialogId });
            var isModalClickHide = this.option.button.length == 0;
            if (isModalClickHide) {
                dia.find('.ui-dialog-ft').remove();
                dia.find('.ui-dialog-cnt').on('tap', function(e) {
                    e.stopPropagation();
                })
                dia.on('tap', function(e) {
                    $(this).remove();
                })
            }
            var bindScope = !!(defaultsConfig.scope);
            if (actionCallback) {
                dia.on("dialog:action", actionCallback);
            }
            if (dismissCallback) {
                dia.on("dialog:hide", dismissCallback);
            }
        },
        showMsg: function(title) {
            $.showDialog({ content: title, button: ['关闭'] });
        },
        showTips: function(querySelector, tipsContent) {
            var tooltipsOffset, tooltips;
            var targetCtl = $(querySelector);
            var targetOffset = targetCtl.offset();
            tooltips = $("<div class='bubble-wrap'><div class='bubble-tips'>" + tipsContent + "</div></div>");
            $(targetCtl).before(tooltips);
            //width
            tooltips.width(targetCtl.width());

            //offset
            tooltipsOffset = tooltips.offset();
            tooltipsOffset.top = tooltipsOffset.top - tooltipsOffset.height;
            tooltipsOffset.left = targetOffset.left;
            tooltips.offset(tooltipsOffset);
        }
    });
    //全局扩展
    $.extend($.fn, {
        tooltips: function(config) {

            var tooltipsOffset, tooltips;
            var targetCtl = $(this);
            var id = targetCtl.attr('id') == "" ? targetCtl.attr("class") : targetCtl.attr('id');
            id = 'tips-arrow-' + id;
            var targetOffset = targetCtl.offset();
            tooltips = $("<div class='bubble-wrap " + id + " '><div class='bubble-tips'>" + config.content + "</div></div>");
            $(targetCtl).before(tooltips);
            //width
            if (config.width) {
                tooltips.width(config.width);
            } else {
                tooltips.width(targetCtl.width());
            }
            //offset
            tooltipsOffset = tooltips.offset();
            tooltipsOffset.top = tooltipsOffset.top - tooltipsOffset.height;
            tooltipsOffset.left = targetOffset.left;
            tooltips.offset(tooltipsOffset);

            //小箭头样式加载.动态指向目标元素中间的位置
            var style = document.createElement("style");
            document.head.appendChild(style);
            var sheet = style.sheet;
            var selectorText = '.bubble-wrap.' + id + '>.bubble-tips::after';
            var rule = 'left:' + (targetCtl.width() / 2) + 'px';
            if (sheet.insertRule) {
                sheet.insertRule(selectorText + "{" + rule + "}", 0);
            } else if (sheet.addRule) {
                sheet.addRule(selectorText, rule, 0);
            }
        }
    });

    /**
     * User: zhangjunbin
     * Date: 16-05-14
     */
    //插件-Toast
    (function($) {
        // 默认模板
        var _toastTpl = '<div class="ui-toast">' +
            '<div class="ui-toast-cnt">' +
            '<i></i><%=content%>' +
            '</div>' +
            '</div>';

        // 默认参数
        var _toastDefault = {
                content: '',
                stayTime: 1000,
                callback: function() {}
            }
            // 构造函数
        var Toast = function(el, option, isFromTpl) {
            var self = this;
            this.element = $(el);
            this._isFromTpl = isFromTpl;
            this.elementHeight = $(el).height();
            this.viewportHeight = ($(window).height() - this.elementHeight) / 2;
            this.option = $.extend(_toastDefault, option);
            $(el).css({
                "-webkit-transform": "translateY(-" + this.elementHeight + "px)"
            });
            setTimeout(function() {
                $(el).css({
                    "-webkit-transition": "all .5s"
                });
                self.show();
            }, 20);

        }
        Toast.prototype = {
            show: function() {
                var self = this;
                // self.option.callback("show");
                self.element.trigger($.Event("tips:show"));
                this.element.css({
                    "-webkit-transform": "translateY(" + this.viewportHeight + "px)"
                });
                if (self.option.stayTime > 0) {
                    setTimeout(function() {
                        self.hide();
                        if (self.option.callback) {
                            self.option.callback();
                        }
                    }, self.option.stayTime)
                }
            },
            hide: function() {
                var self = this;
                self.element.trigger($.Event("tips:hide"));
                this.element.css({
                    "-webkit-transform": "translateY(-" + this.elementHeight + "px)"
                });
                setTimeout(function() {
                    self._isFromTpl && self.element.remove();
                }, 500)


            }
        }

        function Plugin(option) {
            return $.adaptObject(this, _toastDefault, option, _toastTpl, Toast, "toast");
        }

        $.fn.toast = $.toast = Plugin;
    })($);

    //插件-ModalTips
