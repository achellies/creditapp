var express = require('express');
var router = express.Router();
// http://mockjs.com/
var Mock = require('mockjs');

/**
 * 商户信息
 */
router.get('/', function (req, res, next) {
  var code = req.query.code;
  console.log(code);

  res.json({
    "errorCode": 2000,
    "errorMessage": "",
    "result": {
      "merchantId": "22222",
      "name": "家装分期",
      "longitude": 121.232423,
      "latitude": 24.34243,
      "address": "",
      "productId": "123"
    }
  });
});

module.exports = router;
