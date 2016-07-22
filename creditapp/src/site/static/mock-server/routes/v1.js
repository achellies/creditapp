var express = require('express');
var router = express.Router();
var user = require('./v1/user');
var product = require('./v1/product');
var common = require('./v1/common');
var merchant = require('./v1/merchant');
/**
 * 产品
 */
router.use('/product', product);

/**
 * 商户
 */
router.use('/merchant', merchant);

/**
 * 公共
 */
router.use('', common);
/**
 * 用户
 */
router.use('/user', user);

module.exports = router;
