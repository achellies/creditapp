import myReportsTpl from '../../templates/myTodo/myReports.html';
import '../contollers/myTodo/myReportsCtrl';
export default function($routeProvider) {

    $routeProvider
    // 个人查询页
        .when('/myTodo/reports', {
            template: myReportsTpl,
            controller: 'myReportsController',
            navbar: {
                leftTitle: "<",
                leftAction: "back",
                navigatorTitle: "分期详情"
            }
        })
        .otherwise('/myTodo/reports');
}
