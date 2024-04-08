import { mount } from "@vue/test-utils";
import { createPinia } from "pinia";
import { createApp } from "vue";
import QuizQuestionDragSort from "@/components/DragAndDropQuestionPlaythrough.vue";
import { describe, it, expect, beforeEach } from "vitest";

describe("QuizQuestionDragSort", () => {
    let wrapper;
    let app;

    beforeEach(() => {
        app = createApp(QuizQuestionDragSort);
        app.use(createPinia());
        wrapper = mount(QuizQuestionDragSort);
    });

    it("renders correctly", () => {
        expect(wrapper.exists()).toBe(true);
    });

    // Add more tests as needed
});
