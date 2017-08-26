import areaBluComponent from './areaBlu.component';
import areaBluService from './areaBlu.service';

export default angular.module('areaBlu', [])
    .component('areaBlu', areaBluComponent)
    .service('areaBluService', areaBluService)
    .name;
