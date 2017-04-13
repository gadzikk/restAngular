angular.module('myApp', ['ngRoute', 'membersService']).config(
    ['$httpProvider', '$routeProvider', function ($httpProvider, $routeProvider) {
        $routeProvider.when('/home', {
            templateUrl: 'partials/home.html',
            controller: 'MembersCtrl'
        }).when('/account', {
            templateUrl: 'partials/account.html',
            controller: 'AccCtrl'
        }).otherwise({
            redirectTo: '/home'
        });
    }]);
