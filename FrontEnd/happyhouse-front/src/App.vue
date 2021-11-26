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
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap');

h1, h2, h3, h4, h5, h6, span, a{
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
