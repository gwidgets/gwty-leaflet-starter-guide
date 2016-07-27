package com.gwidgets.leaflet.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.gwidgets.api.leaflet.Circle;
import com.gwidgets.api.leaflet.L;
import com.gwidgets.api.leaflet.LatLng;
import com.gwidgets.api.leaflet.Map;
import com.gwidgets.api.leaflet.Polygon;
import com.gwidgets.api.leaflet.Rectangle;
import com.gwidgets.api.leaflet.elemental.Function;
import com.gwidgets.api.leaflet.options.PathOptions;
import com.gwidgets.api.leaflet.options.PolylineOptions;
import com.gwidgets.api.leaflet.options.PopupOptions;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtyLeafletStarter implements EntryPoint {

	public static final String MAP_URL = "https://api.mapbox.com/styles/v1/g-widgets/ciqy0cpax0012cfm8vx7ptrz9/tiles/256/{z}/{x}/{y}"
			+ "?access_token=pk.eyJ1IjoiZy13aWRnZXRzIiwiYSI6ImNpcXV5YzdyajAwNmRoeG0zbm80c3d4Y3IifQ.eyVA2x_DJG_bzDObhnjaTA";

	public boolean firstClickFlag = true;

	@Override
	public void onModuleLoad() {

		// We do not make use of options, so they are set null for now;
		final Map map = L.map("map", null);

		L.tileLayer(MAP_URL, null).addTo(map);

		map.setView(L.latLng(52.51, 13.40), 12, null);

		// click event

		map.on("click", new Function() {
			@Override
			public JavaScriptObject call(JavaScriptObject event) {
				if (firstClickFlag) {
					PopupOptions options = new PopupOptions();
					options.zoomAnimation = false;

					map.openPopup("Hello, Welcome to Berlin!", L.latLng(52.51, 13.40), options);
					firstClickFlag = false;
				}

				return null;
			}
		});

		PathOptions circleOptions = new PathOptions();
		circleOptions.fillColor = "#b35d20";
		circleOptions.color = "#f54e02";
		
		//creating a circle as Leaflet Js: L.circle

		Circle circle = L.circle(L.latLng(52.53, 13.33), 500, circleOptions);
		circle.addTo(map);

		circle.bindPopup("This area has cheap rent", null);

		PathOptions rectangleOptions = new PathOptions();
		rectangleOptions.fillColor = "#ddea09";
		rectangleOptions.color = "#ffff00";

		//creating a circle as Leaflet Js: L.rectangle
		Rectangle rectangle = L.rectangle(L.latLngBounds(L.latLng(52.50, 13.40), L.latLng(52.51, 13.42)),
				rectangleOptions);

		rectangle.addTo(map);

		rectangle.bindPopup("This area has good restaurants", null);
		
		//Polygon coordinates

		List<LatLng> coordinates = new ArrayList<LatLng>();

		coordinates.add(L.latLng(52.51225111854443, 13.376884460449219));
		coordinates.add(L.latLng(52.5095347703273, 13.347358703613281));
		coordinates.add(L.latLng(52.50535544522142, 13.336715698242188));
		coordinates.add(L.latLng(52.51392263399712, 13.344955444335938));
		coordinates.add(L.latLng(52.51831005956184, 13.369331359863281));

		LatLng[] coordinatesArray = (LatLng[]) coordinates.toArray();

		PolylineOptions polygonOptions = new PolylineOptions();
		polygonOptions.fillColor = "#008000";
		polygonOptions.color = "#1d921d";

		Polygon tiergartenPolygon = L.polygon(coordinatesArray, polygonOptions);

		tiergartenPolygon.addTo(map);

		tiergartenPolygon.bindPopup("This is Tiergarten: one of the best parks in Berlin", null);



	}

}
