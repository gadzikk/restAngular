var myApp = angular.module('myApp');
myApp.controller('MembersCtrl',['$scope','$http', 'MembersSrv',function ($scope, $http, MembersSrv) {

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
        $scope.newPerson = {name: "", lname: "", phoneNumber: ""};
        $scope.clearMessages();
    };

    $scope.register = function () {
        $scope.clearMessages();

        MembersSrv.save($scope.newPerson, function (data) {
            $scope.refresh();
            $scope.reset();
            $scope.successMessages = ['Member Registered'];
        }, function (result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = ['Unknown  server error'];
            }
        });
    };

    $scope.refresh();
    $scope.reset();
    $scope.orderBy = 'name';
}]);