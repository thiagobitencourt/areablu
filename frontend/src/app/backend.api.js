export default (function() {
  const API_URL = "http://10.1.37.38:8080";

  const API = {
    getParkingLocations({ lat, lng }, distance) {
      return new Promise((resolve, reject) => {
        $.ajax({
          'type': 'GET',
          'url': API_URL + `/vaga`,
          'contentType': 'application/json',
          'data': { lat, lng, distance },
          'dataType': 'json',
          'success': resolve
        });
      });
    },

    occupyParkingSpot(vagaId, placa) {
      return new Promise((resolve, reject) => {
        $.post(API_URL + `/vaga/ocupar`, { vagaId, placa }, resolve);
      });
    }
  };

  return API;
})();
