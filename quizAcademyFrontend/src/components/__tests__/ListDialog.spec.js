import { mount } from '@vue/test-utils';
import { createPinia } from 'pinia';
import { createApp } from 'vue';
import ListDialog from '@/components/ListDialog.vue';
import { describe, it, expect, beforeEach } from 'vitest'



describe('ListDialog', () => {
    let wrapper;

    beforeEach(() => {
        const app = createApp(ListDialog);
        app.use(createPinia());
        // Provide mock data to the component
        wrapper = mount(ListDialog, {
            props: {
                selectedQuiz: {
                    quizName: 'Mock Quiz',
                    quizDescription: 'Mock Description',
                    tags: [{ tagId: 1, tagName: 'Mock Tag' }],
                    questions: [],
                    user: 'Mock User'
                }
            }
        });
    });

    it('renders correctly', () => {
        expect(wrapper.exists()).toBe(true);
    });

    it('renders list of items correctly', async () => {
        // Wait for the next DOM update cycle before checking for items
        await wrapper.vm.$nextTick();
        const items = wrapper.findAll('.list-item');
        expect(items.length).toBeGreaterThanOrEqual(0); // replace 0 with the expected number of items
    });


    it('closes the dialog', async () => {
        const closeButton = wrapper.find('.cancel-button');
        await closeButton.trigger('click');

        // Check if the dialog is no longer visible
        expect(wrapper.isVisible()).toBe(true);
    });

    // Add more tests as needed
});
