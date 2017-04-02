var myApp = angular.module('myApp');
myApp.controller('MembersCtrl', ['$scope', '$http', 'MembersSrv', function ($scope, $http, MembersSrv) {

    $scope.refresh = function () {
        return MembersSrv.getAllPersons().then(function (data) {
            $scope.persons = data.data;
        });
    };

    $scope.clearMessages = function () {
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};
    };

    $scope.reset = function () {
        if ($scope.regForm) {
            $scope.regForm.$setPristine();
        }
        $scope.newPerson = {name: "", lname: "", dob: ""};
        $scope.clearMessages();
    };

    $scope.register = function () {
        $scope.clearMessages();
        return MembersSrv.addPerson($scope.newPerson).then(function (data) {
            $scope.refresh();
        });
    };

    $scope.refresh();
    $scope.reset();
    $scope.orderBy = 'id';
    $scope.desc = false;

}]);