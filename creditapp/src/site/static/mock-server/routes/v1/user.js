var express = require('express');
var apiUser = express.Router();
// http://mockjs.com/
var Mock = require('mockjs');

/* GET users listing. */
apiUser.get('/', function(req, res, next) {
    res.send('respond with a resource');
});
/*
流程状态查询
 */
apiUser.get('/process', function(request, response, next) {
    var data = {
        currentStep: "idcard",
        amount: 999999,
        period: 6
    }
    if (request.query.productid == '1') {
        data.currentStep = "idcard";
    }
    if (request.query.productid == '2') {
        data.currentStep = "face";
    }
    if (request.query.productid == '3') {
        data.currentStep = "bankcard";
    }
    if (request.query.productid == '4') {
        data.currentStep = "contactInfo";
    }
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    var responseData = { errorCode: 2000, errorMessage: 'OK' };
    responseData.result = data;
    response.end(JSON.stringify(responseData));
});

/**
 * 创建流程
 */
apiUser.post('/process', function(req, res, next) {
    res.json({
        "errorCode": 2000,
        "errorMessage": "",
        "result": {
            "serialNo": "1"
        }
    })
});
/*
更改流程
 */
apiUser.put('/process', function(request, response, next) {
    var responseData = { errorCode: 2000, errorMessage: 'OK' };
    console.log("put     ======>user/process======>" + JSON.stringify(request.body));
    var data = {
        nextStep: ""
    }
    if (request.body.finishStep == 'idcard') {
        data.nextStep = "face";
    }
    if (request.body.finishStep == 'face') {
        data.nextStep = "contactInfo";
    }
    if (request.body.finishStep == 'contactInfo') {
        data.nextStep = "bankcard";
    }
    if (request.body.finishStep == 'bankcard') {
        if (request.body.verifyCode == "444444") {
            responseData.errorCode = "4014";
            responseData.errorMessage = "验证码错误，请检查后重新输入";
        }
        data.nextStep = "confirm";
    }
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });

    responseData.result = data;
    response.end(JSON.stringify(responseData));
    console.log("response======>user/process======>" + JSON.stringify(responseData));
});

apiUser.delete('/process', function(request, response, next) {
    var responseData = { errorCode: 2000, errorMessage: 'OK' };
    console.log("delete     ======>user/process======>" + JSON.stringify(request.query));
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    if (request.query.serialNo == "1") {
        responseData.errorCode = "4001";
        responseData.errorMessage = "流程无法删除";
    }
    response.end(JSON.stringify(responseData));
    console.log("response======>user/process======>" + JSON.stringify(responseData));
});

/*
根据银行卡ID获取验证码
 */
apiUser.post('/bankcard/verifycode', function(request, response, next) {
    console.log(request.body);
    if (request.body.cardId == "1") {
        console.log('向id为1的银行卡所绑定的手机号发送验证码');
        var data = {
            ttl: 30
        }
        response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
        var responseData = { errorCode: 2000, errorMessage: '' };
        responseData.result = data;
        response.end(JSON.stringify(responseData));
    } else {
        response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
        var responseData = { errorCode: 4001, errorMessage: '只有卡号1的卡能发短信哦···' };
        response.end(JSON.stringify(responseData));
    }

});

/**
 * 分期列表
 */
apiUser.get('/loans', function(req, res, next) {
    res.json({
        "errorCode": 2000,
        "errorMessage": "",
        "result": [
            {
            "productId": "123",
            "loanName": "家装分期",
            "amount": 100000,
            "status": "checking",
            "serialNo": "1",
            "applyDate": 14567533434
        }, {
            "productId": "123",
            "loanName": "家装分期",
            "amount": 100000,
            "status": "paying",
            "serialNo": "2",
            "applyDate": 14567533434
        }, {
            "productId": "123",
            "loanName": "家装分期",
            "amount": 100000,
            "status": "paid",
            "serialNo": "3",
            "applyDate": 14567533434
        }, {
            "productId": "123",
            "loanName": "家装分期",
            "amount": 100000,
            "status": "repayed",
            "serialNo": "4",
            "applyDate": 14567533434
        }, {
            "productId": "123",
            "loanName": "家装分期",
            "amount": 100000,
            "status": "refused",
            "serialNo": "5",
            "applyDate": 14567533434
        }, {
            "productId": "123",
            "loanName": "家装分期",
            "amount": 100000,
            "status": "applying",
            "serialNo": "6",
            "applyDate": 14567533434
        }
        ]
    });
});

