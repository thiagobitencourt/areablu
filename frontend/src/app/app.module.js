'use strict';
import angular from 'angular';
import angularRouter from '@uirouter/angularjs'

/** Style Dependency */
import '../assets/sass/style.scss';

/** Dependencies modules imports */
import appConfig from './app.config';
import areaBlu from './modules/areaBlu/areaBlu.module';

angular.module('app', [
  angularRouter,
  areaBlu
]).config(appConfig);


console.log(angular.module('app'));
