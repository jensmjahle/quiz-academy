import { mount } from '@vue/test-utils'
import { createPinia } from 'pinia'
import { createApp, provide } from 'vue'
import CreateQuiz from '@/components/CreateQuiz.vue'
import { describe, it, expect, beforeEach } from 'vitest'
import { createRouter, createWebHistory } from 'vue-router'

describe('CreateQuiz', () => {
    let wrapper
    let app

    beforeEach(() => {
        const router = createRouter({
            history: createWebHistory(),
            routes: [
                { path: '/create-quiz/:question', name: 'CreateQuiz', component: CreateQuiz },
                // Add other routes as needed
            ],
        })

        router.push({ name: 'CreateQuiz', params: { question: {} } }) // or any other value your component expects

        app = createApp({
            setup() {
                provide('$auth', {
                    login: () => Promise.resolve(),
                    loggedIn: true,
                    user: {
                        username: 'johndoe',
                        password: 'password',
                        // Add other user properties as needed
                    },
                })
            },
            render: h => h(CreateQuiz)
        })
        app.use(createPinia())
        app.use(router)

        wrapper = mount(app, { router })
    })

    it('renders correctly', () => {
        expect(wrapper.exists()).toBe(true)
    })

    // Add more tests as needed
})