<template>
    <div id="info">
        <div id="el-dialog" class=""></div>
<!-- <button id="add" @click="toggleBodyClass('addClass', 'noScroll')">Add Class</button>
<button id="remove" @click="toggleBodyClass('removeClass', 'noScroll')">Remove Class</button> -->
    </div>
</template>
<script>
import Event1 from '../bustwo.js';
export default {
  name: 'info',
  methods: {
  toggleBodyClass(addRemoveClass, className) {
    const el = document.getElementById("add");

    if (addRemoveClass === 'addClass') {
      el.classList.add(className);
    } else {
      el.classList.remove(className);
    }
  }
},
}
</script>
<style>
</style>
