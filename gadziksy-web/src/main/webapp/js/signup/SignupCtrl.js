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
