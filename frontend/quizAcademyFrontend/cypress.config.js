import { defineConfig } from "cypress";

export default defineConfig({

    e2e: {
        baseUrl: "http://localhost:5173",
        specPattern: "cypress/e2e/**/*.{cy,spec}.{js,jsx,ts,tsx}",
    },

    component: {
        devServer: {
            framework: "vue-cli",
            bundler: "webpack",
            port: 5173,
        },
    },
});

// Path: frontend/quizAcademyFrontend/cypress.json