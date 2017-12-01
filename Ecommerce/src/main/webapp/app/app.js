/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('AppMain', ['ngRoute', 'ngStorage'])
.run(function($rootScope, $localStorage, api, $localStorage, $location) {
  if ('usuario' in $localStorage) {
    $rootScope.usuario = $localStorage.usuario;
  } else {
    $rootScope.usuario = false;
  }

  if ($rootScope.usuario) {
    api.call('ServletUsuario', 'get', {
      id: $rootScope.usuario
    }).then(function(response) {
      $rootScope.userData = response.data;
    });
  }

  $rootScope.login = function (cred) {
    api.call('ServletSession', 'post', {
      email: cred.username,
      senha: cred.password
    }).then(function(response) {
      $rootScope.usuario = response.data.id_usuario;
      $localStorage.$default({session: response.data});
      $localStorage.$default({usuario: $rootScope.usuario});
      $location.path("/pedidos");
    }, function(response) {
      console.log(response);
      alert("Usuario ou senha incorretos, tente novamente!");
    });
  };

  $rootScope.logout = function (cred) {
    api.call('ServletSession?delete', 'post', {
      id_usuario: $rootScope.usuario
    }).then(function(response) {
      $rootScope.usuario = response.data.id_usuario;
      $localStorage.$default({session: response.data});
      $location.path("/index");
    }, function(response) {
      console.log(response);
    });
  }

})
// Default controller to initial page
.controller("IndexController", function ($scope, $rootScope, api) {
    $rootScope.search = function () {
        if (location.href.indexOf('search') > 0) {
            var p = location.href.substr(location.href.lastIndexOf('#!/')).split('?')[1].split('&');
            var params = p[0].split('=');
            if (params) {
                if (typeof params[0] != 'undefined' && typeof params[1] != 'undefined') {
                    console.log(params);
                    api.call('ServletProduto', 'get', {
                        search: params[1]
                    }).then(function (response) {
                        $rootScope.produtos = response.data;
                        if ($rootScope.produtos.length < 1) {
                            alert("Sua pesquisa não mostrou nenhum resultado");
                        }
                    });
                }
            } else {
                api.call('ServletProduto', 'get', {
                    search: $rootScope.searchV
                }).then(function (response) {
                    $rootScope.produtos = response.data;
                });
            }
        } else {
            api.call('ServletProduto', 'get').then(function (response) {
                $rootScope.produtos = response.data;
            }, function (response) {
                console.log(response);
                alert("Um erro inesperado ocorreu, tente novamente mais tarde");
            });
        }
    };
    $rootScope.search();
});
