<template>
  <div>
    About
  </div>
</template>

<script>
import { actuator, baidu } from '../api/actuator'

export default {
  name: 'AboutView',
  mounted() {
    this.$ipv4().then(response => {
      console.log(response)
    })
    actuator().then(response => {
      console.log(response)
    })
    baidu().then(response => {
      console.log(response)
    })
  }
}
</script>

<style scoped>

</style>
