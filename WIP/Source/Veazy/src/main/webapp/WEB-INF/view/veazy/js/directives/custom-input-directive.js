;(function() {
	'use strict';
	//modify the style of checkbox
	angular.module('veazyDirectives').directive('customInput', ['$timeout', function($timeout) {
		return {
			restrict: 'A',
			link: function(scope, element, attrs) {
				//stop the directive and wait until ng-repeat finishes rendering
				$timeout(function() {
					$(element).customInput();
				}, 0);
			}
		};
	}]);
})();