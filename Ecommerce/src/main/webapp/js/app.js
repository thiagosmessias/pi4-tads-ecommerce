/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('AppMain', ["ngRoute"])
    .config(function($routeProvider) {
        $routeProvider
        .when('app',
                {templateUrl: '/html/Usuarios/Usuarios.html'}
        );
    })
    .controller('AppController', function ($scope) {
        $scope.message = "Hello World!";
    });

