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
        .when('/index',{
            templateUrl: "app/templates/index.html"
        })
        .when('usuarios', {
            templateUrl: "app/templates/usuarios.html"
        })
        .when('usuario/:id', {
            templateUrl: "app/templates/usuario.detail.html"
        })
        .when('usuario/:id/edit', {
            templateUrl: "app/templates/usuario.edit.html"
        })
        .when('produtos', {
            templateUrl: "app/templates/produtos.html"
        })
        .when('produto/:id', {
            templateUrl: "app/templates/produto.detail.html"
        })
        .when('produto/:id/edit', {
            templateUrl: "app/templates/produto.edit.html"
        })   
        .otherwise('/index');
 
});
