var express = require('express');
var apiCommon = express.Router();
// http://mockjs.com/
var Mock = require('mockjs');

/* GET users listing. */
apiCommon.post('/ocr/idcard', function(request, response, next) {
    var data = {
        name: '接口取',
        idCardNumber: '110101199001010011',
        validDate: '2020-01-01'
    }
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    var responseData = { errorCode: 2000, errorMessage: 'OK' };
    responseData.result = data;
    if (request.body.v == "1") {
        console.log(JSON.stringify(data));
        response.end(JSON.stringify(data));
    } else {
        response.end(JSON.stringify(responseData));
    }
});

apiCommon.get('/issuebank', function(request, response, next) {
    var card = request.query.bankcardNo;
    var data = {
        issueBank: "招商银行"
    };
    var responseData = {};
    if (card == "1111111111111111111") {
        response.writeHead(500, { 'Content-Type': 'application/json;charset=utf-8' });
        responseData.errorCode = 10014;
        responseData.errorMessage = "银行卡不在支持列表";
    } else {
        response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
        responseData = data;
    }


    response.end(JSON.stringify(responseData));
});

apiCommon.post('/verifycode', function(request, response, next) {
    if (request.query.mobile != "13444444444") {
        console.log('向mobile为' + request.query.mobile + '的手机号发送验证码');
        var data = {
            ttl: 30,
            verifycode: '123456'
        }
        response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
        response.end(JSON.stringify(data));
    } else {
        response.writeHead(501, { 'Content-Type': 'application/json;charset=utf-8' });
        var data = { errorCode: 4001, errorMessage: '无效号码' };
        response.end(JSON.stringify(data));
    }
});





module.exports = apiCommon;
