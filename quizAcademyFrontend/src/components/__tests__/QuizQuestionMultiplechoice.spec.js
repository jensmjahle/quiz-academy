import { mount } from '@vue/test-utils'
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import QuizQuestionMultichoice from '@/components/QuizQuestionMultichoice.vue'
import { describe, it, expect, beforeEach } from 'vitest'

describe('QuizQuestionMultiplechoice', () => {
    let wrapper
    let app

    beforeEach(() => {
        app = createApp(QuizQuestionMultichoice)
        app.use(createPinia())
        wrapper = mount(QuizQuestionMultichoice)
    })

    it('renders correctly', () => {
        expect(wrapper.exists()).toBe(true)
    })

    // Add more tests as needed
})