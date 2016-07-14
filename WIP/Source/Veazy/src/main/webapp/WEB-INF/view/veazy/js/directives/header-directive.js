;(function() {
	;'use strict';

	angular.module('veazyDirectives').directive('veazyHeader', ['$window', function($window) {
		return {
			restrict: 'E',
			templateUrl: 'partials/header.html',
			link: function(scope, element, attrs) {
				//shrink page header
				this.pageYOffset >= 50 ? scope.scrollDown = true : scope.scrollDown = false;
				
				$($window).bind("scroll", function() {
					this.pageYOffset >= 50 ? scope.scrollDown = true : scope.scrollDown = false;
					scope.$apply();
				});
			},
			controller: ['$scope', '$location', function($scope, $location) {
				$scope.showCourseListPage = function() {
					$location.path('/courselist');
				};

				$scope.showHomePage = function() {
					$location.path('/home');
				};

				$scope.showLoginPage = function() {
					$location.path('/login');
				};

				$scope.showDoTestPage = function() {
					$location.path('/test');
				};
			}]
		};
	}]);
})();