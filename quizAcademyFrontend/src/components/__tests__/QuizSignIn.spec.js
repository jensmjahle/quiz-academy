import { mount } from '@vue/test-utils'
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import QuizSignIn from '@/components/QuizSignIn.vue'
import { describe, it, expect, beforeEach } from 'vitest'
import localVue from "vue-auth-jwt";


describe('QuizSignIn', () => {
    let wrapper
    let app

    beforeEach(() => {
        app = createApp(QuizSignIn)
        app.use(createPinia())
        wrapper = mount(QuizSignIn, {localVue})
    })

    it('renders correctly', () => {
        expect(wrapper.exists()).toBe(true)
    })

    // Add more tests as needed
})