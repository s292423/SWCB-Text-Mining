<template>
    
    <div id="map" ref="rootmap" >
      <div id="el-dialog" class="hide">
        <div class="up">
          <img src="../assets/close.png" style="width: 30px;float: right;cursor: pointer;" title="关闭弹窗" v-on:click="close" class="close"/>
          <img class="hold" src="https://img.theculturetrip.com/450x/wp-content/uploads/2015/11/5170574412_cc891c9188_z.jpg" alt="temple">
        </div>
        <div class="down">
          <div class="infoname"><h3>{{infoname}}</h3></div>
          <div class="infohot"><h3>45</h3></div>
          <div class="nowhot">當前熱門程度</div>
          <button type="button" class="btn btn-info posi" v-on:click="test">加入行程</button>
        </div>
      </div>
    </div>
</template>

<script>

import Event from '../bus.js';
import "ol/ol.css";
import { Map, View } from "ol";
import {Fill, Stroke, Style, Text} from 'ol/style.js';
import {Cluster, OSM, Vector as VectorSource} from 'ol/source.js';
import {Tile as TileLayer, Vector as VectorLayer} from 'ol/layer.js';
import AnimatedCluster from 'ol-ext/layer/AnimatedCluster';
import GeoJSON from 'ol/format/GeoJSON';
import data from '../assets/100_103_mapcode.json';
import spot from '../assets/spot.json';
import spoth from '../assets/spotf.json';
import result from '../assets/results.json';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import Circle from 'ol/style/Circle';


