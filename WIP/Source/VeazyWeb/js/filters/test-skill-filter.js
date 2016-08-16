;(function() {
	'use strict';
	var veazyFilters = angular.module('veazyFilters');

	veazyFilters.filter('testSkill', ['veazyConfig', function(veazyConfig) {
		return function (input) {
			var testSkills = veazyConfig.testSkills;
			for (var i = 0; i < testSkills.length; i++) {
				if (input === testSkills[i].id) {
					return testSkills[i].name;
				}
			}
		}
	}]);
})();