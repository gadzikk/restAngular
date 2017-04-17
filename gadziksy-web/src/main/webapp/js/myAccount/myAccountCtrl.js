var myApp = angular.module('myApp');
myApp.controller('myAccountCtrl',['$scope','$cookies' ,'AuthSrv' , function($scope , $cookies , AuthSrv){

    $scope.accountInfo = function () {
        $scope.account = {
            email: $cookies.get('email'),
            money: $cookies.get('money'),
            creationDate: $cookies.get('creationDate')
        };
    };

    $scope.logout = function () {
        return AuthSrv.userLogout().then(function (data) {

            $scope.accountInfo();
        });
    };

    $scope.accountInfo();
}]);