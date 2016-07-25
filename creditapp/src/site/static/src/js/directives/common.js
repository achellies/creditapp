/**
 * [wdAgreement 协议指令]
 * @param  {[type]} $compile [depends]
 */
function wdAgreement($compile) {
    return {
        restrict: 'E',
        replace: true,
        scope: {
            // @ Attribute string binding
            // = Two-way model binding
            // & Callback method binding
            // key(scope的属性):value(element上的attr的camelCase 如 agree-model)
            agreeModel: '=agreeModel',
            agreeClick: '&',
            agreements: '='
        },
        link: function(scope, ele, attrs) {
            var el = angular.element('<div class="agreement" ng-class="{\'block\':agreeblock}">'),
                label = angular.element('<label for="agr_checkbox_id1">{{agreePreword}}</label>'),
                input = angular.element('<input id="agr_checkbox_id1" type="checkbox" name="checkbox" ng-model="agreeModel">'),
                link = angular.element('<a ng-click=agreeClick({e:$event}) href="{{agreeUrl}}"></a>');

            scope.agreeblock = true;
            scope.agreePreword = attrs['agreePreword'];
            var col = parseInt(attrs['agreeCol']);
            var agreements = scope.agreements;
            var count = 0;
            var content = ""
            var oneLine = {};
            for (var i in agreements) {
                if (col != 0) {
                    if (count % col == 0) {
                        oneLine = angular.element('<div>');
                        link.append(oneLine);
                        count = 0;
                    }
                } else {
                    oneLine = angular.element('<span>');
                    link.append(oneLine);
                    count = 0;
                }
                var curAgreement = agreements[i];
                oneLine.append('《' + curAgreement.desc + '》');
                count++;
            }
            el.append(input);
            el.append(label);
            el.append(link);
            $compile(el)(scope);
            ele.append(el);
        }
    }
};

/**
 * [wdCountDown 倒计时]
 * @param  {[type]} $interval [description]
 * @return {[type]}           [description]
 */
function wdCountDown($interval) {
    // 计算倒计时天
    function rd(t) {
        return Math.floor(t / (24 * 3600 * 1000));
    }

    // 计算倒计时小时
    function rh(t) {
        return Math.floor(t / 1000 / 60 / 60 % 24);
    }

    // 计算倒计时分钟
    function rm(t) {
        return Math.floor(t / 1000 / 60 % 60);
    }

    // 倒计时秒数 
    function rs(t) {
        var m = t / 60000;
        return Math.floor(t / 1000 % 60);
    }

    return {
        restrict: "E",
        template: '<p class="count-down header-common theme-{{theme}}">' +
            '<span class="cd-dd bg-fill">{{dd}}</span>' +
            '<span>天</span>' +
            '<span class="cd-hh bg-fill">{{hh}}</span>' +
            '<span>:</span>' +
            '<span class="cd-mm bg-fill">{{mm}}</span>' +
            '<span>:</span>' +
            '<span class="cd-ss bg-fill">{{ss}}</span>' +
            '</p>',
        replace: true,
        scope: true,
        link: function($scope, $element, $attrs) {
            var remianTime = $attrs.remainTime;
            var theme = $attrs.theme; // red or white
            var endEvt = $attrs.endEvt;

            var dd = 0,
                hh = 0,
                mm = 0,
                ss = 0;
            $scope.theme = theme;

            var start = function() {
                // TODO 是否隐藏该区域
                if (remianTime <= 0) {
                    $scope.$parent.$emit(endEvt);
                    return $interval.cancel(timmer);
                }

                dd = rd(remianTime);
                hh = rh(remianTime);
                mm = rm(remianTime);
                ss = rs(remianTime);

                $scope.dd = dd;
                $scope.hh = hh < 10 ? ('0' + hh) : hh;
                $scope.mm = mm < 10 ? ('0' + mm) : mm;
                $scope.ss = ss < 10 ? ('0' + ss) : ss;

                remianTime -= 1000;
            }
            start();
            // 订时器
            var timmer = $interval(start, 1000);

        }
    }
};


/**
 * [wdTooltips 通用tooltips指令]
 * @return {[type]} [description]
 */
function wdTooltips() {
    return {
        restrict: 'A', //E element元素指令|A attr属性指令|C class类指令|M memo备注指令
        replace: false, //true时用template完全替换指令,false 为嵌入
        link: function(scope, ele, attrs) {
            $("#" + attrs['id']).tooltips({ content: attrs.wdTooltips });
        }
    };
}

/**
 * [repeatReady ng-repeat 完成后执行指令]
 * @return {[type]} [description]
 */
function repeatReady() {
    return {
        restrict: "A",
        link: function(scope, element, attrs) {
            if (scope.$last) {
                element.ready(function() {
                    scope.$eval(attrs.repeatReady);
                });
            }
        }
    };
}

/**
 * [repeatReady ng-repeat 完成后执行指令]
 * @return {[type]} [description]
 */
