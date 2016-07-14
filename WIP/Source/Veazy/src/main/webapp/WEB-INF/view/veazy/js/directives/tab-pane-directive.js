;(function() {
	'use strict';
	//allow clicking on tab to show the corresponding panel
	angular.module('veazyDirectives').directive('tabPane', [function() {
		return {
			link: function(scope, element, attrs) {
				element.click(function(e) {
					e.preventDefault();
					angular.element(element).tab('show');
				});
			}
		}
	}]);
})();