var myApp = angular.module('myApp');
myApp.controller('AuthCtrl', ['$scope', '$http', 'AuthSrv', '$cookies', '$location', '$timeout',
    function ($scope, $http, AuthSrv, $cookies, $location, $timeout) {
        $scope.account = {
            email: '',
            password: ''
        };
        $scope.session = {
            email: '',
            creationDate: '',
            money: ''
        };

        $scope.emailPassed = function () {
            return AuthSrv.getAccountByEmail($scope.account.email).then(function (data) {
                if (data.status == 200) {
                    $scope.accountFound = true;
                }
            }, function () {
                $scope.accountFound = false;
            })
        };

        $scope.login = function () {
            return AuthSrv.userlogin($scope.account).then(function (data) {
                if (data.status == 200) {
                    $scope.session = data.data;
                    $cookies.put('email', $scope.session.email);
                    $cookies.put('money', $scope.session.money);
                    $cookies.put('creationDate', $scope.session.creationDate);
                    $scope.loggedin = true;
                    $timeout(function () {
                        $location.path('/myaccount')
                    }, 2000);
                }
            }, function () {
                $scope.loggedin = false;
            })
        }
    }]);