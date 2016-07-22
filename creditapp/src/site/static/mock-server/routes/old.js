var express = require('express');
var router = express.Router();
var user = require('./old/user');
var common = require('./old/common');
/**
 * 公共
 */
router.use('', common);
/**
 * 用户
 */
router.use('/user', user);

module.exports = router;
