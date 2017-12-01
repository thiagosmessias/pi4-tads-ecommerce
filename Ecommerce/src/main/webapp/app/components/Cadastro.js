angular.module('AppMain').controller("cadastro", function ($scope, $http, $rootScope) {
    $scope.user={};
    $scope.addCli = function () {
        $http({
            url: 'ServletUsuario',
            method: 'post',
            data: $scope.user
        }).then(function (response) {
            alert('Funcionario cadastrado');
            console.log(response.data);
        }, function (response) {
            console.log(response.data);
        });
    };
});