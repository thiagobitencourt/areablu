import template from './areaBlu.html';

function controller(uiGmapGoogleMapApi) {
  const vm = this;
  vm.hello = "Bem vindo";

  vm.map = {
    center: {
      latitude: 40.1451,
      longitude: -99.6680
    },
    zoom: 8,
    searchbox: {
      template:'searchbox.tpl.html',
      events:{
        places_changed: function (searchBox) {}
      }
    },
    options: {
      scrollwheel: false
    }
  };

  uiGmapGoogleMapApi.then(function(maps) {
    maps.visualRefresh = true;
  });
};

controller.$inject = ['uiGmapGoogleMapApi'];
export default {
  template,
  controller,
  controllerAs: 'vm',
  bindings: {
  }
}