export default {
  props: ['btnName'],  
  data() {
    return {
      map: null,
      heatData: data,
      vSource3: null,
      infoname: '月老廟',

    };
  },methods: {
    close: function(){
      console.log("close");
      // $("#el-dialog").addClass("hide");
      const el = document.getElementById("el-dialog");
      el.classList.add('hide');
    },test: function(){
      Event.$emit('changehot', this.infoname);
    }
  },
  mounted() {
    // $("#el-dialog").addClass("hide");
    // $(".close").click(function(event) {
    //     $("#el-dialog").addClass("hide");
    // });
    var mapcontainer = this.$refs.rootmap;

    var osm = new TileLayer({
        source: new OSM()
    });
    this.vSource3 = new VectorSource({
        features: (new GeoJSON()).readFeatures(this.heatData)
    });	
    var vectorLayer3 = new VectorLayer({
        source: this.vSource3,
        style: new Style({
            fill: new Fill({
                color: [255,255,255, 0]
            }),
            stroke: new Stroke({
                color: '#888888',width: 1
            })
        })
    });
    this.map = new Map({
      target: "map",
      layers: [
        osm,vectorLayer3,
      ],
      view: new View({
        projection: "EPSG:4326", 
        center: [121, 23.6], 
        zoom: 8.2
      })
    });
  },watch: {
    btnName: function () {
      Event.$emit('post', spot['features']);

      var features = this.vSource3.getFeatures();
      for (var i = 0;i<features.length;i++) {	
        if(features[i].get("mapcode")==this.btnName){
          var Extent = features[i].getGeometry().getExtent();
          this.map.getView().fit(Extent, this.map.getSize());
          var style = new Style({fill:new Fill({color: [255, 255, 255, 0]}),stroke:new Stroke({color: '#7cb5ec',width: 3})});
          features[i].setStyle(style);
        }else {
          var style = new Style({fill:new Fill({color: [255, 255, 255, 0.5]}),stroke:new Stroke({color: '#888888',width: 1})});
				  features[i].setStyle(style);
        }
      }
    }
  },created() {
    Event.$on('PostCheckValue', (data)=>{
      console.log(data);
      var ts = [];
      var t = new GeoJSON().readFeatures(spot);
      for (var i = 0;i<t.length;i++) {	
        if(t[i].get("C_x") == data){
          ts.push(t[i]);
        }
      }
      var spotFeature = new VectorSource({
        features: ts
      });
      var spotLayer = new VectorLayer({
          source: spotFeature,
          style: new Style({
            image: new Circle({
              radius: 5,
              stroke: new Stroke({
                color: 'black'
              }),
              fill: new Fill({
                color: 'pink'
              })
            })
          })
      });
      var th = [];
      var h = new GeoJSON().readFeatures(spoth);
      for (var i = 0;i<h.length;i++) {	
        if(h[i].get("C_x") == data){
          th.push(h[i]);
        }
      }
      var spothFeature = new VectorSource({
        features: th
      });
      var spothLayer = new VectorLayer({
          source: spothFeature,
          style: new Style({
            image: new Circle({
              radius: 5,
              stroke: new Stroke({
                color: 'black'
              }),
              fill: new Fill({
                color: 'orange'
              })
            })
          })
      });


      Event.$emit('getinfo', [ts,th]);
      var tc = [];
      var c = new GeoJSON().readFeatures(result);
      for (var i = 0;i<c.length;i++) {	
        if(c[i].get("C_x") == data){
          tc.push(c[i]);
        }
      }
      
      var clusterFeature = new VectorSource({
          features: tc
      });
      var clusterSource = new Cluster({
        distance: 100,
        source: clusterFeature
      });
      var styleCache = {};
      var clusters = new AnimatedCluster({
        source: clusterSource,
        animationDuration: 700,
        style: function (feature) {
            var size = feature.get('features').length;
                var style = styleCache[size];
                if (!style){	
                    var color = size>3000 ? "255,0,0" : size>800 ? "247,80,0" : size>100 ? "255,146,36" : size>50 ? "255,211,6": "130,217,0";
                    var radius = Math.max(8, Math.min(8*0.75, 20));
                    style = styleCache[size] = new Style(
                        {	image: new Circle(
                            {	radius: radius,
                                stroke: new Stroke(
                                {	color: "rgba("+color+",0.5)", 
                                    width: 15 ,
                                    //lineDash: dash,
                                    lineCap: "butt"
                                }),
                                fill: new Fill(
                                {	color:"rgba("+color+",1)"
                                })
                            }),
                            text: new Text(
                            {	text: size.toString(),
                                fill: new Fill(
                                {	color: '#fff'
                                })
                            })
                        });
                }
            return [style];
        }
      });

      clusters.set('name', data+'clusters');
      spotLayer.set('name', data+'spotLayer');
      spothLayer.set('name', data+'spothLayer');
      this.map.addLayer(clusters);
      this.map.addLayer(spotLayer);
      this.map.addLayer(spothLayer);

      this.map.on('click', function (e) {
        console.log("apple");
        const el = document.getElementById("el-dialog");
        // if (addRemoveClass === 'addClass') {
        //   el.classList.add(className);
        // } else {
          el.classList.remove('hide');
        // }
      });
    })
    Event.$on('DelCheckValue', (data)=>{
      var layersToRemove = [];
      var returnToRemove = [];
      this.map.getLayers().forEach(function (layer) {
          if (layer.get('name') != undefined && (layer.get('name') === data+'clusters'|| layer.get('name') === data+'spotLayer' || layer.get('name') === data+'spothLayer')) {
              layersToRemove.push(layer);
          }
          if (layer.get('name') != undefined && (layer.get('name') === data+'spotLayer' || layer.get('name') === data+'spothLayer')) {
              returnToRemove.push(layer);
          }
      });

      var len = layersToRemove.length;
      for(var i = 0; i < len; i++) {
          this.map.removeLayer(layersToRemove[i]);
      }
      var u = [];
      for(var i = 0; i < returnToRemove.length; i++) {
        for (var j=0; j< returnToRemove[i].getSource().getFeatures().length; j++){
            u.push(returnToRemove[i].getSource().getFeatures()[j]['values_']['spot']);
        }
      }
      Event.$emit('delgetinfo', u);
    })
  }
};
</script>

<style>
#map {
  height:100%;
}
/*隐藏ol的一些自带元素*/
.ol-attribution,.ol-zoom { display: none;}
#el-dialog {
  position: absolute;
  bottom: 8%;
  right:2%;
  width: 23%;
  height: 300px;
  background-color: #e9ebee;
  border: 1px solid #C6C6C6;
  border-radius: 5px;
  box-shadow: 2px 2px 5px #C6C6C6;
  display: flex;
  flex-direction: column;
}
.up {
  flex:2;
  height:100%;
  width: 100%;
}
.hold {
  height:100%;
  width: 100%;
}
.close {
  position: absolute;
  top: 2px;
  right: 2px;
}
.down {
  display: flex;
  flex-direction: column;
  flex:2;
  height:100%;
  width: 100%;
}
.infoname {
  flex: 2;
  height:100%;
  text-align: center;
}
.infohot {
  flex: 2;
  height:100%;
  text-align: center;
}
.nowhot {
  flex: 2;
  height:100%;
  text-align: center;
}

</style>