/**
 * Created by cicidi on 2/25/2017.
 */
function initMap() {

    // Create a new StyledMapType object, passing it an array of styles,
    // and the name to be displayed on the map type control.
    var styledMapType = new google.maps.StyledMapType(
        [{
            "elementType": "geometry",
            "stylers": [{"hue": "#ff4400"}, {"saturation": -68}, {"lightness": -4}, {"gamma": 0.72}]
        }, {"featureType": "road", "elementType": "labels.icon"}, {
            "featureType": "landscape.man_made",
            "elementType": "geometry",
            "stylers": [{"hue": "#0077ff"}, {"gamma": 3.1}]
        }, {
            "featureType": "water",
            "stylers": [{"hue": "#00ccff"}, {"gamma": 0.44}, {"saturation": -33}]
        }, {"featureType": "poi.park", "stylers": [{"hue": "#44ff00"}, {"saturation": -23}]}, {
            "featureType": "water",
            "elementType": "labels.text.fill",
            "stylers": [{"hue": "#007fff"}, {"gamma": 0.77}, {"saturation": 65}, {"lightness": 99}]
        }, {
            "featureType": "water",
            "elementType": "labels.text.stroke",
            "stylers": [{"gamma": 0.11}, {"weight": 5.6}, {"saturation": 99}, {"hue": "#0091ff"}, {"lightness": -86}]
        }, {
            "featureType": "transit.line",
            "elementType": "geometry",
            "stylers": [{"lightness": -48}, {"hue": "#ff5e00"}, {"gamma": 1.2}, {"saturation": -23}]
        }, {
            "featureType": "transit",
            "elementType": "labels.text.stroke",
            "stylers": [{"saturation": -64}, {"hue": "#ff9100"}, {"lightness": 16}, {"gamma": 0.47}, {"weight": 2.7}]
        }],
        {name: 'Styled Map'});

    // Create a map object, and include the MapTypeId to add
    // to the map type control.
    var map = new google.maps.Map(document.getElementById('googleMap'), {
        center: {lat: 37.695479, lng: -121.921816},
        zoom: 11,
        mapTypeControlOptions: {
            mapTypeIds: ['styled_map', 'roadmap', 'satellite', 'hybrid', 'terrain'
            ]
        }
    });

    //Associate the styled map with the MapTypeId and set it to display.
    map.mapTypes.set('styled_map', styledMapType);
    map.setMapTypeId('styled_map');
    var jsonStr = $("#googleMap").attr("geo-data");
    var hashMap = JSON.parse(jsonStr);
    addMarkerList(map, hashMap);
}

function addMarker(googleMap, lat, long, message, setCenter) {

    var myLatlng = new google.maps.LatLng(lat, long);
    var mapOptions = {
        zoom: 13,
        center: myLatlng
    }
    if (!googleMap)
        googleMap = new google.maps.Map(document.getElementById("googleMap"), mapOptions);

    var marker = new google.maps.Marker({
        position: myLatlng,
        title: message
    });
    if (setCenter) {
        if (googleMap) {
            //googleMap.setCenter({lat: lat, lng: long})
            //googleMap.setCenter(new google.maps.LatLng(lat, long));
            //googleMap.setCenter(new google.maps.LatLng(-34, 151));
            var latlng = new google.maps.LatLng(lat, long);
            googleMap.setCenter(latlng);

        }
    }
// To add the marker to the map, call setMap();
    marker.setMap(googleMap);
}
function addMarkerList(googleMap, geoData) {
    var index = 0;
    var setCenter;
    for (var key in geoData) {
        if (index == 0)
            setCenter = true;
        if (geoData.hasOwnProperty(key)) {
            var geoMetry = JSON.parse(geoData[key]);

            addMarker(googleMap, geoMetry.location.lat, geoMetry.location.lng, key, setCenter);
            index++;
            setCenter = false;
        }

    }
}