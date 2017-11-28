angular.module('AppMain')
.controller('pedidosController', function($rootScope, $http, api, $location, $scope) {
  api.call('ServletPedido', 'get', {
    usuario: 1
  }).then(function(response) {
    $rootScope.pedidos = response.data;
  }, function(response) {
    alert("Um erro inesperado ocorreu");
    console.log(response);
  });

  $scope.cancel = function(id) {
    console.log(id);
    api.call('ServletPedido?delete', 'post', {
      id: id
    }).then(function() {
      location.reload();
    })
  };
});
