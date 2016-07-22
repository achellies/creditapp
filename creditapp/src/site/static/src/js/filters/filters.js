function paddingZero() {
    return function(input, length) {
        input = input || '';
        length = length || 0;
        var out = input.toString();
        for (var i = 0; i < length - out.length; i++)
            out = '0' + out;

        return out
    }
};

function numberToChinese() {
    return function(input, uppercase) {
        var chineseUpper = ['零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖', '拾'];
        var chineseLower = ['零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十'];
        var source = [],out;
        if (uppercase) {
            source = chineseUpper;
        } else {
            source = chineseLower;
        }
        out = source[input];
        return out

    }
};


export default [{
    name: 'paddingZero',
    dependences: [],
    fn: paddingZero
}, {
    name: 'numberToChinese',
    dependences: [],
    fn: numberToChinese
}];
