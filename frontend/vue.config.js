module.exports = {
  chainWebpack: (config) => {
    config.module.rule('eslint').use('eslint-loader').options({
      fix: true,
    });
  },

  devServer: {
    port: 8081,
    proxy: 'http://localhost:8080',
  },

  outputDir: 'target/static/',
};
