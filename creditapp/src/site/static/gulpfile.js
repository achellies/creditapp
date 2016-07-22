var autoprefixer = require('autoprefixer');
var gulp = require('gulp');
var postcss = require('gulp-postcss');
var webpack = require('webpack');
var gulpwebpack = require('gulp-webpack');
var wpconf = require('./webpack.config.js');
var del = require('del');
var browserSync = require('browser-sync');
var revReplace = require('gulp-rev-replace');
var imagemin = require('gulp-imagemin');
var sequence = require('gulp-run-sequence');
var sass = require('gulp-sass');
var csso = require('gulp-csso');
var rev = require('gulp-rev');
var rename = require('gulp-rename');
var uglify = require('gulp-uglify');
var nodemon = require('gulp-nodemon');

// vars
var isPrd = process.env.NODE_ENV === 'production'; // 生产环境
var isDev = process.env.NODE_ENV === 'development'; // 开发环境
var bs = browserSync.create();
var reload = browserSync.reload;

// 清空中介目录
gulp.task('clear-assets', function () {
    return del(['./assets/*']);
});

// 清空目录
gulp.task('clear-dist', function () {
    return del(['./dist/*']);
});

// 清空目录
gulp.task('publish', function () {
    del.sync(['./installment/dist/**']);
    gulp.src('./dist/**')
    .pipe(gulp.dest('./installment/dist'));
});

// webpack构建
gulp.task('webpack', function () {
    return gulp.src('')
        .pipe(gulpwebpack(wpconf, webpack))
        .pipe(gulp.dest('./dist/js'))
        // 自动刷新
        .pipe(bs.stream());
});

// 自动刷新开发任务
gulp.task('bs', function () {
    bs.init({
        proxy: {
            target: "http://localhost:3012"
        },
        port: 3003
    });
});

// mock服务
gulp.task('mock', function (cb) {
    nodemon({
        script: 'mock-server/bin/www',
        ext: 'js'
    }).on('restart', function () {
        console.log('restarted!')
    });
    cb();
});

// 处理cordova
gulp.task('cordova', function () {
    gulp.src(['./src/libs/cordova/**/*.js',
        '!./src/libs/cordova/cordova-plugins/cordova-js-src/*',
        '!./src/libs/cordova/cordova-plugins/js/*'
    ]).pipe(uglify({
        mangle: false
    })).pipe(gulp.dest('./dist/libs/cordova'));
});


// 处理frozen
gulp.task('frozenui', function () {
    gulp.src(['./src/libs/frozenui/**'])
        .pipe(gulp.dest('./dist/libs/frozenui'));
});


// 处理zepto
gulp.task('zepto', function () {
    gulp.src(['./src/libs/zepto/**'])
        .pipe(gulp.dest('./dist/libs/zepto'));
});


// 处理fastclick
gulp.task('fastclick', function () {
    gulp.src(['./src/libs/fastclick/**'])
        .pipe(gulp.dest('./dist/libs/fastclick'));
});


/************开发环境任务***********/

// 开发样式任务
gulp.task('dev-sass', function () {
    return gulp.src(['src/sass/**/*.scss'])
        .pipe(sass({
            outputStyle: 'expanded'
        }))
        // css兼容性
        .pipe(postcss([autoprefixer({
            browsers: ['iOS > 7', 'Android > 4', 'last 4 Chrome versions']
        })]))
        .pipe(gulp.dest('./dist/css'))
        // 自动刷新
        .pipe(bs.stream());
});


// 字体库
gulp.task('dev-fonts', function () {
    return gulp.src('./src/fonts/**/*.{woff,woff2,svg,eot,ttf}')
        .pipe(gulp.dest('./dist/fonts'));
});


// 
gulp.task('dev-imgs', function () {
    gulp.src('./src/imgs/**/*.{jpg,jpeg,gif,png}')
        .pipe(gulp.dest('./dist/imgs'));
});

