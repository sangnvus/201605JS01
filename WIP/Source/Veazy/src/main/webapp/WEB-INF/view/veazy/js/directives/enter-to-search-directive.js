;(function() {
	'use strict';
	angular.module('veazyDirectives').directive('enterToSearch', [function() {
		return function(scope, element, attrs) {
			element.on('keypress', function(e) {
				//key enter
				if (e.which === 13) {
					scope.$apply(function() {
						scope.$eval(attrs.enterToSearch);
					});
				}
			});
		};
	}]);
})();