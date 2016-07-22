var PI = Math.PI, lmx = 0;

window.requestAnimationFrame = window.requestAnimationFrame
    || window.mozRequestAnimationFrame
    || window.webkitRequestAnimationFrame
    || window.msRequestAnimationFrame;

function limit(n, max, min) {
    return Math.min(max, Math.max(n, min));
}
function Slider(options) {
    options = options || {};
    this.container = options.container;
    this.scrolling = false;
    this.scrollWidth = window.innerWidth - 30;
    this.step = options.step || 0;
    this.blocks = options.blocks;
    this.steps = options.steps;

    this.init();
}

Slider.prototype = {
    constructor: Slider,
    // 兼容滑动
    start: function () {
        var x = 0, y = 0, t = 0, touch = {};
        /**
         * touchstart
         */
        this.container[0].addEventListener('touchstart', function (e) {
            if (this.scrolling) return;
            // TODO style方式ios有兼容问题，暂时改来切换样式
            this.container[0].classList.remove('transition');
            e.preventDefault();
            touch.pageX = e.touches[0].pageX;
            touch.pageY = e.touches[0].pageY;
        }.bind(this), false);


        /**
         * touchend
         */
        this.container[0].addEventListener('touchend', function (e) {
            e.preventDefault();
            if (this.scrolling) return;
            this.container[0].classList.add('transition');
            var _touch = e.changedTouches[0], move, mx, my, dir, r;

            // 计算移动距离和方向
            move = this.compute(touch, _touch);
            mx = move.mx;
            my = move.my;
            dir = move.dir;
            lmx = 0; // 重置touchmove移动缓存

            if (dir) {
                r = dir == 'left' ? 1 : -1;
                this.step = limit(this.step += r, 3, 0);
            }

            // 根据step计算动画结束位置，不会有浮点数的问题
            t = this.scrollWidth * this.step * -1;
            this.container[0].style.WebkitTransform = 'translateX(' + t + 'px)';
        }.bind(this), false);


        /**
         * touchmove
         */
        this.container[0].addEventListener('touchmove', function (e) {
            if (this.scrolling) return;
            e.preventDefault();
            var _touch = e.touches[0], move, mx, my, dir;
            move = this.compute(touch, _touch);
            mx = move.mx;
            my = move.my;
            dir = move.dir;
            if (dir) {
                requestAnimationFrame(function () {
                    var _mx = mx - lmx;
                    lmx = mx;
                    this.container[0].style.WebkitTransform = 'translateX(' + (t += _mx) + 'px)';
                }.bind(this));
            }
        }.bind(this), false);

        /**
         * touchcancel
         */
        this.container[0].addEventListener('touchcancel', function(){

        }.bind(this), false);

        // 动画结束
        this.container.on('webkitTransitionEnd transitionend msTransitionEnd oTransitionEnd', function () {
            this.scrolling = false;
            this.container[0].classList.remove('transition');
            this.container.trigger('switchend', [this.step]);
        }.bind(this));

        // 动画开始
        this.container.on('webkitTransitionStart transitionstart msTransitionStart oTransitionStart', function () {
            this.scrolling = true;
            
        }.bind(this));
    },
    /**
     * 计算位置和方向
     */
    compute: function (to, tn) {
        var ox = to.pageX, oy = to.pageY, nx = tn.pageX, ny = tn.pageY;
        var mx = parseInt(nx) - parseInt(ox), my = parseInt(ny) - parseInt(oy);
        var dir;
        if (2 * Math.abs(my) <= Math.abs(mx)) {
            dir = mx > 0 ? 'right' : 'left';
        }
        return {
            dir: dir,
            mx: mx,
            my: my
        };
    },
    init:function(){
        var cw = parseInt(window.innerWidth);
        // 设置block width
        this.blocks.each(function (i, block) {
            $(block).css('width', cw - 40);
        });

        this.listenall();
    },
    listenall: function(){
        var that = this;
        this.container.on('switchend', function(e, step){
            that.steps.removeClass('cur next pre');
            that.steps.each(function(i, _step){
                if (step == i){
                    $(_step).addClass('cur');
                }else if (step < i){
                    $(_step).addClass('next');
                }else if(step>i){
                    $(_step).addClass('pre');
                }
            });
        });
    }
}

window.WdSlider = Slider;