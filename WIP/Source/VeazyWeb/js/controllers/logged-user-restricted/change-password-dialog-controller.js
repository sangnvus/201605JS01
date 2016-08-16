;(function() {
	'use strict';
	var changePasswordDialogCtrl = function($scope, User, veazyConfig) {
		$scope.REGULATIONS = veazyConfig.REGULATIONS;
	};

	changePasswordDialogCtrl.$inject = ['$scope', 'User', 'veazyConfig'];
	angular.module('veazyControllers').controller('changePasswordDialogCtrl', changePasswordDialogCtrl);
})();