angular.module('AppMain')
.controller('pedidosController', function($rootScope, $http, api) {
  $rootScope.produtos = [
    {
      id: '1',
      nome: 'Produto 1',
      descricao: 'lorem ipsus',
      preco: 10.50
    },
    {
      id: '2',
      nome: 'Produto 2',
      descricao: 'lorem ipsus',
      preco: 10.50
    },
    {
      id: '3',
      nome: 'Produto 3',
      descricao: 'lorem ipsus',
      preco: 10.50
    },
    {
      id: '4',
      nome: 'Produto 4',
      descricao: 'lorem ipsus',
      preco: 10.50
    },
    {
      id: '5',
      nome: 'Produto 5',
      descricao: 'lorem ipsus',
      preco: 10.50
    },
    {
      id: '6',
      nome: 'Produto 6',
      descricao: 'lorem ipsus',
      preco: 10.50
    }
  ];
  api.call('ServletPedido', 'post', {
    items: $rootScope.produtos,
    usuario: 1
  }).then(function(response) {
    console.log(response);
  }, function(response) {
    console.log(response);
  });
  // $http.get('http://localhost:8080/Ecommerce/ServletPedido')
  // .then(function(response) {
  //   console.log(response);
  //
  // }, function(response) {
  //   console.log('erro', response);
  // });
});
