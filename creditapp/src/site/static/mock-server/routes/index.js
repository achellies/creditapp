var express = require('express');
var router = express.Router();
// http://mockjs.com/
var Mock = require('mockjs');
var v1 = require('./v1');
var old = require('./old');

router.use('/timesloan/api/v1/consumption', v1);
router.use('/timesloan/api/v1', old);

module.exports = router;
