/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('AppMain', ['ngRoute', 'ngStorage'])
.run(function($rootScope, $localStorage, api) {
  if ('usuario' in $localStorage) {
    $rootScope.usuario = $localStorage.usuario;
  } else {
    $rootScope.usuario = false;
  }
  $rootScope.usuario = 1;
  if ($rootScope.usuario) {
    api.call('ServletUsuario', 'get', {
      id: $rootScope.usuario
    }).then(function(response) {
      $rootScope.userData = response.data;
    });
  }
})
// Default controller to initial page
.controller("IndexController", function($scope, $rootScope, api) {
  api.call('ServletProduto', 'get').then(function(response) {
    $rootScope.produtos = response.data;
  }, function(response) {
    console.log(response);
    alert("Um erro inesperado ocorreu, tente novamente mais tarde");
  });
});
