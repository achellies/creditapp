cordova.define("wd-plugin-liverec.wdLiveRec", function(require, exports, module) {
    var exec = require('cordova/exec');

    // exports.updateBar = function(arg0, arg1, arg2, arg3, arg4, success, error, [arg0, arg1, arg2, arg3, arg4]) {
    exports.checkLive = function(success, error, options) {
        exec(success, error, "WDPLive", "checkLive", options);
    };

});
