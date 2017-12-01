angular.module('AppMain')
.controller('loginController', function(api, $localStorage, $rootScope, $scope, $location) {
  $scope.show = $rootScope.usuario ? $rootScope.usuario: false;
  $scope.login = function (cred) {
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
});
