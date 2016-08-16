;(function() {
	'use strict';
	//allow clicking on tab to show the corresponding panel
	angular.module('veazyDirectives').directive('malihuScroll', ['$timeout', function($timeout) {
		return {
			link: function(scope, element, attrs) {
				$timeout(function() {
					$(element).mCustomScrollbar({
						// setHeight: 400,
						// theme: 'dark-3',
						mouseWheel: {
							preventDefault: true 
						}
					});
				}, 0);
			}
		}
	}]);
})();