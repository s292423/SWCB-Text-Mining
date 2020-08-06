<template>

<div id="dash">
    <div class="main">
    <div class="sign">{{ sign }}</div>
    <div class="images">
        <img class="img" :src="myImage"/>
    </div>
    <div class="tags">
        <div class="tag" v-on:mouseover="mouseover('temple')">
        <div class="name"># 寺廟</div>
        </div>
        <div class="tag" v-on:mouseover="mouseover('street')">
        <div class="name"># 老街</div>
        </div>
        <div class="tag" v-on:mouseover="mouseover('sea')">
        <div class="name"># 海邊</div>
        </div>
        <div class="tag" v-on:mouseover="mouseover('sign')">
        <div class="name"># 人造景物</div>
        </div>
        <div class="tag" v-on:mouseover="mouseover('hot')">
        <div class="name"># 網紅</div>
        </div>
        <div class="tag" v-on:mouseover="mouseover('hit')">
        <div class="name"># 本月流行</div>
        </div>
    </div>
    </div>
</div>


</template>

<script>

export default {
  name: 'hashTag',
  data() {
    return {
      myImage: "https://img.theculturetrip.com/450x/wp-content/uploads/2015/11/5170574412_cc891c9188_z.jpg",
      sign : 'TEMPLE',
    }
  },methods: {
    mouseover:  function(name) {
      if (name == 'temple'){
        this.myImage = "https://img.theculturetrip.com/450x/wp-content/uploads/2015/11/5170574412_cc891c9188_z.jpg"
        this.sign = 'TEMPLE'
      }else if(name == 'street'){
        this.myImage = "http://cdn.tour-ntpc.com/site/a3be84a9-7283-40a3-b4c7-758bf39c7828/Content/Upload/Tour/679164c3-27fa-4220-a541-0e239339040b.jpg"
        this.sign = 'OLD STREET'
      }else if(name == 'sea'){
        this.myImage = "https://images.pexels.com/photos/457881/pexels-photo-457881.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        this.sign = 'SEA'
      }else if(name == 'sign'){
        this.myImage = "https://travel.yilan.tw/image/23820/1024x768"
        this.sign = 'SIGN'
      }else if(name == 'hot'){
        this.myImage = "https://cdn2.ettoday.net/images/3681/d3681246.jpg"
        this.sign = 'HOT'
      }else if(name == 'hit'){
        this.myImage = "http://www.funtime.com.tw/blog/wp-content/uploads/2017/08/10.png"
        this.sign = 'HIT'
      }
    }
  }
}
</script>

<style>
html, body {
  width: 100%; 
  height: 100%;
  background-color: rgba(240,240,240,0.5);
  margin: 0;
  position: absolute;
}

#dash {
  width: 90%; 
  height: 70%;
  display: flex;
  margin: 0 auto;
  position: relative;
  top: 15%;
  box-shadow: 2px 2px 5px #C6C6C6;
}
.main {
  width: 100%;
  height: 100%;
  background-color: white;
  display: flex;
}

.tag {
  height: 35px;
  background-color: white;
  margin: 5px;
  padding: 5px;
  border-radius: 5px;
  transition:  0.5s;
  cursor: pointer;
  box-shadow: 2px 2px 5px #C6C6C6;
  border: 1px solid #C6C6C6;
}
.tag:hover {
  transform: translatey(-5px);
  background-color: #C6C6C6;
/*   color: #C6C6C6; */
}
.tags {
  width: 100%;
  height: auto;
  flex: 1;
  padding: 20px 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  overflow-y: scroll;
}

.images {
/*   width: 500px; */
  height: auto;
  flex: 4;
  justify-content: center;
  align-items: center;
}
.img {
  width: 100%;
  height: 100%;
  display: block;
  background-size: cover;
}
.sign {
  font-size:50px;
  width: max-content;
  height: min-content;
  letter-spacing: 10px;
  position: absolute;
  bottom: 2%;
  left: 4%;
  color: white;
}
.name {
  width: max-content;
}

/* 
@media screen and (min-width: 1500px) and (max-width: 2000px) {
  #app {
    width: 40%; 
    height: 40%;
    display: flex;
    margin: 0 auto;
    position: relative;
    top: 25%;
    box-shadow: 2px 2px 5px #C6C6C6;
  }
  .sign {
    font-size:50px;
    width: max-content;
    height: min-content;
    letter-spacing: 10px;
    position: absolute;
    bottom: 2%;
    left: 4%;
    color: white;
  }
}

@media screen and (min-width: 100px) and  (max-width: 500px) {
  #app {
    width: 40%; 
    height: 40%;
    display: flex;
    margin: 0 auto;
    position: relative;
    top: 25%;
    box-shadow: 2px 2px 5px #C6C6C6;
  }
  .name {
    width: max-content;
    font-size: 5px;
  }
  .sign {
    font-size:30px;
    width: max-content;
    height: min-content;
    letter-spacing: 10px;
    position: absolute;
    bottom: 2%;
    left: 4%;
    color: white;
  }
} */
</style>
