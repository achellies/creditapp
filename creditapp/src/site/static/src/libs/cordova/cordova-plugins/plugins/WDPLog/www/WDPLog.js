cordova.define("WDPLog.WDPLog", function(require, exports, module) {
               var exec = require('cordova/exec');
               
               exports.coolMethod = function(arg0, success, error) {
               exec(success, error, "WDPLog", "printLogWithString", [arg0]);
               };
               
               });

