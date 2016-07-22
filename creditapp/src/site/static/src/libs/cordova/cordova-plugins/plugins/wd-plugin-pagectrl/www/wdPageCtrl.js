cordova.define("wd-plugin-pagectrl.wdPageCtrl", function(require, exports, module) {
    var exec = require('cordova/exec');

    // exports.updateBar = function(arg0, arg1, arg2, arg3, arg4, success, error, [arg0, arg1, arg2, arg3, arg4]) {
    exports.pageControl = function(success, error, options) {
        exec(success, error, "WDPPageControl", "pageControl", options);
    };

});
