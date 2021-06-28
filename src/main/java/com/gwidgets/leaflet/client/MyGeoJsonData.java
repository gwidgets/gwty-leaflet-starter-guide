package com.gwidgets.leaflet.client;

import elemental2.core.JsObject;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

import static jsinterop.annotations.JsPackage.GLOBAL;

@JsType(isNative=true, namespace=GLOBAL, name="Object")
public class MyGeoJsonData extends JsObject {

  @JsProperty
  String type;

  @JsProperty
  Properties properties;

  @JsProperty
  Geometry geometry;

  //Constructor needs to be protected and zero-arguments
  protected MyGeoJsonData() { }

  @JsType(isNative=true, namespace=GLOBAL, name="Object")
  static class Geometry {

    @JsProperty
    String type;

    @JsProperty
    double[] coordinates;

    protected Geometry() {
    }
  }

  @JsType(isNative=true, namespace=GLOBAL, name="Object")
  static class Properties {

    @JsProperty
    String name;

    @JsProperty
    String amenity;

    @JsProperty
    String popUpContent;

    protected Properties() {
    }

  }
}
