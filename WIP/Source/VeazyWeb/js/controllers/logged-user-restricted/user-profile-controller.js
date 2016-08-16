(function() {
	'use strict';
	var userProfileCtrl = function($scope, UserService, veazyConfig, ngDialog, getUser) {
		$scope.user = getUser.data;
		$scope.CODE = veazyConfig.CODE;

		// console.log(getUser);
		console.log($scope.user);

		//click change password
		$scope.openChangePasswordDialog = function() {
			ngDialog.open({
				template: 'partials/logged-user-restricted/change-password-dialog.html',
				className: 'ngdialog-theme-default change-password-dialog',
				showClose: true,
				closeByDocument: false,
				width: 500,
				controller: 'changePasswordCtrl'
			});
		};

		//click update profile
		$scope.openUpdateProfileDialog = function() {
			ngDialog.open({
				template: 'partials/logged-user-restricted/update-profile-dialog.html',
				className: 'ngdialog-theme-default update-profile-dialog',
				showClose: true,
				closeByDocument: false,
				width: 700,
				controller: 'updateProfileCtrl',
				data: {
					user: $scope.user
				}
			});
		};

		$scope.makeMeEditor = function() {
			UserService.makeEditor($scope.user.userName).then(function(response) {
				console.log(response);
			});
		};
	};

	userProfileCtrl.$inject = ['$scope', 'UserService', 'veazyConfig', 'ngDialog', 'getUser'];

	angular.module('veazyControllers').controller('userProfileCtrl', userProfileCtrl);
})();