import { mount } from "@vue/test-utils";
import { createPinia } from "pinia";
import { createApp } from "vue";
import MultipleChoiceQuestionCreation from "@/components/questionCreationComponents/MultipleChoiceQuestionCreation.vue";
import { describe, it, expect, beforeEach } from "vitest";

describe("MultipleChoiceQuestionCreation", () => {
    let wrapper;
    let app;

    beforeEach(() => {
        app = createApp(MultipleChoiceQuestionCreation);
        app.use(createPinia());
        wrapper = mount(MultipleChoiceQuestionCreation);
    });

    it("renders correctly", () => {
        expect(wrapper.exists()).toBe(true);
    });

    // Add more tests as needed
});
