angular.module('AccountService', []).service('AccSrv', ['$scope', '$http', function ($http) {

    this.getAccountByEmail = function (email) {

        var req = {
            method: 'GET',
            url: "http://localhost:8080/gadziksy-web/rest/account/" + email
        };
        return $http(req);

    }


}]);