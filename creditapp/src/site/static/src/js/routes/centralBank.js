import centralBankQueryTpl from '../../templates/centralBank/centralBankQuery.html';
import '../contollers/centralBank/centralBankQueryCtrl';
import centralBankResultTpl from '../../templates/centralBank/centralBankResult.html';
import '../contollers/centralBank/centralBankResultCtrl';
import centralBankDetailTpl from '../../templates/centralBank/centralBankDetail.html';
import '../contollers/centralBank/centralBankDetailCtrl';
import centralBankRegisterTpl from '../../templates/centralBank/centralBankRegister.html';
import '../contollers/centralBank/centralBankRegisterCtrl';
export default function($routeProvider) {

    $routeProvider
    // 个人查询页
        .when('/centralBank/query', {
            template: centralBankQueryTpl,
            controller: 'centralBankQueryController',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "人行报告查询"
            }
        })
        // 查询结果页
        .when('/centralBank/result', {
            template: centralBankResultTpl,
            controller: 'centralBankResultCtrl',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "人行报告"
            }
        })
        // 单项详情页
        .when('/centralBank/detail/:type', {
            template: centralBankDetailTpl,
            controller: 'centralBankDetailCtrl',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "信用卡记录"
            }
        })
        .when('/centralBank/register', {
            template: centralBankRegisterTpl,
            controller: 'centralBankRegisterCtrl',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "如何获取用户名和密码"
            }
        })
        .otherwise('/centralBank/query');
}
