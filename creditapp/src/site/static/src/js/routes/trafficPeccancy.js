import trafficPeccancyAddTpl from '../../templates/trafficPeccancy/trafficPeccancyAdd.html';
import '../contollers/trafficPeccancy/trafficPeccancyAddCtrl';
import trafficPeccancyResultTpl from '../../templates/trafficPeccancy/trafficPeccancyResult.html';
import '../contollers/trafficPeccancy/trafficPeccancyResultCtrl';
import trafficPeccancyDetailTpl from '../../templates/trafficPeccancy/trafficPeccancyDetail.html';
import '../contollers/trafficPeccancy/trafficPeccancyDetailCtrl';
export default function($routeProvider) {

    $routeProvider
    // 个人查询页
        .when('/trafficPeccancy/add', {
            template: trafficPeccancyAddTpl,
            controller: 'trafficPeccancyAddController',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "添加车辆"
            }
        })
        // 查询结果页
        .when('/trafficPeccancy/result', {
            template: trafficPeccancyResultTpl,
            controller: 'trafficPeccancyResultCtrl',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "违章查询"
            }
        })
        // 单项详情页
        .when('/trafficPeccancy/detail/:type', {
            template: trafficPeccancyDetailTpl,
            controller: 'trafficPeccancyDetailCtrl',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "车辆违章记录"
            }
        })
        .otherwise('/trafficPeccancy/result');
}
