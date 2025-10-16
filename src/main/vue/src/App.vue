<template>
  <div id="_layout">

  <header>
    _header
  </header>
  <nav>
    <ul id="_nav-menu">
      <li>
        <a href="/example/hub-and-spoke">
          Hub And Spoke
        </a>
      </li>
      <li>
        <a href="/example/master-detail">
          Master Detail
        </a>
      </li>
      <li>
        <a href="/example/modal-overlay">
          Modal Overlay
        </a>
      </li>
    </ul>
  </nav>
  <main>
    <router-view/>
  </main>
  <aside>
    _aside
  </aside>
  <footer>
    _footer
  </footer>

  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  #_layout {
    height: 100vh;
    display: grid;
    grid-template-areas:
                  '_header _header _header'
                  '_nav _main _aside'
                  '_footer _footer _footer';
    grid-template-columns: 18rem calc(100% - 18rem - 9rem) 9rem;
    grid-template-rows: auto 1fr 3rem;
  }
  header {
    grid-area: _header;
    display: grid;
    grid-template-areas:
                  '_header-logo _header-menu';
    grid-template-columns: auto auto;
    border-bottom: groove 2px lightgray;
    background-color: #fafafa;
    padding: 1rem;
  }
  nav {
    grid-area: _nav;
    display: grid;
    grid-template-areas:
                  '_nav-menu';
    padding: 1rem;
  }
  main {
    grid-area: _main;
    padding: 1rem;
  }
  aside {
    grid-area: _aside;
    padding: 1rem;
  }
  footer {
    grid-area: _footer;
    display: flex;
    justify-content: center;
    align-items: center;
    border-top: dotted 1px lightgray;
    background-color: #fafafa;
    font-size: smaller;
    color: #ccc;
  }

</style>
