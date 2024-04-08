<template>
    <div class="container">
        <label v-if="props.label"> {{ props.label }}</label>
        <input
            v-bind="$attrs"
            :value="props.modelValue"
            :placeholder="props.label"
            @input="$emit('update:modelValue', $event.target.value)"
        />
    </div>
</template>

<script setup>
const props = defineProps({
    label: {
        type: String,
        default: ""
    },
    modelValue: {
        type: [String, Number],
        default: ""
    }
});
</script>

<style scoped>
label {
    font-size: 1.25rem;
}

input {
    border-radius: 10px;
    color: var(--base-color);
    font-size: 1rem;
    padding: 1rem;
}
.container {
    display: flex;
    flex-flow: column;
    width: 100%;
    padding-top: 10px;
    padding-bottom: 10px;
}
</style>
