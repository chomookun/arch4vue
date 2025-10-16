module.exports = {
    devServer: {
        port: 8000,
        proxy: {
            '^/api/example': {
                target: 'http://127.0.0.1:8080',
                changeOrigin: true
            }
        }
    }
}
