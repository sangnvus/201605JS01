;(function() {
	'use strict';
	// var veazyFilters = angular.module('veazyFilters');

	var timeDuration = function($translate) {
		return function (input, type) {
			if (input == null) {
				return;
			}

			var currentLang = $translate.use();
			if (type) {
				if (type === 'number') {
					return moment.duration(input, 'seconds').format('hh:mm:ss', {trim: false});
				} else if (type == 'text') {
					if (currentLang === 'en') {
						return moment.duration(input, 'seconds').format('m[min]s[sec]');
					} else if (currentLang === 'ja') {
						return moment.duration(input, 'seconds').format('m[分]s[秒]');
					}
				}
			} else {
				return moment.duration(input, 'seconds').format('hh:mm:ss', {trim: false});
			}
		}
	};

	timeDuration.$inject = ['$translate'];

	angular.module('veazyFilters').filter('timeDuration', timeDuration);
})();