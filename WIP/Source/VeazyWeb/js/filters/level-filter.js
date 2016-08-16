;(function() {
	'use strict';
	var veazyFilters = angular.module('veazyFilters');

	veazyFilters.filter('level', ['veazyConfig', function(veazyConfig) {
		return function (input) {
			var levels = veazyConfig.levels;
			for (var i = 0; i < levels.length; i++) {
				if (input == levels[i].id) {
					return levels[i].name;
				}
			}
		}
	}]);
})();