;(function() {
	'use strict';

	var status = function() {
		return function(isBanned) {
			if (isBanned === true) {
				return 'BANNED';
			} else {
				return 'ACTIVE';
			}
		};
	};

	status.$inject = [];
	angular.module('veazyFilters').filter('status', status);
})();