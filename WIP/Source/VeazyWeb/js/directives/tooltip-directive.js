;(function() {
	'use strict';
	angular.module('veazyDirectives').directive('tooltip', ['$timeout', function($timeout) {
		return {
			restrict: 'A',
			link: function(scope, element, attrs) {
				$timeout(function() {
					$(element).tooltip({
						trigger: 'hover'
					});

					$(element).click(function() {
						$(element).tooltip('hide');
					});
				}, 0);
			}
		}
	}]);
})();