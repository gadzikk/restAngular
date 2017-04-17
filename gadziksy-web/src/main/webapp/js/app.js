angular.module('myApp', ['ngRoute', 'ngCookies', 'membersService']).config(
    ['$httpProvider', '$routeProvider', function ($httpProvider, $routeProvider) {
        $routeProvider.when('/home', {
            templateUrl: 'js/home/home.html',
            controller: 'MembersCtrl'
        }).when('/account', {
            templateUrl: 'js/signup/account.html',
            controller: 'AccCtrl'
        }).when('/login', {
            templateUrl: 'js/authentication/login.html',
            controller: 'AuthCtrl'
        }).when('/myaccount', {
            templateUrl: 'js/myAccount/myAccount.html',
            controller: 'myAccountCtrl'
        })
            .otherwise({
                redirectTo: '/home'
            });
    }]);
