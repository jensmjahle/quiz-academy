import { mount } from '@vue/test-utils'
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import QuizListItem from '@/components/QuizListItem.vue'
import { describe, it, expect, beforeEach } from 'vitest'

describe('QuizListItem', () => {
    let wrapper
    let app

    beforeEach(() => {
        app = createApp(QuizListItem)
        app.use(createPinia())
        wrapper = mount(QuizListItem, {
            props: {
                quiz: {
                    quizName: 'Test Quiz',
                    // Add other properties as needed
                }
            }
        })
    })
    /*
    it('renders correctly', () => {
        expect(wrapper.exists()).toBe(true)
    })
    */

    // Add more tests as needed
})
