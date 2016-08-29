;(function() {
	'use strict';
	// var veazyFilters = angular.module('veazyFilters');

	var timeDuration = function($translate) {
		return function (input, type) {
			if (input == null) {
				return;
			}

			var currentLang = $translate.use();
			var sec;
			var isMinute;
			var duration = moment.duration(input, 'seconds');
			if (type) {
				if (type === 'number') {
					return duration.format('hh:mm:ss', {trim: false});
				} else if (type == 'text') {
					sec = parseInt(duration.format('s'));
					isMinute = sec % 60 === 0;
					if (currentLang === 'en') {
						return isMinute ? duration.format('m[min]') : duration.format('m[min] s[sec]');
					} else if (currentLang === 'ja') {
						return isMinute ? duration.format('m[分]') : duration.format('m[分]s[秒]');
					}
				}
			} else {
				return duration.format('hh:mm:ss', {trim: false});
			}
		}
	};

	timeDuration.$inject = ['$translate'];

	angular.module('veazyFilters').filter('timeDuration', timeDuration);
})();