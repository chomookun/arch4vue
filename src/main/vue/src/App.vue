<template>
  <header>
    Header
  </header>
  <router-view/>
  <footer>
    Footer
  </footer>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
header {
  border: solid 1px red;
}
footer {
  footer: solid 1px blue;
}
</style>
