'use strict';

/* put your routes here */

angular.module('care-reporting', ['motech-dashboard', 'YourModuleServices', 'ngCookies', 'bootstrap'])
    .config(['$routeProvider', function ($routeProvider) {

        $routeProvider
            .when('/welcome', { templateUrl: '../care-reporting/resources/partials/welcome.html', controller: YourController })
            .otherwise({redirectTo: '/welcome'});
    }]);
