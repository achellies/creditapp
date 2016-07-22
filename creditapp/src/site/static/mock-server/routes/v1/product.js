var express = require('express');
var router = express.Router();
// http://mockjs.com/
var Mock = require('mockjs');

/**
 * 产品详情
 */
router.get('/', function (req, res, next) {
  var productid = req.query.productid;
  res.json({
    "errorCode": 2000,
    "errorMessage": "",
    "result": {
      "name": "家装分期",
      "lowLimit": 10000,
      "highLimit": 100000,
      "stepLimit": 1000,
      "interests": [
        {
          "normalFeeRate": 0.006,
          "manageFeeRate": 0.06,
          "period": 6
        },
        {
          "normalFeeRate": 0.008,
          "manageFeeRate": 0.08,
          "period": 4
        }
      ]
    }
  });
});

module.exports = router;
