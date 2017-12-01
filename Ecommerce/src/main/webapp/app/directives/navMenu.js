angular.module('AppMain')
.directive('navMenu', function() {
  return {
    restrict: 'E',
    templateUrl: 'app/templates/directive.navMenu.html',
    controller: function($scope, $rootScope, $location) {
      console.log(this.searchV);
      $scope.search = function() {
        if (this.searchV) {
          window.location.href = location.href.substr(0, location.href.lastIndexOf('#!/')) + '#!/index?search=' + this.searchV;
        }
      };

      $scope.pedido = function() {
        if ($rootScope.usuario)  {
          $location.path('/pedidos');
        } else {
          alert("É necessário acessar sua conta para ver os pedidos");
          $location.path('/login');
        }
      };
      $scope.loginLogout = function() {
        if ($rootScope.usuario) {
          $rootScope.logout();
        } else {
          $location.path('/login');
        }
      };

    }
  };
});
