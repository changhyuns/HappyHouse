<template>
  <div id="app">
    <router-view :key="$route.fullPath" />
  </div>
</template>

<script>
// import NaviBar from "./components/layout/NaviBar.vue";

export default {
  name: "App",
  components: {
    // NaviBar,
  },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@800&display=swap');

h1, h2, h3, span {
  font-family: 'Nanum Gothic', sans-serif;
}

a:hover {
  text-decoration: none;
  font-weight: bold;
}

a.router-link-exact-active {
  color: #42b983;
}
</style>
