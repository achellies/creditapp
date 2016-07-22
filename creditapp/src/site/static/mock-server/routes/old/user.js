var express = require('express');
var apiUser = express.Router();
// http://mockjs.com/
var Mock = require('mockjs');

/* GET users listing. */
apiUser.get('/', function(req, res, next) {
    res.send('respond with a resource');
});
/*
查询身份证信息 旧API
 */
apiUser.get('/idcard', function(request, response, next) {
    var mockError = false;
    if (!mockError) {
        var data = {
            realName: '沈涛',
            idCardNo: '510121198805116072',
            validDate: '2020-01-01',
            frontImgDataUrl: 'http://img4.imgtn.bdimg.com/it/u=918753068,2915877896&fm=21&gp=0.jpg',
            backImgDataUrl: 'http://img0.imgtn.bdimg.com/it/u=2088292662,4110209500&fm=21&gp=0.jpg'
        }
        response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
        var responseData = { errorCode: 2000, errorMessage: 'OK' };
        responseData.result = data;
        if (request.query.v == "1") {
            console.log("api user/idcard v=1 called");
            response.end(JSON.stringify(data));
        } else {
            response.end(JSON.stringify(responseData));
        }
    } else {
        response.writeHead(408, { 'Content-Type': 'application/json;charset=utf-8' });
        var responseData = { errorMessage: 'test errorMessage', errorCode: 4008 };
        response.end(JSON.stringify(responseData));
    }
});
/*
提交身份证信息 旧API
 */
apiUser.post('/idcard', function(request, response, next) {
    console.log("post    ======>user/idcard======>" + JSON.stringify(request.body));
    var data = {};
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    var responseData = { errorCode: 2000, errorMessage: 'OK' };
    responseData.result = data;
    if (request.body.v == "1") {
        responseData = data;
        response.end(JSON.stringify(responseData));
    }
    console.log("response======>user/idcard======>" + JSON.stringify(responseData));
});
/*
提交人脸识别 旧API
 */
apiUser.post('/face', function(request, response, next) {
    console.log("post    ======>user/face======>" + JSON.stringify(request.body));
    var data = {
            errorMessage: 'test error',
            retryRemains: 5,
            errorCode: 4001
        },
        image = request.body.liveBodyImage;
    if (image.length > 1000) {
        response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
        data = { status: 'success' };
        response.end(JSON.stringify(data));
    } else if (image == "2") {
        response.writeHead(501, { 'Content-Type': 'application/json;charset=utf-8' });
        response.end(JSON.stringify(data));
    } else {
        response.writeHead(501, { 'Content-Type': 'application/json;charset=utf-8' });
        data.errorMessage = 'test error: locked';
        data.errorCode = 4041;
        response.end(JSON.stringify(data));
    }
    console.log("response======>user/face======>" + JSON.stringify(data));
});

/*
获取卡列表
 */
apiUser.get('/bankcards', function(request, response, next) {
    var data = [{
        id: "1",
        issueBankAbbr: 'CCB',
        issueBank: "建设银行",
        backgroundColor: '#A33324',
        accountNoEncrypted: "622222***9544",
        phoneNoEncrypted: "135****0999"
    }, {
        id: "2",
        issueBankAbbr: 'ABC',
        issueBank: "农业银行",
        backgroundColor: '#A33324',
        accountNoEncrypted: "623333***9544",
        phoneNoEncrypted: "135****0999"
    }, {
        id: "3",
        issueBankAbbr: 'CMB',
        issueBank: "招商银行",
        backgroundColor: '#A33324',
        accountNoEncrypted: "624444***9544",
        phoneNoEncrypted: "135****0999"
    }];
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    // var responseData = { errorCode: 2000, errorMessage: '', result: {} };
    // responseData.result = data;
    response.end(JSON.stringify(data));
});

apiUser.post('/bankcard/select', function(request, response, next) {
    console.log("post    ======>user/bankcard/select======>" + JSON.stringify(request.body));
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    var data = { status: 'success' };
    response.end(JSON.stringify(data));
    console.log("response======>user/bankcard/select======>" + JSON.stringify(data));
});

apiUser.post('/bankcard/add', function(request, response, next) {
    console.log("post    ======>user/bankcard/add======>" + JSON.stringify(request.body));
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    var data = { status: 'success' };
    response.end(JSON.stringify(data));
    console.log("response======>user/bankcard/add======>" + JSON.stringify(data));
});

/*
提交个人信息与联系人信息 旧API(需要增加省市区)
 */
apiUser.post('/extra', function(request, response, next) {
    console.log("post    ======>user/extra======>" + JSON.stringify(request.body));
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    var data = { status: 'success' };
    response.end(JSON.stringify(data));
    console.log("response======>user/extra======>" + JSON.stringify(data));
});

/*
获取个人信息 旧API(需要增加省市区)
 */
apiUser.get('/extra', function(request, response, next) {
    var data = {
        userMobile: '13577777777',
        homeAddress: '详细地址',
        contactName: '联系人名',
        contactRelationship: '1',
        contactPhoneno: '13588888888',
        addressProvince: '浙江省',
        addressCity: '杭州市',
        addressDistrict: '西湖区',
        addressCode: '330101'
    }
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    var responseData = { errorCode: 2000, errorMessage: 'OK' };
    responseData.result = data;
    if (request.query.v == "1") {
        responseData = data;
    }
    response.end(JSON.stringify(responseData));
});

apiUser.get('/password/trade', function(request, response, next) {
    var data = {
        alreadySet: true
    }
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    response.end(JSON.stringify(data));
});

/*
获取默认卡
 */
apiUser.get('/bankcard/select', function(request, response, next) {
    var data = {
        id: "1",
        issueBank: "招商银行",
        accountNoEncrypted: "624185***9544",
        phoneNoEncrypted: "135****0999"
    };
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    var responseData = { errorCode: 2000, errorMessage: '', result: {} };
    responseData = data;
    response.end(JSON.stringify(responseData));
});

module.exports = apiUser;
