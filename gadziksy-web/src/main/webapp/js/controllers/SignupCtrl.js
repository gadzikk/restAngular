var myApp = angular.module('myApp');
myApp.controller('AccCtrl', ['$scope', '$http', 'SignupSrv', '$location', function ($scope, $http, SignupSrv, $location) {
    $scope.ExistingAccount = {
        id: "",
        email: "",
        password: "",
        money: "",
        creationDate: ""
    };

    $scope.addAccount = function () {
        return SignupSrv.createAccount($scope.newAccount).then(function (data) {
            $scope.refresh();
        })
    };

    $scope.getAccount = function () {
        return SignupSrv.getAccountByEmail($scope.ExistingAccount.email).then(function (data) {
            $scope.ExistingAccount = data.data;
            if (data.status == 200) {
                $scope.accountFound = true;
                $location.path("http://localhost:8080/gadziksy-web/index.html#/home");
            }
        }, function () {
            $scope.accountFound = false;
        })
    };

    $scope.refresh = function () {
        if ($scope.accForm) {
            $scope.accForm.$setPristine();
        }
        $scope.newAccount = {
            email: "",
            password: "",
            money: ""
        };
    }
}]);
