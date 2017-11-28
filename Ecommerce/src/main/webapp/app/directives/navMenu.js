angular.module('AppMain')
.directive('navMenu', function() {
  return {
    restrict: 'E',
    templateUrl: 'app/templates/directive.navMenu.html',
    controller: function($scope) {
      console.log(this.searchV);
      $scope.search = function() {
        if (this.searchV) {
          window.location.href = location.href.substr(0, location.href.lastIndexOf('#!/')) + '#!/index?search=' + this.searchV;
        }
      }
    }
  };
});
