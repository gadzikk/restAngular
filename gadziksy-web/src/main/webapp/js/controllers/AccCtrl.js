var myApp = angular.module('myApp');
myApp.controller('AccCtrl',['$scope','$http' ,'AccSrv' , function ($scope , $http , AccSrv) {
    $scope.hello = 'helloss';
    $scope.world = 'comunisma';
    $scope.ExistingAccount = {
        "id": '',
        "email": '',
        "password": '',
        "creationDate": ''
    };

    $scope.getAccount = function () {
        return AccSrv.getAccountByEmail($scope.ExistingAccount.email).then(function (data) {
            $scope.ExistingAccount = data.data;
        })

    }

}]);
