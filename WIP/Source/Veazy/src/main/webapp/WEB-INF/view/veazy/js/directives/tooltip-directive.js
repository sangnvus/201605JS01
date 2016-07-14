;(function() {
	'use strict';
	angular.module('veazyDirectives').directive('tooltip', [function() {
		return {
			restrict: 'A',
			link: function(scope, element, attrs) {
				$(element).tooltip({
					trigger: 'hover'
				});
			}
		}
	}]);
})();