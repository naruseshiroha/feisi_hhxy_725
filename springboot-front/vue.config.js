// module.exports = {
//   publicPath: "./", //配置根目录
//   outputDir: "dist", //构建输出目录
//   assetsDir: "assets", //静态资源目录
//   lintOnSave: false, //false不开启，有效值：true || false
//   productionSourceMap: false, //关闭生产环境下面的source-map
//   devServer: {
//     open: true, //是否启动打开浏览器
//     host: "localhost", //主机，0.0.0.0支持局域网地址，可以用真机测试
//     // host: "0.0.0.0", //主机，0.0.0.0支持局域网地址，可以用真机测试
//     port: "8080",
//     //配置跨域代理
//     proxy: {
//       "/proxy": {
//         // target:"http://vueshop.glbuys.com/api",
//         target: "http://localhost:8088",
//         changeOrigin: true, //支持跨域
//         pathRewrite: {
//           "^/proxy": "",
//         },
//       },
//     },
//   },
//   // configureWebpack:{
//   //     devtool: 'source-map' //打开sourceMap用于断点调试
//   // },
//   configureWebpack: (config) => {
//     // console.log(config)
//     config.devtool = "source-map"; //配置开发者环境的sourceMap用于断点调试
//   },
// };
