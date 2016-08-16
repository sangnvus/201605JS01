;(function() {
	var errorDialogCtrl = function($scope, ngDialog) {
		$scope.closeDialog = function() {
			ngDialog.close();
		};
	};

	errorDialogCtrl.$inject = ['$scope', 'ngDialog'];
	angular.module('veazyControllers').controller('errorDialogCtrl', errorDialogCtrl);
})();