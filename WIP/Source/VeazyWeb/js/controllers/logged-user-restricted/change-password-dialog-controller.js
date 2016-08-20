;(function() {
	'use strict';
	var changePasswordCtrl = function($scope, User, veazyConfig) {
		$scope.REGULATIONS = veazyConfig.REGULATIONS;
	};

	changePasswordCtrl.$inject = ['$scope', 'User', 'veazyConfig'];
	angular.module('veazyControllers').controller('changePasswordCtrl', changePasswordCtrl);
})();