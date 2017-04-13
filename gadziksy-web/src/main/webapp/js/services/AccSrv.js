myApp.service('AccSrv', ['$http', function ($http) {

    this.getAccountByEmail = function (email) {
        var req = {
            method: 'GET',
            url: "http://localhost:8080/gadziksy-web/rest/account/" + email
        };
        return $http(req);
    };

    this.createAccount = function (params) {
        var req = {
            method: 'POST',
            url: "http://localhost:8080/gadziksy-web/rest/account/create",
            data:params
        };
        var promse = $http(req);
        return promse;
    }
}]);