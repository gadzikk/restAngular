angular.module('membersService', []).service('MembersSrv', ['$http', function ($http) {
        this.getAllPersons = function () {
            var url = "http://localhost:8080/gadziksy-web/rest/person";
            var req = {
                method: 'GET',
                url: url
            };
            return $http(req);
        };
        this.addPerson = function (params) {
            var req = {
                method: 'POST',
                url: "http://localhost:8080/gadziksy-web/rest/person/add",
                data: params
            };
            var promise = $http(req);
            return promise;
        };
        this.removePerson = function (id) {
            var req =
            {
                method:'DELETE',
                url : "http://localhost:8080/gadziksy-web/rest/person/" + id,
                data: id
            };
            var promise = $http(req);
            return promise;
        }
    }]);