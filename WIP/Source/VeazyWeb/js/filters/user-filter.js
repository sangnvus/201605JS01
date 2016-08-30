;(function() {
	'use strict';
	var user = function($filter) {
		// var roles = veazyConfig.roles;
		return function(input, role, status, keyword) {
			if (input == null) {
				return;
			}
			var queryObj = {};

			if (role && role !== 0) {
				queryObj.role = role;
			}
			if (status && status !== 0) {
				if (status === 1) {
					queryObj.isBanned = true;
				} 
				else if (status === 2) {
					queryObj.isBanned = false;
				}
			}
			if (keyword) {
				queryObj.userName = keyword;
			}

			// console.log($filter('filter')(input, queryObj));

			return $filter('filter')(input, queryObj);
		}
	};
	user.$inject = ['$filter'];

	angular.module('veazyFilters').filter('user', user);
})();