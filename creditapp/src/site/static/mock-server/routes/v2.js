var express = require('express');
var router = express.Router();
// http://mockjs.com/
var Mock = require('mockjs');

/* GET users listing. */
router.get('/', function (req, res, next) {
  res.send('respond with a resource');
});

router.get('/test', function (req, res, next) {
  res.json([]);
})

module.exports = router;
