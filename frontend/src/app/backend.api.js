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
        $.ajax({
          url: API_URL + '/vaga/ocupar',
          type:"POST",
          data: JSON.stringify({vagaId: vagaId, placa: placa}),
          contentType:"application/json; charset=utf-8",
          dataType:"json"
        })
      });

    }
  };

  return API;
})();
