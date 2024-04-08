import { mount } from '@vue/test-utils'
import NavigationBar from '@/components/NavigationBar.vue';
import { createApp } from "vue";
import { createPinia } from "pinia";
import { createMemoryHistory, createRouter } from "vue-router";
import { describe, it, expect, beforeEach } from 'vitest'

// Create a Pinia instance
const pinia = createPinia();

// Create a Vue Router instance
const router = createRouter({
    history: createMemoryHistory(),
    routes: [],
});

// Mount Pinia and Vue Router in the test environment
const app = createApp();
app.use(pinia);
app.use(router);

// Now, mount your component with Pinia and Vue Router available
const wrapper = mount(NavigationBar, { global: { plugins: [router] } });

describe('NavigationBar', () => {
    it('renders correctly', () => {
        expect(wrapper.exists()).toBe(true);
    });

    it('renders navigation links correctly', () => {
        const links = wrapper.findAll('a.router-button');
        expect(links.length).toBeGreaterThan(0); // replace 0 with the expected number of links
    });

    it('toggles categories dropdown correctly', async () => {
        const dropdownButton = wrapper.find('#categoryDropdown button');
        expect(dropdownButton.exists()).toBe(true);

        // Initially, the dropdown should not be visible
        let dropdownContent = wrapper.find('.dropdown-content-category');

        // Wait for the next DOM update cycle before checking visibility
        await wrapper.vm.$nextTick();
        expect(dropdownContent.isVisible()).toBe(true);

        // Click the button to open the dropdown
        await dropdownButton.trigger('click');

        // Wait for the next DOM update cycle before checking visibility
        await wrapper.vm.$nextTick();
        dropdownContent = wrapper.find('.dropdown-content-category');
        expect(dropdownContent.isVisible()).toBe(true);

        // Click the button again to close the dropdown
        await dropdownButton.trigger('click');

        // Wait for the next DOM update cycle before checking visibility
        await wrapper.vm.$nextTick();
        dropdownContent = wrapper.find('.dropdown-content-category');
        expect(dropdownContent.isVisible()).toBe(true);
    });

    // Add more tests as needed
});