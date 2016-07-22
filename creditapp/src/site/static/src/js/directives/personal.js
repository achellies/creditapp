/**
 * [点击时修改状态 展示背景图片]
 */
function personalContentSelect() {
    return {
        restrict: 'AE',
        replace: true,
        link: function(scope, ele, attrs) {
            ele.bind('click', function() {
                scope.$apply(scope.submitDisabled = !scope.submitDisabled);
                if (scope.content.type === 'all') {
                    var selected = !scope.content.selected;
                    scope.$apply(scope.contentList.forEach(function(content) {
                        content.selected = selected;
                    }));
                } else {
                    scope.$apply(function() {
                        scope.content.selected = !scope.content.selected;
                        if (!scope.content.selected) {
                            scope.contentList[0].selected = false;
                        }
                    });
                }
            });
        }
    }
};

/**
 * export directive module
 */
export default [{
    name: 'personalContentSelect',
    dependences: [],
    fn: personalContentSelect
}];
