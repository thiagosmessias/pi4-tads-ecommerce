angular.module('AppMain')
.controller('carrinhoController', function(pedido, api, $scope, $rootScope, $location) {
  $scope.itens = pedido.list();
  $scope.frete = 15;
  $scope.desconto = 0;
  $scope.delete = pedido.delete;

  $scope.calcTotalPed = function() {
    var x = 0;
    for (item of $scope.itens) {
      x += item.preco * item.quantidade;
    }
    return x;
  };

  $scope.calcTotal = function() {
    return $scope.calcTotalPed() + $scope.frete - $scope.desconto;
  }

  if ($rootScope.usuario) {
    api.call('ServletEndereco', 'get', {
      usuario: $rootScope.usuario
    }).then(function(response) {
      $scope.enderecos = response.data;
    }, function(response) {
      console.log(response);
      alert("Um comportamento inesperado ocorreu, por favor tente novamente mais tarde");
    });
  }

  $scope.makeRequest = function() {
    api.call('ServletPedido', 'post', {
      items: pedido.list(),
      usuario: $rootScope.usuario,
      endereco: $scope.endereco
    }).then(function() {
      alert("Pedido relalizado com sucesso");
      pedido.clear();
      $location.path('pedidos');
    }, function(response) {
      console.log(response);
      alert("Um comportamento inesperado ocorreu, por favor tente novamente mais tarde");
    });
  }

});
