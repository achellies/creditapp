cordova.define("wd-plugin-transpw.wdTransPw", function(require, exports, module) {
    var exec = require('cordova/exec');

    // exports.updateBar = function(arg0, arg1, arg2, arg3, arg4, success, error, [arg0, arg1, arg2, arg3, arg4]) {
    exports.getTransPw = function(success, error, options) {
        exec(success, error, "WDPTransactionPwd", "getTransactionPwd", options);
    };
    exports.setTransPw = function(success, error, options) {
        exec(success, error, "WDPTransactionPwd", "setTransactionPwd", options);
    };
});
