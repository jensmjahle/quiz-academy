import { mount } from "@vue/test-utils";
import { createPinia } from "pinia";
import { createApp } from "vue";
import TextInputQuestionCreation from "@/components/questionCreationComponents/TextInputQuestionCreation.vue";
import { describe, it, expect, beforeEach } from "vitest";

describe("TextInputQuestionCreation", () => {
    let wrapper;
    let app;

    beforeEach(() => {
        app = createApp(TextInputQuestionCreation);
        app.use(createPinia());
        wrapper = mount(TextInputQuestionCreation);
    });

    it("renders correctly", () => {
        expect(wrapper.exists()).toBe(true);
    });

    // Add more tests as needed
});
