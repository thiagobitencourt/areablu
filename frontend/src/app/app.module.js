'use strict';

/** Style Dependency */
import '../assets/sass/style.scss';
import API from './backend.api';

let map;
let currentLocation;
let markers = [];
let parkingCircle;

const maps = {
  getParkingPlaces(position) {
    return API.getParkingLocations(position)
      .then(result => {
        result.map(mark => {
          createMarker({ lat: parseFloat(mark.lat), lng: parseFloat(mark.lng) })
        });
      });
  },

  getLocation(callback) {
    return new Promise((resolve, reject) => {
      if (navigator.geolocation) {
          currentLocation = navigator.geolocation
            .getCurrentPosition(position => {
              var lat = position.coords.latitude;
              var lng = position.coords.longitude;
              currentLocation = { lat, lng };
              resolve(currentLocation);
          });
      } else {
        reject("Navegador não puporta obter a sua localização atual");
      }
    });
  },
  // Remove markers from map
  removeMarkers() {
    markers.forEach(mark => mark.setMap(null));
  },
  // Center the map in a given position
  centerMap(position) {
    map.setCenter(position);
  },
  // Cria um novo marcado no mapa, centraliza o mapa no marcado se for solicitado
  createMarker(position, center) {
    let icon = center ? undefined : { path: google.maps.SymbolPath.CIRCLE, strokeColor: "#306f11", fillColor: "#47e223", scale: 5 };
    let marker = new google.maps.Marker({ map, position, icon });
    markers.push(marker);

    if(center) {
      centerMap(position);
      map.setZoom(17);
    }
  },
  // Método executado quando o usuário seleciona um novo local, após uma busca
  changeDetinLocation() {
    var places = this.getPlaces();

    if (places.length == 0) {
        return;
      }

    // Clear out the old markers.
    markers.forEach(function(marker) {
      marker.setMap(null);
    });
    markers = [];

    // For each place, get the icon, name and location.
     places.forEach(function(place) {
       if (!place.geometry) {
         console.log("Returned place contains no geometry");
         return;
       }

       let position = {
         lat: place.geometry.location.lat(),
         lng: place.geometry.location.lng()
       }
       createMarker(position, true);

       if(parkingCircle) {
         parkingCircle.setMap(null);
       }
       parkingCircle = new google.maps.Circle({
          strokeColor: '#4c7fc4',
          strokeOpacity: 0.8,
          strokeWeight: 2,
          fillColor: '#4c7fc4',
          fillOpacity: 0.35,
          map: map,
          center: position,
          radius: 200
        });

       getParkingPlaces(position);
       document.getElementById('input-destino').value = "";
     });
  },
  // Método de inicialização
  initMap() {
    getLocation()
      .then(center => {
      map = new google.maps.Map(document.getElementById('map'), {
        center: currentLocation,
        zoom: 14
      });
      // Centraliza o mapa na posição inicial do usuário
      centerMap(currentLocation);

      let input = document.getElementById('input-destino');
      let searchBox = new google.maps.places.SearchBox(input);

      map.addListener('bounds_changed', () => {
        searchBox.setBounds(map.getBounds());
      });
      searchBox.addListener('places_changed', changeDetinLocation.bind(searchBox));
    });
  }
}

Object.assign(window, maps);
