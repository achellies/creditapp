export default angular.module('wanda.app')
    .controller('centralBankDetailCtrl', ['$scope', '$routeParams', function($scope, $routeParams) {
        $scope.ar_info = [
            {
                'title': '从未发生过逾期的账号明细如下',
                'list': [
                    '因为let类似于私有变量的特性，只要在let的地盘里（块级作用域），你说什么let就做什么，纯种二大爷属性。你想让let像var一样，多做一丢丢！？呵呵…小伙子还是读书少啊～～ 你见过二大爷属性的人给你端茶递水吗}',
                    'const的作用域与let命令相同：只在声明所在的块级作用域内有效，并且不可重复声明'
                ]
            }, {
                'title': '发生过逾期的账户明细如下',
                'list': [
                    'let就是地头蛇，出了他的地盘，他就不管你了。但是一旦谁敢抢他位置，他就敢跟你横（报错）',
                    '在他的地盘（相同作用域），不能重复声明同一个变量'
                ]
            }];
    }]);
