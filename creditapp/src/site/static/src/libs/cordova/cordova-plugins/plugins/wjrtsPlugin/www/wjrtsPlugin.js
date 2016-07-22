cordova.define("wjrtsPlugin.wjrtsPlugin", function(require, exports, module) {
var exec = require('cordova/exec');

exports.coolMethod = function(arg0, success, error) {
    exec(success, error, "wjrtsPlugin", "coolMethod", [arg0]);
};

});
