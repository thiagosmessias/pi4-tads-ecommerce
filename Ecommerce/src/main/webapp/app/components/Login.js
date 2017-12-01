angular.module('AppMain')
.controller('loginController', function(api, $localStorage, $rootScope, $scope, $location) {
  $scope.login = $rootScope.login;
  $scope.logout = $rootScope.logout;
});
