<?php
$string = file_get_contents("./spotf.json");
$json_a = json_decode($string, true);
$featuress = array();
$featuress['type'] = "FeatureCollection";
$crs['type'] = "name";
$propertie['name'] = "urn:ogc:def:crs:OGC:1.3:CRS84";
$crs['properties'] = $propertie;
$featuress['crs'] = $crs;
$features = array();
foreach($json_a as $value){
    $feature = array();
    $properties = array();
    $geometry = array();
    $coordinates = array();
    array_push($coordinates, $value['x']);
    array_push($coordinates, $value['y']);
    $properties['C_x'] = $value['C'];
    // print_r($coordinates);
    $geometry['type'] = "Point";
    $geometry['coordinates'] = $coordinates;
    $feature['type'] = "Feature";
    $feature['properties'] = $properties;
    $feature['geometry'] = $geometry;
    array_push($features, $feature);
}
$featuress['features'] = $features;
print_r($featuress);
$fp = fopen('results.json', 'w');
fwrite($fp, json_encode($featuress));
fclose($fp);
?>