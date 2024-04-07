import { defineConfig } from 'vite'
import { fileURLToPath, URL } from 'node:url';
import vue from '@vitejs/plugin-vue'
import {resolve} from "path";
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  coverage: {
    enabled: true,
    outputDir: resolve(__dirname, 'coverage'), // Output coverage reports to a 'coverage' directory in the root of the project
    exclude: ['**/node_modules/**'], // Exclude node_modules from coverage
  }
})