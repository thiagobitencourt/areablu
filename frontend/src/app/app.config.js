function configApp($stateProvider, $urlRouterProvider) {
  $stateProvider.state('userlist', {
    url: '/',
    component: 'areaBlu'
  });

  $urlRouterProvider.otherwise("/");
}
configApp.$inject = ['$stateProvider', '$urlRouterProvider'];

export default configApp;
