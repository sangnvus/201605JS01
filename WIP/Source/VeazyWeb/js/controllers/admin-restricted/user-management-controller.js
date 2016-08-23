(function() {
	'use strict';
	var userManagementCtrl = function($scope, UserService, veazyConfig, ngDialog, FileUploader) {
		// $scope.uploader = new FileUploader();
		$scope.CODE = veazyConfig.CODE;

		// console.log(getUser);
		// console.log($scope.user);

		//click change password
		// $scope.openChangePasswordDialog = function() {
		// 	ngDialog.open({
		// 		template: 'partials/logged-user-restricted/change-password-dialog.html',
		// 		className: 'ngdialog-theme-default change-password-dialog',
		// 		showClose: true,
		// 		closeByDocument: false,
		// 		width: 500,
		// 		controller: 'changePasswordCtrl'
		// 	});
		// };

		//click update profile
		$scope.openUserDetailDialog = function() {
			ngDialog.open({
				template: 'partials/admin-restricted/user-detail-dialog.html',
				className: 'ngdialog-theme-default update-profile-dialog',
				showClose: true,
				closeByDocument: false,
				width: 700,
				// controller: 'updateProfileCtrl',
				data: {
					user: $scope.user
				}
			});
		};
	};

	userManagementCtrl.$inject = ['$scope', 'UserService', 'veazyConfig', 'ngDialog', 'FileUploader'];

	angular.module('veazyControllers').controller('userManagementCtrl', userManagementCtrl);
})();