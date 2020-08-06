<template>
    <div class="hotspot">
      <div class="leftspot">
        <h3>熱門景點</h3>
        <div v-for="(item, index) in hot">
            <span>{{item.name}}</span>
        </div>
      </div>
      <div class="rightspot">
        <h3>隱藏景點</h3>
        <div v-for="(item, index) in hid">
            <span>{{item.name}}</span>
        </div>
      </div>
    </div>
</template>
<script>

import Event from '../bus.js'
export default {
  name: 'show',
  data() {
      return {
          hot: [],
          hid: [],
      }
  },
  created() {
      Event.$on('getinfo', (data)=>{
          // console.log(data);
          for(var j=0; j<data[0].length;j++){
            this.hot.push({name:data[0][j].get("spot")}); //熱門景點
          }
          for(var i=0; i<data[1].length;i++){
            this.hid.push({name:data[1][i].get("spot")}); //熱門景點
          }
      }),
      Event.$on('delgetinfo', (data)=>{
        console.log(data);


        for(var i=0;i<this.hid.length;i++){
          for(var j=0; j<data.length;j++){
            try{
              if(this.hid[i].name==data[j]){
                console.log(this.hot[i].name+" "+data[j]);
                this.hid.splice(i,1);
              }
            }catch(e){
            }
          }
        }

        for(var i=0;i<this.hot.length;i++){
          for(var j=0; j<data.length;j++){
            try{
              if(this.hot[i].name==data[j]){
                console.log(this.hot[i].name+" "+data[j]);
                this.hot.splice(i,1);
              }
            }catch(e){
            }
          }
        }
      })
  }
}
</script>
<style>
.hotspot {
  display: flex;
  flex-direction: row;
  height: 100%;
}
.leftspot {
  width:100%;
  flex:2;
  height: 100%;
  /* align-items: center; */
  text-align: center;
  background-color: pink;
  overflow-y: scroll;
}
.rightspot {
  width:100%;
  flex:2;
  height: 100%;
  /* align-items: center; */
  text-align: center;
  background-color: orange;
  overflow-y: scroll;
}


</style>
