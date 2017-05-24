var myApp = angular.module('myApp');
myApp.controller('myAccountCtrl', ['$scope', '$cookies', 'AuthSrv', 'myAccountSrv',
    function ($scope, $cookies, AuthSrv, myAccountSrv) {

        $scope.accountInfo = function () {
            $scope.account = {
                email: $cookies.get('email'),
                money: $cookies.get('money'),
                creationDate: $cookies.get('creationDate')
            };
        };

        $scope.operations = {
            id: "",
            type: "",
            amount: "",
            creationDate: ""
        };

        $scope.page = 1;
        $scope.currentpage = 1;
        $scope.nextpage = 0;
        $scope.prevpage = 0;


        $scope.logout = function () {
            return AuthSrv.userLogout().then(function (data) {
                $scope.accountInfo();
            });
        };

        $scope.getOperations = function () {
            return myAccountSrv.getOperationsForAccount().then(function (data) {
                $scope.operations = data.data;
            });
        };

        $scope.getOperationByPage = function (page) {
            var startIndex = ((page - 1) * 10);
            var endIndex = (page * 10);
            return myAccountSrv.getOperationsByPageForAccount(startIndex, endIndex).then(function (data) {
                $scope.operations = data.data;
                $scope.currentpage = page;
                $scope.nextpage = parseInt(page) + 1;
                $scope.prevpage = parseInt(page) - 1;
            });
        };

        $scope.accountInfo();
        $scope.getOperationByPage($scope.page);
    }]);