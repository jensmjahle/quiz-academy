import { mount } from "@vue/test-utils";
import { createPinia } from "pinia";
import { createApp } from "vue";
import BaseAnswerContainer from "@/components/BaseAnswerContainer.vue";
import { describe, it, expect, beforeEach } from "vitest";
import localVue from "vue-auth-jwt";

describe("BaseAnswerContainer", () => {
    let wrapper;
    let app;

    beforeEach(() => {
        app = createApp(BaseAnswerContainer);
        app.use(createPinia());
        wrapper = mount(BaseAnswerContainer, {
            localVue,
            propsData: {
                question: {
                    type: "MULTIPLE_CHOICE" // or any other type your component expects
                    // add other properties that your component expects in a question
                }
            }
        });
    });

    it("renders correctly", () => {
        expect(wrapper.exists()).toBe(true);
    });

    // Add more tests as needed
});
