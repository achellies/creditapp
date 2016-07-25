var webpack = require('webpack');
var path = require('path');
var fs = require('fs');
var glob = require('glob');
var autoprefixer = require('autoprefixer');

// 插件
var CommonsChunkPlugin = webpack.optimize.CommonsChunkPlugin;

// vars
var isPrd = process.env.NODE_ENV === 'production';
var isDev = process.env.NODE_ENV === 'development';
var chunkhash = isPrd ? '.[chunkhash:10]' : '';
var filehash = isPrd ? '.[hash:10]' : '';
var commonjs = glob.sync('./src/js/common/**/*.js');
var pages = glob.sync('./src/js/pages/**/*.js');
var entries = {};
var plugins = [];
var directives = glob.sync('./src/js/directives/**/*.js');
var filters = glob.sync('./src/js/filters/**/*.js');
var services = glob.sync('./src/js/services/**/*.js');
var providers = glob.sync('./src/js/providers/**/*.js');
var factories = glob.sync('./src/js/factories/**/*.js');


commonjs.push('fastclick');

// 设置entry
pages.forEach(function(p) {
    var name = path.basename(p, '.js');
    entries['pages/' + name] = path.dirname(p) + '/' + name;
});

//entries.app = './src/js/app';
entries.common = commonjs;
entries.angular = ['angular', 'angular-animate', 'angular-touch', 'angular-route'];
entries.app = ['./src/js/app.js']
    .concat(directives)
    .concat(filters)
    .concat(providers)
    .concat(filters)
    .concat(factories);

// 分开打包插件
//plugins.push(new CommonsChunkPlugin({ name: ['vendors'], filename: '[name].js', minChunks: Infinity }))
plugins.push(new CommonsChunkPlugin({ name: ['app', 'common', 'angular'], filename: '[name].js', minChunks: Infinity }));
// 测试自定义插件
// plugins.push(new TestPlugin());

if (isPrd) {
    // 压缩代码插件
    plugins.push(new webpack.optimize.UglifyJsPlugin());
    // md5插件
    // plugins.push(new WebpackManifestPlugin());
}

// webpack configset
module.exports = {
    entry: entries,
    output: {
        path: path.resolve(__dirname, isDev ? './dist' : './assets'),
        filename: '[name].js',
        publicPath: isDev ? './dist' : ''
    },
    resolveLoader: {
        // loader 短名
        // alias: {
        //     // 自定义loader
        //     "html-loader-custom": path.join(__dirname, "./webpack/loaders/html")
        // }
    },
    resolve: {
        // 明确查找路径，提高性能
        root: path.resolve('src'),
        modulesDirectories: ["node_modules"],
        extensions: ["", ".js", ".coffee", ".json"]
    },
    module: {
        loaders: [{
                test: /(\.jsx|\.js)$/,
                loader: 'babel',
                query: {
                    'presets': ['es2015', 'stage-1']
                },
                include: [path.join(process.cwd(), './src')]
            }, {
                test: /\.scss$/,
                loaders: ['style', 'css?root=' + __dirname, 'sass', 'postcss-loader']
            },
            // {
            //     test: /\.css$/,
            //     loaders: ['style', 'css', 'postcss-loader']
            // },
            // {
            //     test: /\.json$/,
            //     loader: 'json',
            //     exclude: /(node_modules|bower_components)/,
            // },
            {
                test: /\.(jpe?g|png|gif)$/,
                loader: 'url?limit=1024&name=imgs/[name].[ext]'
            },
            // {
            //     test: /\.(woff2?|otf|eot|svg|ttf)$/i,
            //     loader: 'url?name=fonts/[name].[ext]'
            // },
            {
                test: /\.html$/,
                loader: 'raw'
            }
        ]
    },
    postcss: function() {
        return {
            defaults: [autoprefixer],
            cleaner: [autoprefixer({ browsers: ['iOS > 7', 'Android > 4', 'last 4 Chrome versions'] })]
        };
    },
    plugins: plugins
}
