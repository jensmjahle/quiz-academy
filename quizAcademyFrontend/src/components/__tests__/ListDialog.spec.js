import { mount } from '@vue/test-utils'
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import ListDialog from '@/components/ListDialog.vue'
import { describe, it, expect, beforeEach } from 'vitest'

describe('ListDialog', () => {
    let wrapper
    let app

    beforeEach(() => {
        app = createApp(ListDialog)
        app.use(createPinia())
        wrapper = mount(ListDialog)
    })

    it('renders correctly', () => {
        expect(wrapper.exists()).toBe(true)
    })

    // Add more tests as needed
})
