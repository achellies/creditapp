;(function() {
    var common = {
        fixFooterBottom: function() {
            var ctlToFixed = $('[fix-bottom]');
            if (ctlToFixed.length > 0) {
                ctlToFixed = $(ctlToFixed[0]);
                var bottomOffset = typeof bottomOffset !== 'undefined' ? bottomOffset : 0,
                    body = document.body,
                    html = document.documentElement,
                    height = Math.max(body.offsetHeight, html.clientHeight, html.offsetHeight);

                var wnd_height = $(window).height();
                var final_height = (height > wnd_height) ? height : wnd_height;
                var ctl_height = ctlToFixed.height();
                // Collapsing Margins
                // 规定浮动元素和绝对定位元素不参与Margin折叠
                $('body').css('height', final_height).css('position', "absolute");
                ctlToFixed.css({ "bottom": 0, "position": "absolute" });
            }
        }
    }
    window.wdCommon = common;
})()
