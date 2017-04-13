var myApp = angular.module('myApp');
myApp.controller('AuthCtrl', ['$scope', '$http', 'AuthSrv', function ($scope, $http, AuthSrv) {
    $scope.information = 'OK';
    $scope.account = {
        email: '',
        password: ''
    };
    $scope.session = {
        email:'',
        creationDate:'',
        money:''
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
                $scope.loggedin = true;
            }
        }, function () {
            $scope.loggedin = false;

        })
    }


}]);