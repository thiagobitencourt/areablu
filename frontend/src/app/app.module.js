'use strict';
import angular from 'angular';
import angularRouter from '@uirouter/angularjs'

import './components/angular-simple-logger/angular-simple-logger';
import 'angular-google-maps/dist/angular-google-maps';

/** Style Dependency */
import '../assets/sass/style.scss';

/** Dependencies modules imports */
import appConfig from './app.config';
import mapsConfig from './maps.config';
import areaBlu from './modules/areaBlu/areaBlu.module';

angular.module('app', [
  angularRouter,
  'nemLogging',
  'uiGmapgoogle-maps',
  areaBlu
])
.config(appConfig)
.config(mapsConfig);
