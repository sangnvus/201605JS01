;(function() {
	'use strict';
	var role = function(veazyConfig) {
		var roles = veazyConfig.roles;
		return function(input) {
			if (input == null) {
				return;
			}
			for (var i = 0; i < roles.length; i++) {
				var role = roles[i];
				if (input === role.id) {
					return role.name;
				}
			}
		}
	};
	role.$inject = ['veazyConfig'];

	angular.module('veazyFilters').filter('role', role);
})();