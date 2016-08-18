;(function() {
	'use strict';
	var veazyFilters = angular.module('veazyFilters');

	veazyFilters.filter('lessonState', ['veazyConfig', function(veazyConfig) {
		return function (input) {
			var lessonStates = veazyConfig.lessonStates;
			for (var i = 0; i < lessonStates.length; i++) {
				if (input === lessonStates[i].id) {
					return lessonStates[i].name;
				}
			}
		}
	}]);
})();