angular.module('AppMain')
.controller('UserController', function($rootScope, $http, utils) {
  this.post = function(data) {
    $http.post($rootScope.apiUrl + '/ServletUsuario', data)
    .done(function(response) {
      alert('success');
    }, function(response) {
      alert('error');
    });
  }

  this.get = function(data) {
    $http.get($rootScope.apiUrl + '?' +  utils.obj2url(data))
    .done(function(response) {
      alert('success');
      console.log(response.data);
    }, function(response) {
      alert('false');
    });
  }
});
