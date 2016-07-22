cordova.define("wd-plugin-openoutside.wdOpenOutside", function(require, exports, module) {
    var exec = require('cordova/exec');

    // exports.updateBar = function(arg0, arg1, arg2, arg3, arg4, success, error, [arg0, arg1, arg2, arg3, arg4]) {
    exports.getBankCardNumber = function(success, error, options) {
        exec(success, error, "WDPOpenOutside", "openOutSide", options);
    };

});
