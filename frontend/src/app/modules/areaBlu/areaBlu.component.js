import template from './areaBlu.html';

function controller() {
  const vm = this;

  console.log("here...");

  vm.hello = "Bem vindo";
};

controller.$inject = [];
export default {
  template,
  controller,
  controllerAs: 'vm',
  bindings: {
  }
}
