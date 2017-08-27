package com.gwidgets.leaflet.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.gwidgets.api.leaflet.Circle;
import com.gwidgets.api.leaflet.L;
import com.gwidgets.api.leaflet.LatLng;
import com.gwidgets.api.leaflet.Map;
import com.gwidgets.api.leaflet.Polygon;
import com.gwidgets.api.leaflet.Rectangle;
import com.gwidgets.api.leaflet.events.Event;
import com.gwidgets.api.leaflet.events.EventCallback;
import com.gwidgets.api.leaflet.events.EventTypes;
import com.gwidgets.api.leaflet.events.MouseEvent;
import com.gwidgets.api.leaflet.options.CircleOptions;
import com.gwidgets.api.leaflet.options.MapOptions;
import com.gwidgets.api.leaflet.options.PathOptions;
import com.gwidgets.api.leaflet.options.PolylineOptions;
import com.gwidgets.api.leaflet.options.PopupOptions;
import com.gwidgets.api.leaflet.options.TileLayerOptions;
import com.gwidgets.api.leaflet.utils.LeafletResources;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtyLeafletStarter implements EntryPoint {

	public static final String MAP_URL = "https://api.mapbox.com/styles/v1/g-widgets/ciqy0cpax0012cfm8vx7ptrz9/tiles/256/{z}/{x}/{y}"
			+ "?access_token=pk.eyJ1IjoiZy13aWRnZXRzIiwiYSI6ImNpcXV5YzdyajAwNmRoeG0zbm80c3d4Y3IifQ.eyVA2x_DJG_bzDObhnjaTA";

	public boolean firstClickFlag = true;

	@Override
	public void onModuleLoad() {
		//true for unminified, false for minified
		LeafletResources.whenReady(true, 
				e -> 
		             {	 
		//Map options required values: center point, zoom, and a min zoom 
	    MapOptions options = new MapOptions.Builder(L.latLng(52.485611, 13.416460), 11.0, 10.0)
	    		                       .maxZoom(400.0).build();
		
		final Map map = L.map("map", options);
			
		//TileLayerOptions tloptions = new TileLayerOptions.Builder().minZoom(1).maxZoom(17).build();
		L.tileLayer(MAP_URL, null).addTo(map);	

		map.on(EventTypes.MapEvents.CLICK, new EventCallback<MouseEvent>() {
			@Override
			public void call(MouseEvent event) {
				GWT.log("Mouse event "+event.getLayerPoint().x);
				
				if (firstClickFlag) {
					PopupOptions options = new PopupOptions.Builder()
							               .build();
					map.openPopup("Hello, Welcome to Berlin!", L.latLng(52.51, 13.40), options);
					firstClickFlag = false;
				}

			}
		});
		
		map.on(EventTypes.MapEvents.ZOOMSTART, new EventCallback<Event>() {
			@Override
			public void call(Event event) {
				// TODO Auto-generated method stub
				GWT.log(map.getZoom()+"");
			}
		});

		CircleOptions circleOptions = new CircleOptions.Builder()
				                    .fillColor("#b35d20")
				                    .color("#f54e02")
				                    .radius(500)
				                     .build();
		
		//creating a circle as Leaflet Js: L.circle

		Circle circle = L.circle(L.latLng(52.53, 13.33), circleOptions);
		circle.addTo(map);
		//circle.setRadius(500);
		
		GWT.log("radius " + circle.getRadius());	

		circle.bindPopup("This area has cheap rent", null);

		PathOptions rectangleOptions = new PathOptions.Builder()
				                          .fillColor("#ddea09")
				                          .color("#ffff00")
				                          .build();

		//creating a circle as Leaflet Js: L.rectangle
		Rectangle rectangle = L.rectangle(L.latLngBounds(L.latLng(52.50, 13.40), L.latLng(52.51, 13.42)),
				rectangleOptions);

		rectangle.addTo(map);

		rectangle.bindPopup("This area has good restaurants", null);
		rectangle.bindTooltip("kreuzberg", null);
		
		//Polygon coordinates

		List<LatLng> coordinates = new ArrayList<LatLng>();

		coordinates.add(L.latLng(52.51225111854443, 13.376884460449219));
		coordinates.add(L.latLng(52.5095347703273, 13.347358703613281));
		coordinates.add(L.latLng(52.50535544522142, 13.336715698242188));
		coordinates.add(L.latLng(52.51392263399712, 13.344955444335938));
		coordinates.add(L.latLng(52.51831005956184, 13.369331359863281));

		LatLng[] coordinatesArray = (LatLng[]) coordinates.toArray();
		
		LatLng[][] coordinatesPolygon = {coordinatesArray};

		PolylineOptions polygonOptions = new PolylineOptions.Builder()
				                         .fillColor("#008000")
				                         .color("#1d921d")
				                         .build();

		Polygon tiergartenPolygon = L.polygon(coordinatesPolygon, polygonOptions);

		tiergartenPolygon.addTo(map);

		tiergartenPolygon.bindPopup("This is Tiergarten: one of the best parks in Berlin", null);
		tiergartenPolygon.bindTooltip("park", null);
		
		            	 return null;}
				);
	}
}