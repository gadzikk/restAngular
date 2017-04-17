myApp.service('AuthSrv', ['$http', function ($http) {

    this.getAccountByEmail = function (email) {
        var req = {
            method: 'GET',
            url: "http://localhost:8080/gadziksy-web/rest/account/" + email
        };
        return $http(req);
    };

    this.userlogin = function (params) {
        var req = {
            method:'POST',
            url:"http://localhost:8080/gadziksy-web/rest/authentication/login",
            data:params
        };
        return $http(req);

    }
}]);