/**
 * 分期详情
 */
apiUser.get('/loan', function(req, res, next) {
    var serialNo = req.query.serialNo;
    console.log(serialNo);
    res.json({
        "errorCode": 2000,
        "errorMessage": "",
        "result": {
            "productId": "001",
            "loanName": "家装分期",
            "amount": 100000,
            "status": "applying",
            "applyDate": 14567533434,
            "bankName": "招商银行",
            "bankCard": "*8888",
            "serialNo": "1",
            "payDetails": [{
                "payStatus": "paid",
                "amount": 40000
            }, {
                "payStatus": "cancelled",
                "amount": 40000
            }, {
                "payStatus": "paid",
                "amount": 40000
            }, {
                "payStatus": "paid",
                "amount": 40000
            }],
            "overdueAmount": 1000
        }
    });
});

/**
 * 提交分期申请
 */

apiUser.post('/loan', function(request, response, next) {
    var responseData = { errorCode: 2000, errorMessage: 'OK' };
    console.log(request.body);
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    if (request.body.password != '123456') {
        responseData.errorCode = "4013";
        responseData.errorMessage = "密码错误";
    }
    response.end(JSON.stringify(responseData));
});

/**
 * 分期支付
 */
apiUser.put('/loan', function(req, res, next) {
    var productid = req.body.productid;
    var serialNo = req.body.serialNo;
    console.log(productid);
    res.json({
        "errorCode": 2000,
        "errorMessage": "支付失败"
    });
});

/*
获取还款计划
 */
apiUser.get('/repayments', function(request, response, next) {
    var responseData = { errorCode: 2000, errorMessage: 'OK' };
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });

    var data = {
        paymentList: [{
            loanAmount: 20000.00,
            period: 1,
            principalAmtList: [{ payPrincipalAmt: 8000.00, times: 1 }, { payPrincipalAmt: 7000.00, times: 2 }, { payPrincipalAmt: 5000.00, times: 3 }],
            repayDate: 1468166400000,
            useFeeAmount: 589, //分期手续费
            limitFeeAmount: 589, //额度动用手续费
        }, {
            loanAmount: 25000.00,
            period: 2,
            principalAmtList: [{ payPrincipalAmt: 8000.00, times: 1 }, { payPrincipalAmt: 7000.00, times: 2 }, { payPrincipalAmt: 10000.00, times: 3 }],
            repayDate: 1468166400000,
            useFeeAmount: 222, //分期手续费
            limitFeeAmount: 222, //额度动用手续费
        }]
    };
    responseData.result = data;
    response.end(JSON.stringify(responseData));
});

/*
获取还款详情
 */
apiUser.get('/repayment', function(request, response, next) {
    var data = {};
    var responseData = { errorCode: 2000, errorMessage: 'OK' };
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });
    if (request.query.serialNo == "1") {
        data = {
            serialNo: 1,
            repaymentAmount: 500900,
            isOverdue: true,
            overdueAmount: 100000,
            principalAmount: 500000,
            interestFee: 800,
            penaltyFee: 100,
            payBank: '逾期的银行',
            bankcard: '**** 8888'
        };
    }
    if (request.query.serialNo == "2") {
        data = {
            serialNo: 2,
            repaymentAmount: 801000,
            isOverdue: false,
            overdueAmount: 100000,
            principalAmount: 800000,
            interestFee: 500,
            penaltyFee: 500,
            payBank: '未逾期的银行',
            bankcard: '**** 9999'
        };
    }

    responseData.result = data;
    response.end(JSON.stringify(responseData));
});
/*
提交还款申请
 */
apiUser.post('/repayment', function(request, response, next) {
    console.log("post     ======>user/repayment======>" + JSON.stringify(request.body));
    var responseData = { errorCode: 2000, errorMessage: 'OK' };
    // ;
    if (request.body.serialNo == "1" && request.body.prepayAmount > 90000) {
        responseData.errorCode = 4001;
        responseData.errorMessage = "测试只能还少于90000哦";
    }
    if (request.body.password != "123456") {
        responseData.errorCode = 4013;
        responseData.errorMessage = "密码只能是123456";
    }
    response.writeHead(200, { 'Content-Type': 'application/json;charset=utf-8' });

    response.end(JSON.stringify(responseData));
    console.log("response======>user/repayment======>" + JSON.stringify(responseData));
})


module.exports = apiUser;
