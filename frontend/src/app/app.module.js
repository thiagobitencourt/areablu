'use strict';
/** Style Dependency */
import '../assets/sass/style.scss';

import API from './areaBlu/areaBlu.service';
import maps from './areaBlu/areaBlu.module';

import mainTemplate from './areaBlu/areaBlu.html';

Object.assign(window, maps);
$('app').html(mainTemplate);
