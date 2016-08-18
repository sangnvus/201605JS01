;(function() {
	'use strict';
	var veazyFilters = angular.module('veazyFilters');

	veazyFilters.filter('questionType', ['veazyConfig', function(veazyConfig) {
		return function (input) {
			var questionTypes = veazyConfig.questionTypes;
			for (var i = 0; i < questionTypes.length; i++) {
				if (input === questionTypes[i].id) {
					return questionTypes[i].name;
				}
			}
		}
	}]);
})();