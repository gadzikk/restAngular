myApp.service('AuthSrv', ['$http', '$cookies', function ($http, $cookies) {

    this.getAccountByEmail = function (email) {
        var req = {
            method: 'GET',
            url: "http://localhost:8080/gadziksy-web/rest/account/" + email
        };
        return $http(req);
    };

    this.userlogin = function (params) {
        var req = {
            method: 'POST',
            url: "http://localhost:8080/gadziksy-web/rest/authentication/login",
            data: params
        };
        return $http(req);
    };

    this.userLogout = function () {
        $cookies.remove('email');
        $cookies.remove('money');
        $cookies.remove('creationDate');
        var req = {
            method: 'POST',
            url: "http://localhost:8080/gadziksy-web/rest/authentication/logout"
        };
        return $http(req);
    }
}]);