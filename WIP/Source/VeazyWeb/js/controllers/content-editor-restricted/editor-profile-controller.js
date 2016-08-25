;(function() {
	var editorProfileCtrl = function($scope, ngDialog, getUser) {
		$scope.user = getUser.data;

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
	};

	editorProfileCtrl.$inject = ['$scope', 'ngDialog', 'getUser'];
	angular.module('veazyControllers').controller('editorProfileCtrl', editorProfileCtrl);
})();