angular.module('AppMain')
.controller('pedidosController', function($rootScope, $http, api) {
  api.call('ServletPedido', 'get', {
    usuario: 1
  }).then(function(response) {
    $rootScope.pedidos = response.data;
  }, function(response) {
    alert("Um erro inesperado ocorreu");
    console.log(response);
  });
});
