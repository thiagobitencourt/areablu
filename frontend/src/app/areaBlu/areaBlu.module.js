export default (() => {
  let map;

  return {
    initMap() {
      console.log("Mapa iniciado");
      let element = document.getElementById('map');

      console.log(element);

      map = new google.maps.Map(element, {
        center: {
          lat: -34.397, lng: 150.644
        },
        zoom: 8
      });

    }
  };
})();
