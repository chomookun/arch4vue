module.exports = {
    root: true,
    env: {
        node: true
    },
    extends: [
        'eslint:recommended',
        'plugin:vue/vue3-essential'
    ],
    parserOptions: {
        ecmaVersion: 2020
    },
    rules: {
        'no-unused-vars': 'off',
        'vue/multi-word-component-names': 'off',
        "vue/require-v-for-key": "off",
        "vue/no-unused-vars": "off"
    }
}
