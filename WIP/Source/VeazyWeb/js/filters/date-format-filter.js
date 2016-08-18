;(function() {
	'use strict';
	var veazyFilters = angular.module('veazyFilters');

	veazyFilters.filter('dateFormat', ['$translate', function($translate) {
		return function (input) {
			if (!input || input.length === 0) {
				return;
			}

			var currentLang = $translate.use();
			if (currentLang === 'en') {
				return moment(input).format('YYYY/MM/DD');
			} else if (currentLang === 'ja') {
				return moment(input).format('YYYY年MM月DD日');
			}
		}
	}]);
})();