/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('AppMain', ['ngRoute'])

// Default controller to initial page
.controller("IndexController", function($scope, $rootScope) {
  $rootScope.apiUrl = "http://localhost:8080/Ecommerce/";
  $scope.hi = "Hello World";
});
