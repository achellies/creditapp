/**
 * App run for SP-userCredit
 * @param  {[type]} $location  [description]
 * @param  {[type]} $rootScope [description]
 * @return {[type]}            [description]
 */
export default ['$location', '$rootScope', function($location, $rootScope) {
    FastClick.attach(document.body);
    var isNavBarSetterLoaded = false;
    var fnNavBarSetterLoader = function(navbar) {
        document.addEventListener('deviceready', onDeviceReady, false);
        var timeoutAfterLoader = setInterval(function() {
            if (isNavBarSetterLoaded) {
                fnNavBarSetterLoader(navbar);
                clearInterval(timeoutAfterLoader);
            }
        }, 100);
    }


    //navbar 设置native navbar的共用对象，默认返回到app首页，如果需要修改，在router中配置navbar对象的相应属性即可
    function Navbar() {
        this.leftTitle = "<";
        this.leftAction = "url";
        this.leftUrl = "wdsd://home?page=1";
        this.rightTitle = "";
        this.rightAction = "null";
        this.rightUrl = "";
    }

    function onDeviceReady() {
        isNavBarSetterLoaded = true;
        fnNavBarSetterLoader = function(navbar) {
            window.wdCordova.updateBar(navbar.navbarSuccess, null, [navbar]);
        }
    }


    $rootScope.$on('$routeChangeStart', function(event, current, previous) {
        // console.log("Route Changed");
        //发生在路由切换, 目前用于$location.path跳转时根据用户响应来决定是否发生路由
        //需要在响应的$scope中定义onRouteChange 处理event.preventDefault;
        var ignoreDialog = false; //不进行对话框处理
        if (previous && previous.scope && previous.scope.onRouteChange) {
            if (angular.isFunction(previous.scope.onRouteChange)) {
                ignoreDialog = previous.scope.onRouteChange(event, current, previous);
            }
        }
        //有对话框在则关闭对话框.并且触发hide事件
        if (ignoreDialog) {
            //
        } else {
            var dialog = $('.ui-dialog');
            if (dialog.length > 0) {
                dialog.trigger($.Event("dialog:hide"));
                dialog.remove();
                event.preventDefault();
            }
        }
        if (current.hasOwnProperty('$$route')) {
            document.title = current.$$route.title;
            var navbar = current.$$route.navbar;
            var navbarcommon = new Navbar();
            $.extend(navbarcommon, navbar);
            fnNavBarSetterLoader(navbarcommon);
        }
    });

}];
