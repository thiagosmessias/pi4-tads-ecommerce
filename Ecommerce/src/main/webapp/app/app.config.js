/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('AppMain')
        .config(function ($routeProvider, $locationProvider) {
            $locationProvider.hashPrefix('!');
            // Routes to single page application
            $routeProvider
                    .when('/index', {
                        templateUrl: "app/templates/index.html"
                    })
                    .when('/pedidos', {
                        templateUrl: "app/templates/Pedidos.html"
                    })
                    .when('/carrinho', {
                        templateUrl: "app/templates/Carrinho.html"
                    })
                    .when('/usuario/:id/edit', {
                        templateUrl: "app/templates/usuario.edit.html"
                    })
                    .when('/cadastro', {
                        templateUrl: "app/templates/cadastro.html"
                    })
                    .when('/produtos', {
                        templateUrl: "app/templates/produtos.html"
                    })
                    .when('/produto/', {
                        templateUrl: "app/templates/produto.detail.html"
                    })
                    .when('/produto/:id/edit', {
                        templateUrl: "app/templates/produto.edit.html"
                    })
                    .otherwise('/index');

        });
