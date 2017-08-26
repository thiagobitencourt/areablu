export default (function() {
  const API_URL = "http://10.1.37.38:8080";

  const API = {
    getParkingLocations(reference, radius) {
      return new Promise((resolve, reject) => {
        $.get(API_URL + '/vaga', resolve);
      })
    }
  };

  return API;
})();
