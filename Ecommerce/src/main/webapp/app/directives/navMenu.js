angular.module('AppMain')
.directive('navMenu', function() {
  return {
    restrict: 'E',
    templateUrl: 'app/templates/directive.navMenu.html',
  };
});
