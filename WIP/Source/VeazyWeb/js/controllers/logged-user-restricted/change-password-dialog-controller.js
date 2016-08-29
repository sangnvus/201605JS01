;(function() {
	'use strict';
	var changePasswordCtrl = function($scope, $state, UserService, veazyConfig, md5, ngDialog) {
		$scope.REGULATIONS = veazyConfig.REGULATIONS;
		var CODE = veazyConfig.CODE;
		$scope.passwordChanged = false;

		$scope.changePassword = function() {
			var password = md5.createHash($scope.password || '');
			var newPassword = md5.createHash($scope.newPassword || '');
			UserService.changePassword(password, newPassword).then(function(response) {
				switch (response.code) {
					case CODE.SUCCESS: {
						$scope.passwordChanged = true;
						break;
					}
					case CODE.UNAUTHORIZED: {
						$state.go('login');
						break;
					}
				}
			});
		};

		$scope.closeDialog = function() {
			ngDialog.close();
		}
	};

	changePasswordCtrl.$inject = ['$scope', '$state', 'UserService', 'veazyConfig', 'md5', 'ngDialog'];
	angular.module('veazyControllers').controller('changePasswordCtrl', changePasswordCtrl);
})();