angular.module('myApp', ['ngRoute','ngCookies', 'membersService']).config(
    ['$httpProvider', '$routeProvider' , function ($httpProvider, $routeProvider) {
        $routeProvider.when('/home', {
            templateUrl: 'partials/home.html',
            controller: 'MembersCtrl'
        }).when('/account', {
            templateUrl: 'partials/account.html',
            controller: 'AccCtrl'
        }).when('/login', {
            templateUrl: 'partials/login.html',
            controller: 'AuthCtrl'
        }).otherwise({
            redirectTo: '/home'
        });
    }]);
