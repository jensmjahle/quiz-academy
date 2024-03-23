import { mount } from '@vue/test-utils';
import NavigationBar from '../NavigationBar.vue';
import { describe, it, expect } from "vitest";


describe('NavigationBar', () => {
  it('renders navigation links correctly', () => {
    const wrapper = mount(NavigationBar);

    // Check if all navigation links are rendered
    expect(wrapper.findAll('.router-button').length).toBe(5);

    // Check if the text content of navigation links is correct
    const links = wrapper.findAll('.router-button');
    expect(links[0].text()).toBe('Home');
    expect(links[1].text()).toBe('My Quizzes');
    expect(links[2].text()).toBe('Create Quiz');
    expect(links[3].text()).toBe('Log In');
    expect(links[4].text()).toBe('Sign Up');
  });

  // Add more test cases as needed
});
