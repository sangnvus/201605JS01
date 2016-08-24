;(function() {
	'use strict';
	var retakeTestResultCtrl = function($scope, $state, veazyConfig, ngDialog, Helper, UserService) {
		$scope.CODE = veazyConfig.CODE;

		//exam result
		$scope.examResult = $state.params.examResult;

		$scope.trustAsHtml = Helper.trustAsHtml;
	};

	retakeTestResultCtrl.$inject = ['$scope', '$state', 'veazyConfig', 'ngDialog', 'Helper', 'UserService'];
	angular.module('veazyControllers').controller('retakeTestResultCtrl', retakeTestResultCtrl);
})();