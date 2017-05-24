myApp.service('myAccountSrv' , ['$http' , function ($http) {

    this.getOperationsForAccount = function () {
        var req = {
            method : 'GET',
            url : "http://localhost:8080/gadziksy-web/rest/operation/all"
        };
        return $http(req);
    };

    this.getOperationsByPageForAccount = function (start , end) {
        var req = {
            method : 'GET',
            url: "http://localhost:8080/gadziksy-web/rest/operation/" + start +"/"+end
        };
        return $http(req);
    };

}]);