import personalQueryTpl from '../../templates/personal/personalQuery.html';
import '../contollers/personal/personalQueryCtrl';
import personalResultTpl from '../../templates/personal/personalResult.html';
import '../contollers/personal/personalResultCtrl';
import personalDetailTpl from '../../templates/personal/personalDetail.html';
import '../contollers/personal/personalDetailCtrl';
export default function($routeProvider) {

    $routeProvider
    // 个人查询页
        .when('/personal/query', {
            template: personalQueryTpl,
            controller: 'personalQueryController',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "个人报告查询"
            }
        })
        // 查询结果页
        .when('/personal/result', {
            template: personalResultTpl,
            controller: 'personalResultCtrl',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "个人报告"
            }
        })
        // 单项详情页
        .when('/personal/detail/:type', {
            template: personalDetailTpl,
            controller: 'personalDetailCtrl',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "个人报告"
            }
        })
        .otherwise('/personal/query');
}
