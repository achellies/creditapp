cordova.define("wd-plugin-package.total", function(require, exports, module) {
    var exec = require('cordova/exec');

    exports.coolMethod = function(arg0, success, error) {
        exec(success, error, "WDPLog", "printLogWithString", [arg0]);
    };

    exports.getIdCard = function(success, error, options) {
        exec(success, error, "WDPIdCard", "getIdCard", options);
    };

    exports.getBankCardNumber = function(success, error, options) {
        exec(success, error, "WDPOpenOutside", "openOutSide", options);
    };

    exports.updateBar = function(success, error, options) {
        exec(success, error, "WDPNavigationBar", "updateBar", options);
    };

    exports.PQrCode = function(success, error, options) {
        exec(success, error, "WDPQrCode", "scanQrCode", options);
    };

    exports.LiveCheck = function(success, error, options) {
        exec(success, error, "WDPLive", "checkLive", options);
    };

    exports.SelectCity = function(success, error, options) {
        exec(success, error, "WDPSelectCity", "getCity", options);
    };

    exports.SetTranscationPwd = function(success, error, options) {
        exec(success, error, "WDPTransactionPwd", "setTransactionPwd", options);
    };

    exports.getTranscationPwd = function(success, error, options) {
        exec(success, error, "WDPTransactionPwd", "getTransactionPwd", options);
    };

    exports.CloseCurrentWebView = function() {
        exec(function(data) {}, function(data) {}, "WDPPageControl", "pageControl", [{ action: "close" }]);
    };

    exports.GoToAppHome = function() {
        exec(function(data) {}, function(data) {}, "WDPUrlScheme", "openUrl", [{ "url": "wdsd://home?page=1" }]);
    };


});
