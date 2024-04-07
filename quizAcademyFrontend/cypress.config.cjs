const { defineConfig } = require('cypress')

module.exports = defineConfig({

    e2e: {
        baseUrl: "http://localhost:5173",
        specPattern: "cypress/e2e/**/*.{cy,spec}.{js,jsx,ts,tsx}",
    },
    component: {
        devServer: {
            framework: "vue-cli",
            bundler: "webpack",
            port: 5173
        },
    },
});