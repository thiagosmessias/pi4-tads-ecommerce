angular.module('AppMain')
.directive('sideMenu', function() {
  return {
    restrict: 'E',
    templateUrl: 'app/templates/directive.sideMenu.html',
  };
});
