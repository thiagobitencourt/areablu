'use strict';

/** Style Dependency */
import '../assets/sass/style.scss';
import API from './backend.api';

let map;
let currentLocation;
let markers = [];
let parkingCircle;
let markerCluster;
let defaultCircleRadius = 200;
let currentDestinPosition;
let polingInterval;

// Configure the modal.
$(document).ready(function(){
  // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
  $('.modal').modal({
    dismissible: true
  });
});

const maps = {
  getParkingPlaces(position) {
    return API.getParkingLocations(position, defaultCircleRadius)
      .then(result => {
        removeMarkers();
        if(markerCluster) {
          markerCluster.clearMarkers();
        }
        result.map(mark => {
          createMarker({ lat: parseFloat(mark.lat), lng: parseFloat(mark.lng) })
        });
        markerCluster = new MarkerClusterer(map, markers,
            {
              imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'
            }
        );
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
          }, error => {
            // Fallback to use a default user location
            currentLocation = { lat: -26.909057299999997, lng: -49.0738722 };
            resolve(currentLocation);
          });
      } else {
        reject("Navegador não puporta obter a sua localização atual");
      }
    });
  },
  // Remove markers from map
  removeMarkers() {
    markers.forEach(mark => {
      mark.setMap(null);
      mark.setVisible(false);
    });
    markers = [];
  },
  // Center the map in a given position
  centerMap(position) {
    map.setCenter(position);
  },

  ocuparVaga({ vaga, ...carro}) {
      console.log(vaga, carro);
      // Aqui chama a api de vincular um carro a uma vaga.
  },
  // Método chamado ao clicar em uma vaga
  clickMarker() {
    let { position: vaga } = this;
    vaga.id = this.id;

    let car = {
      vaga,
      id: 658,
      placa: 'ABC-1234',
      modelo: 'Mustang GT'
    };
    let modal = $('#modal1');
    // Configura e abre a modal
    $('#car-info').html(`<b>Carro:</b> ${car.modelo}`);
    $('#car-plate').html(`<b>Placa:</b> ${car.placa}`);
    $('#occupy-click').click(ocuparVaga.bind(null, car));
    modal.modal('open');
  },
  // Cria um novo marcado no mapa, centraliza o mapa no marcado se for solicitado
  createMarker(position, center) {
    // let icon = center ? undefined : { path: google.maps.SymbolPath.CIRCLE, strokeColor: "#306f11", fillColor: "#47e223", scale: 5 };
    let marker = new google.maps.Marker({ map, position });
    marker.id = position.id;

    markers.push(marker);
    marker.addListener('click', clickMarker);

    if(center) {
      centerMap(position);
      map.setZoom(17);
      marker.setVisible(false);
    }
  },

  createCircle(position, radius = defaultCircleRadius) {
    parkingCircle = new google.maps.Circle({
       strokeColor: '#4c7fc4',
       strokeOpacity: 0.8,
       strokeWeight: 2,
       fillColor: '#4c7fc4',
       fillOpacity: 0.35,
       map: map,
       center: position,
       radius: radius
     });
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

    let place = places[0];
    if (!place.geometry) {
      console.log("Returned place contains no geometry");
      return;
    }

     let position = {
       lat: place.geometry.location.lat(),
       lng: place.geometry.location.lng()
     }
     centerMap(position);
     map.setZoom(17);

     if(parkingCircle) {
       parkingCircle.setMap(null);
     }

     createCircle(position);
     getParkingPlaces(position);
     currentDestinPosition = position;
     document.getElementById('input-destino').value = "";
  },
  // Método de inicialização
  initMap() {
    getLocation()
      .then(center => {
      map = new google.maps.Map(document.getElementById('map'), {
        center: currentLocation,
        zoom: 14
      });
      currentLocation.id = 654;
      currentDestinPosition = currentLocation;
      // Centraliza o mapa na posição inicial do usuário
      createMarker(currentLocation);
      centerMap(currentLocation);
      createCircle(currentLocation);
      // getParkingPlaces(currentLocation);
      map.setZoom(17);

      let input = document.getElementById('input-destino');
      let searchBox = new google.maps.places.SearchBox(input);

      map.addListener('bounds_changed', () => {
        searchBox.setBounds(map.getBounds());
      });
      searchBox.addListener('places_changed', changeDetinLocation.bind(searchBox));
      initializePoling();
    });
  },

  initializePoling() {
    polingInterval = setInterval(() => {
      getParkingPlaces(currentDestinPosition);
    }, 3000);
  }
}

Object.assign(window, maps);
$('#logo-img').attr("src", `data:image/png;base64,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`
);
