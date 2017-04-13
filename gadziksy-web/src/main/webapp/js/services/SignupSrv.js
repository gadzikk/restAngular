myApp.service('SignupSrv', ['$http', function ($http) {

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