/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('AppMain', ["ngRoute"])
.config(function ($routeProvider){
  $routeProvider
  .when("/", {
    temaplteUrl: "/templates/index.html"
  }).when("/index", {
    templateUrl: "/templates/index.html"
  })
  .otherwise('/');

  $routeProvider.caseInsensitiveMatch = true;
}).controller("IndexController", function($scope) {
  $scope.hi = "Hello World";
});
