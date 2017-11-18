angular.module('AppMain')
.directive('productOnList', function() {
  return {
    restrict: 'E',
    templateUrl: 'app/templates/directive.produtoOnList.html',
    controller: function($rootScope, $scope, pedido) {
      $scope.add = function(prod) {
        pedido.add(prod);
      };
    }
  };
});