// 
gulp.task('dev-vendors', function () {
    return gulp.src('./src/js/vendors/**/*.js')
        .pipe(gulp.dest('./dist/js/vendors'));
});

// 开发视图任务
gulp.task('dev-view', function () {
    return gulp.src(['./src/views/**/*.html'])
        .pipe(gulp.dest('./dist/views'))
        // 自动刷新浏览器
        .pipe(bs.stream())
});

// 开发重新打包任务
gulp.task('dev-rebuild', ['clear-dist'], function () {
    return sequence('webpack', 'dev-vendors', 'dev-sass', 'dev-view', 'dev-fonts', 'dev-imgs', 'cordova', 'frozenui', 'zepto','fastclick');
});

// 监控文件变化，自动构建, 手动刷新
gulp.task('dev-watch', ['dev-rebuild'], function (cb) {
    gulp.watch("src/sass/**/*.scss", ['dev-sass']);
    gulp.watch(["src/js/**/*.js", "src/templates/**/*.html", "src/sass/**/*.scss"], ['webpack']);
    gulp.watch("src/views/**/*.html", ['dev-view']);
    cb();
});

// 自动刷新
gulp.task('dev-reload', function () {
    return sequence('dev-watch', 'mock', 'bs');
});


/******************产线任务****************/

gulp.task('rev-replace', function () {
    var manifest = gulp.src("./dist/rev-manifest.json");
    return gulp.src(['./src/views/**/*.html'])
        .pipe(revReplace({
            manifest: manifest
        }))
        .pipe(gulp.dest('./dist/views'));
});

// 
gulp.task('prd-fonts', function () {
    return gulp.src('./src/fonts/**/*.{woff,woff2,svg,ttf,eot}')
        .pipe(gulp.dest('./assets/fonts'))
});

// 
gulp.task('prd-vendors', function () {
    return gulp.src('./src/js/vendors/**/*.js')
        .pipe(uglify({
            mangle: false
        }))
        .pipe(gulp.dest('./dist/js/vendors'));
});

// 
gulp.task('prd-imgs', function () {
    return gulp.src('./src/imgs/**/*.{jpg,jpeg,gif,png}')
        .pipe(imagemin())
        .pipe(gulp.dest('./assets/imgs'))
});

gulp.task('prd-sass', function (cb) {
    return gulp.src('./src/sass/**/*.scss')
        .pipe(sass())
        // css兼容性
        .pipe(postcss([autoprefixer({
            browsers: ['iOS > 7', 'Android > 4', 'last 4 Chrome versions']
        })]))
        // 压缩
        .pipe(csso({
            restructure: true,
            debug: true
        }))
        .pipe(gulp.dest('./assets/css'));
});

// webpack构建
gulp.task('prd-webpack', function () {
    return gulp.src('')
        .pipe(gulpwebpack(wpconf, webpack))
        .pipe(gulp.dest('./assets/js'))
});

gulp.task('manifest', function () {
    return gulp.src('./assets/**/*.{js,css,jpg,jpeg,gif,png,woff,woff2,svg,eot,ttf}')
        .pipe(rev())
        .pipe(rename(function (p) {
            var basename = p.basename;
            p.basename = basename.replace(/-(\w+)$/, '.$1');
            return p;
        }))
        .pipe(gulp.dest('./dist/'))
        .pipe(rev.manifest())
        .pipe(gulp.dest('./dist'))
});

// 生产产物build
gulp.task('prd-rebuild', ['clear-assets'], function () {
    return sequence('clear-dist', 'prd-webpack', 'prd-vendors', 'prd-sass', 'prd-fonts', 'prd-imgs', 'manifest', 'cordova', 'frozenui', 'zepto','fastclick', 'rev-replace');
});

// 生产产物build带服务
gulp.task('prd', function () {
    return sequence('prd-rebuild', 'mock', 'bs');
});
