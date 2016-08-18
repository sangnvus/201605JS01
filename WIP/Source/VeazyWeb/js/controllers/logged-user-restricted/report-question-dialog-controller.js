;(function() {
	'use strict';
	var reportQuestionDialogCtrl = function($scope, veazyConfig, ngDialog) {
		$scope.closeDialog = function() {
			ngDialog.close();
		};
	};

	reportQuestionDialogCtrl.$inject = ['$scope', 'veazyConfig', 'ngDialog'];
	angular.module('veazyControllers').controller('reportQuestionDialogCtrl', reportQuestionDialogCtrl);
})();