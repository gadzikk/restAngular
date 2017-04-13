var myApp = angular.module('myApp');
myApp.controller('AccCtrl', ['$scope', '$http', 'AccSrv', function ($scope, $http, AccSrv) {
    $scope.ExistingAccount = {
        id: "",
        email: "",
        password: "",
        creationDate: ""
    };
    $scope.accountFounded = false;

    $scope.addAccount = function () {
        return AccSrv.createAccount($scope.newAccount).then(function (data) {
            $scope.refresh();
        })
    };

    $scope.getAccount = function () {
        return AccSrv.getAccountByEmail($scope.ExistingAccount.email).then(function (data) {
            $scope.ExistingAccount = data.data;
            if (data.status == 200) {
                $scope.accountFound = true;
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
            password: ""
        };
    }
}]);
