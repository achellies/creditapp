import housingFundAddTpl from '../../templates/housingFund/housingFundAdd.html';
import '../contollers/housingFund/housingFundAddCtrl';
import housingFundListTpl from '../../templates/housingFund/housingFundList.html';
import '../contollers/housingFund/housingFundListCtrl';
import housingFundResultTpl from '../../templates/housingFund/housingFundResult.html';
import '../contollers/housingFund/housingFundResultCtrl';
import housingFundDetailTpl from '../../templates/housingFund/housingFundDetail.html';
import '../contollers/housingFund/housingFundDetailCtrl';
import housingFundRegisterTpl from '../../templates/housingFund/housingFundRegister.html';
import '../contollers/housingFund/housingFundRegisterCtrl';
export default function($routeProvider) {

    $routeProvider
    // 个人查询页
        .when('/housingFund/add', {
            template: housingFundAddTpl,
            controller: 'housingFundAddController',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "添加账号"
            }
        })
        .when('/housingFund/list', {
            template: housingFundListTpl,
            controller: 'housingFundListCtrl',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "公积金账户"
            }
        })
        .when('/housingFund/result/:id', {
            template: housingFundResultTpl,
            controller: 'housingFundResultCtrl',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "账户详情"
            }
        })
        .when('/housingFund/detail/:year', {
            template: housingFundDetailTpl,
            controller: 'housingFundDetailCtrl',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "缴费记录"
            }
        })
        .when('/housingFund/register', {
            template: housingFundRegisterTpl,
            controller: 'housingFundRegisterCtrl',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "如何获取用户名和密码"
            }
        })
        .otherwise('/housingFund/list');
}
