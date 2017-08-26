'use strict';
import angular from 'angular';

/** Style Dependency */
import '../assets/sass/style.scss';

/** Dependencies modules imports */
import areaBlu from './modules/todoApp/todoApp.module';

export default (() => {
  return angular.module('app', [
    areaBlu
  ])
})();
