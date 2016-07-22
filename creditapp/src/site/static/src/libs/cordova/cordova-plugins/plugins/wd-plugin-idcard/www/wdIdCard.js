cordova.define("wd-plugin-idcard.wdIdCard", function(require, exports, module) {
    var exec = require('cordova/exec');

    // exports.updateBar = function(arg0, arg1, arg2, arg3, arg4, success, error, [arg0, arg1, arg2, arg3, arg4]) {
    exports.getIdCard = function(success, error, options) {
        exec(success, error, "WDPIdCard", "getIdCard", options);
    };

});
