import { mount } from "@vue/test-utils";
import { createPinia } from "pinia";
import { createApp } from "vue";
import QuizQuestionTextInput from "@/components/TextInputQuestionCreation.vue";
import { describe, it, expect, beforeEach } from "vitest";

describe("QuizQuestionTextInput", () => {
    let wrapper;
    let app;

    beforeEach(() => {
        app = createApp(QuizQuestionTextInput);
        app.use(createPinia());
        wrapper = mount(QuizQuestionTextInput);
    });

    it("renders correctly", () => {
        expect(wrapper.exists()).toBe(true);
    });

    // Add more tests as needed
});
