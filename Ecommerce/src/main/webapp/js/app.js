/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('AppMain', ['ngRoute'])
.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider
  .when('/index',{
    templateUrl: "templates/index.html"
  })
  .when('usuarios', {
    templateUrl: "templates/usuarios/lista/html"
  })
  .otherwise('/index');
})

// Define controller
.controller("IndexController", function($scope, $rootScope) {
  $rootScope.apiUrl = "http://localhost:8080/Ecommerce/";
  $scope.hi = "Hello World";
  console.log('hereee');
});
