import { mount } from "@vue/test-utils";
import { createPinia } from "pinia";
import { createApp } from "vue";
import DragAndDropQuestionPlaythrough from "@/components/questionPlaythroughComponets/DragAndDropQuestionPlaythrough.vue";
import { describe, it, expect, beforeEach } from "vitest";

describe("DragAndDropQuestionPlaythrough", () => {
    let wrapper;
    let app;

    beforeEach(() => {
        app = createApp(DragAndDropQuestionPlaythrough);
        app.use(createPinia());
        wrapper = mount(DragAndDropQuestionPlaythrough);
    });

    it("renders correctly", () => {
        expect(wrapper.exists()).toBe(true);
    });

    // Add more tests as needed
});
