import template from './areaBlu.html';

function controller() {
  const vm = this;

  vm.hello = "Bem vindo";
};
areaBluComponent.$inject = [];
export default {
  template,
  controller,
  controllerAs: 'vm'
};
