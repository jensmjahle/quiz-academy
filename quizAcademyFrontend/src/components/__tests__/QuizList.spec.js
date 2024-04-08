import { mount } from "@vue/test-utils";
import { createPinia } from "pinia";
import { createApp } from "vue";
import QuizList from "@/components/QuizList.vue";
import { describe, it, expect, beforeEach } from "vitest";

describe("QuizList", () => {
    let wrapper;
    let app;

    beforeEach(() => {
        app = createApp(QuizList);
        app.use(createPinia());
        wrapper = mount(QuizList);
    });

    it("renders correctly", () => {
        expect(wrapper.exists()).toBe(true);
    });

    // Add more tests as needed
});
