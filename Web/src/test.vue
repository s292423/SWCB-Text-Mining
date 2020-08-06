<template>
  <div>
      <child-2 />
      <child-1  :btnName="btnName"/>
  </div>
</template>

<script>
import Child1 from './components/Child1'
import Child2 from './components/Child2'

export default {
  name: 'Parent',
  components: {
    'child-1': Child1,
    'child-2': Child2
  },
  data () {
    return {
      btnName: ' 我是一个button'
    }
  }
}
</script>
