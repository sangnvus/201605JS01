;(function() {
	'use strict';
	var veazyFilters = angular.module('veazyFilters');

	veazyFilters.filter('timeDuration', [function() {
		return function (input) {
			if (!input) {
				return;
			}
			return moment.duration(input, 'seconds').format('mm:ss')
		}
	}]);
})();