function fixBottom() {
    return {
        restrict: "A",
        link: function(scope, element, attrs) {
            element.ready(function() {
                var ctlToFixed = $('[fix-bottom]');
                if (ctlToFixed.length > 0) {
                    ctlToFixed = $(ctlToFixed[0]);
                    var bottomOffset = typeof bottomOffset !== 'undefined' ? bottomOffset : 0,
                        body = document.body,
                        html = document.documentElement,
                        height = Math.max(body.offsetHeight, html.clientHeight, html.offsetHeight);

                    var wnd_height = $(window).height();
                    var final_height = (height > wnd_height) ? height : wnd_height;
                    var ctl_height = ctlToFixed.height();
                    // Collapsing Margins
                    // 规定浮动元素和绝对定位元素不参与Margin折叠
                    $('body').css('height', final_height).css('width','100%').css('position', "absolute");
                    ctlToFixed.css({ "bottom": 0, "position": "absolute" });
                }
            });
        }
    };
}

/**
 * [numberLimit 
 * 步增限制的数字输入(自带格式化,上限)
 * @上限:attr number-max; 不填或者-1 :不限制;
 * @步增:attr number-step 默认最小为1(暂不实现)
 * ]
 * @param  {[type]} $filter  [description]
 * @param  {[type]} $browser [description]
 * @return {[type]}          [description]
 */
function numberLimit($filter, $browser) {
    return {
        restrict: "A",
        require: '?ngModel',
        link: function(scope, elem, attrs, ctrl) {
            if (!ctrl) return;

            var listener = function() {
                var value = elem.val().replace(/,/g, '')
                var max = attrs["numberMax"] ? parseFloat(attrs["numberMax"]) : -1;
                if (max != -1) {
                    value = value > max ? max : value;
                }
                if (value == 0) {
                    elem.val("");
                } else {
                    elem.val($filter('number')(value))
                }

            }

            ctrl.$render = function() {
                elem.val($filter('number')(ctrl.$viewValue));
            }

            elem.bind('keydown', function(event) {
                var key = event.keyCode;
                if (!((key >= 48 && key <= 57) || key == 8)) {
                    event.returnValue = false;
                    return;
                }
                $browser.defer(listener);
            })
            ctrl.$parsers.push(function(viewValue) {
                var max = attrs["numberMax"] ? parseFloat(attrs["numberMax"]) : -1;

                viewValue = parseInt(viewValue.replace(/[^\d|\-+|\.+]/g, ''));
                if (max != -1) {
                    viewValue = viewValue > max ? max : viewValue;
                }
                var plainNumber = viewValue;
                if (viewValue == 0) {
                    elem.val("");
                } else {
                    elem.val($filter("number")(plainNumber));
                }
                ctrl.$setViewValue($filter("number")(plainNumber));
                return plainNumber;

            });
        }
    };
}

/**
 * tab 显示
 * @returns {{restrict: string, replace: boolean, link: link}}
 */
function wdUiTab() {
    return {
        restrict: 'EA',
        replace: false,
        link: function(scope, ele, attrs) {
            document.addEventListener('deviceready', function(){
                var tab = new fz.Scroll(ele[0], {
                    role: 'tab',
                    autoplay: !!attrs["autoplay"],
                    interval: (!!attrs["autoplay"] && attrs["interval"]) ? attrs["interval"] : 0
                });
                var scroll = new fz.Scroll('.nav-bar', {
                    scrollY: true
                });
            });
        }
    };
}

/**
 * tab 自动滚动到目标位置
 */
function wdUiScrollTab($timeout) {
    return {
        restrict: 'E',
        replace: false,
        link: function(scope, ele, attrs) {
            $timeout(function(){
                var index = attrs['index'] - 1 >= 0 ? attrs['index'] - 1 : 0;
                var children = $(attrs['tab']).children(), len = children.length;
                index = (index > len - 1) ? len - 1 : index;
                var left = children.eq(index).offset().left - 14;
                $(attrs['tab']).scrollLeft(left);
            });
        }
    };
}

/**
 * export wd-directive module
 */
export default [{
    name: 'wdAgreement',
    dependences: ['$compile'],
    fn: wdAgreement
}, {
    name: 'wdCountDown',
    dependences: ['$interval'],
    fn: wdCountDown
}, {
    name: 'wdTooltips',
    dependences: [],
    fn: wdTooltips
}, {
    name: 'repeatReady',
    dependences: ['$compile'],
    fn: repeatReady
}, {
    name: 'numberLimit',
    dependences: ['$filter', '$browser'],
    fn: numberLimit
}, {
    name: 'fixBottom',
    dependences: [],
    fn: fixBottom
}, {
    name: 'wdUiTab',
    dependences: [],
    fn: wdUiTab
}, {
    name: 'wdUiScrollTab',
    dependences: ['$timeout'],
    fn: wdUiScrollTab
}];
