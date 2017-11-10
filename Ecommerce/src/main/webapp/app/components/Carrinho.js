angular.module('AppMain')
.controller('carrinhoController', function(pedido, $scope) {
  $scope.itens = pedidos.list();
});
