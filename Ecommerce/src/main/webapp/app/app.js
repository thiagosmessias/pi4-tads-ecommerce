/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('AppMain', ['ngRoute', 'ngStorage'])

// Default controller to initial page
.controller("IndexController", function($scope, $rootScope, api) {
  $rootScope.produtos = [
    {
      id: '1',
      nome: 'Produto 1',
      descricao: 'lorem ipsus',
      valor: 10.50
    },
    {
      id: '2',
      nome: 'Produto 2',
      descricao: 'lorem ipsus',
      valor: 10.50
    },
    {
      id: '3',
      nome: 'Produto 3',
      descricao: 'lorem ipsus',
      valor: 10.50
    },
    {
      id: '4',
      nome: 'Produto 4',
      descricao: 'lorem ipsus',
      valor: 10.50
    },
    {
      id: '5',
      nome: 'Produto 5',
      descricao: 'lorem ipsus',
      valor: 10.50
    },
    {
      id: '6',
      nome: 'Produto 6',
      descricao: 'lorem ipsus',
      valor: 10.50
    }
  ];
});
