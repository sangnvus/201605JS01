;(function() {
	'use strict';

	var veazyHeader = function($window) {
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
			controller: 'headerCtrl'
		};
	};

	veazyHeader.$inject = ['$window'];
	angular.module('veazyDirectives').directive('veazyHeader', veazyHeader);
})();