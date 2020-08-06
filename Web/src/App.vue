<template>
<div id="app">
  <navBar/>
  <div class="inner">
    <div class="left">
      <div class="leftTop">
        <div class="TownChoose">
          <h3>請選擇鄉鎮市區：</h3>
          <my-select v-model="townIndex" :data-source="towns"></my-select>
          <my-select v-model="areaIndex" :data-source="districts"></my-select>
          <button type="button" class="btn btn-success" v-on:click="changeMap(towns[townIndex] + districts[areaIndex])">送出</button>
        </div>
        <hr>
        <div class="CheckBoxChoose">
          <div class="checkboxes">
            <h3>旅遊區選擇：</h3>
            <checkBox/>
          </div>
          <div class="show">
            <show/>
          </div>
        </div>
      </div>
      <div class="leftBottom">
        <hashTag/>
      </div>
    </div>
    <div class="center">
      <div class="centerTop">
        <div class="centerleft">
          <img class="logo" src="./assets/logo3.png" alt="logo">
        <!-- </div>
        <div class="centerright">
          <img class="logo" src="./assets/logo1.png" alt="logo"> -->

        </div>
      </div>
      <div class="centerBottom">
        <olmap :btnName="btnName" on:="changehot"/>
      </div>
      <div class="centerEnd">
        <div id="knowlabel" class='legend-scale'>
            <!-- <div class='legend-title'>地址數量</div> -->
            <ul class='legend-labels'>
                <li id="lv1"><span style='background:rgba(255,0,0, 1);'></span><h>>3000</h></li>
                <li id="lv2"><span style='background:rgba(247,80,0,1);'></span><h>800~3000</h></li>
                <li id="lv3"><span style='background:rgba(255,146,36, 1);'></span><h>100~800</h></li>
                <li id="lv4"><span style='background:rgba(255,211,6, 1);'></span><h>50~100</h></li>
                <li id="lv5"><span style='background:rgba(130,217,0, 1);'></span><h><50</h></li>
            </ul>
        </div>
      </div>
    </div>
    <div class="right">
      <div class="title">
        <h2>行程規劃</h2>
      </div>
      <div class="content">
        <div class="card todolist" v-for="(value, index) in lists">
          <div class="ti">{{value}}</div>
          <div class="btns">
            <div class="btn" v-on:click="deletNote(index)">刪除</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import Event from './bus.js';
import boot from './assets/bootstrap.min.css';
import olmap from './components/olmap.vue';
import mySelect from './components/my-select.vue';
import data from './assets/county.json';
import checkBox from './components/checkBox';
import hashTag from './components/hashTag';
import navBar from './components/navBar';
import show from './components/show';
// import info from './components/info'

let cities = data;

let towns = () => cities.map(x => x.name);

let districts = function() {
  return cities[this.townIndex].districts.map(x => x.name);
};

let zip = function() {
  return cities[this.townIndex].districts[this.areaIndex].zip;
};

let townIndex = function() {
  this.areaIndex = 0;
};

export default {
  name: 'app',
  data() {
    return {
      townIndex: 0,
      areaIndex: 0,
      btnName: '',
      lists: [],
    }
  },
  components: {
    olmap,
    mySelect,
    checkBox,
    hashTag,
    navBar,
    show,
  }, computed: {
    towns,
    districts,
    zip,
  },
  watch: {
    townIndex,
  }, methods: {
    changeMap: function(mapcode){
      this.btnName = mapcode
    },
    // changehot: function(data) {
    //   console.log('success');
    //   this.lists.push(data);
    // },
    deletNote: function(index) {
      this.lists.splice(index, 1)
    },
  },created() {
    Event.$on('changehot', (data)=>{
      console.log(data);
      this.lists.push(data);
    })
  }
}
</script>

<style>
*{padding:0; margin:0;}
html,body{
  height: 100%;
  width: 100%;
}
hr {
  margin: 0;
}
#app {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
}
.inner {
  display: flex;
  width: 100%;
  height: 100%;
}
.left {
  flex: 4;
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  background-color: white;
}

.leftTop {
  height:100%;
  margin: 5px;
  background-color: white;
  /* box-shadow: 2px 2px 5px #C6C6C6; */
  border-radius: 5px;
  border: 1px solid #e9ebee;
  display: flex;
  flex-direction: column;
}

.TownChoose {
  margin: 0 auto;
  margin-top: 5%;
  flex:1;
}

.CheckBoxChoose {
  flex:3;
  height: 100%;
  display: flex;
  flex-direction: row;
  padding: 10px;
}
.checkboxes {
  flex:2;
  height: 100%;
  overflow-y: scroll;
}
.show {
  flex:7;
  height: 100%;
}
.leftBottom {
  height:100%;
  background-color: white;
}

.center {
  flex: 4;
  display: flex;
  height: 100%;
  width: 100%;
  flex-direction: column;
  background-color: white;
}

.centerTop {
  flex:3;
  height: 100%;
  margin: 0 10px;
  /* display: flex; */
  /* flex-direction: row; */
  /* background-color: yellow; */
}
.centerleft {
  width: 100%;
  height: 100%;
  flex:2;
  /* margin-right:3px; */
  text-align: center;
}
.centerright {
  width: 100%;
  height: 100%;
  flex:2;
  margin-left:3px;
  text-align: center;
}
.logo{
  width: 100%; 
  /* max-width:100%;  */
  max-height: 100%;
}

.centerBottom {
  flex: 15;
  height: 100%;
  /* width: 100%; */
  margin: 10px;
}
.centerEnd {
  flex:1;
  width: 100%;
}

.right {
  flex: 3;
  /* background-color: black; */
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
}

.title {
  flex:2;
  /* width: 100%; */
  height:100%;
  text-align: center;
  background-color: #FED309;
}
.title h2 {
  margin-top: 9%;
}
.content {
  flex:12;
  width: 100%;
  height:100%;
  text-align: center;
}
.legend-title{
  font-size: 15px;
  font-weight: bold;
  text-align: center;
  font-family:'Microsoft JhengHei',Arial;
}
.legend-scale{
  background-color:white;
  /* opacity: 0.8; */
  align-self: center;
}
/* .legend-labels{
  padding-left: 0px;
  margin-bottom: 0px;
} */
.legend-scale ul li {
  display: block;
  float: left;
  width: 20%;
  text-align: center;
  font-size: 60%;
  list-style: none;
  z-index: 100;
  font-family:'Microsoft JhengHei',Arial;
  font-weight: bold;
}
ul.legend-labels li span {
  display: block;
  float: left;
  height: 15px;
  width: 100%;
  z-index:100;
}
.hide{
  display: none !important;
}

</style>