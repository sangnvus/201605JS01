;(function() {
	'use strict';

	var userDetailCtrl = function($scope, $state, ngDialog, veazyConfig, getUser) {
		$scope.user = getUser;
		// console.log($scope.user);

		$scope.closeDialog = function() {
			ngDialog.close();
		}
	};

	userDetailCtrl.$inject = ['$scope', '$state', 'ngDialog', 'veazyConfig', 'getUser'];
	angular.module('veazyControllers').controller('userDetailCtrl', userDetailCtrl);
})();