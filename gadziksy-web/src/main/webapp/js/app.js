angular.module('myApp', ['ngRoute', 'membersService']).config(
    ['$httpProvider', '$routeProvider', function ($httpProvider, $routeProvider) {
        $routeProvider.when('/home', {
            templateUrl: 'partials/home.html',
            controller: 'MembersCtrl'
        }).otherwise({
            redirectTo: '/home'
        });
    }]);
