(function() {
	'use strict';
	var userManagementCtrl = function($scope, UserService, veazyConfig, ngDialog, FileUploader) {
		// $scope.uploader = new FileUploader();
		$scope.CODE = veazyConfig.CODE;
		var CODE = $scope.CODE;

		UserService.getUserList().then(function(response) {
			switch (response.code) {
				case CODE.SUCCESS: {
					console.log(response.data);
					$scope.userList = response.data.listUsers;
					$scope.filteredUserList = $scope.userList;

					//pagination
					$scope.totalItems = $scope.userList.length;
					$scope.currentPage = 1;
					$scope.numPerPage = 5;

					$scope.paginate = function(value) {
						var begin, end, index;
						begin = ($scope.currentPage - 1) * $scope.numPerPage;
						end = begin + $scope.numPerPage;
						index = $scope.userList.indexOf(value);
						return (begin <= index && index < end);
					};
					break;
				}
				case CODE.UNAUTHORIZED: {
					$state.go('login');
					break;
				}
				case CODE.NO_PERMISSION: {
					$state.go('forbidden');
					break;
				}
				default: {

				}
			}
		});

		//click update profile
		$scope.openUserDetailDialog = function() {
			ngDialog.open({
				template: 'partials/admin-restricted/user-detail-dialog.html',
				className: 'ngdialog-theme-default update-profile-dialog',
				showClose: true,
				closeByDocument: false,
				width: 700,
				resolve: {
					getUser: function($timeout, $q, veazyConfig, UserService) {
						// UserService.getUserDetail()
						// return UserService.getUserDetail();
					}
				}
				// controller: 'updateProfileCtrl',
				// data: {
				// 	user: $scope.user
				// }
			});
		};
	};

	userManagementCtrl.$inject = ['$scope', 'UserService', 'veazyConfig', 'ngDialog', 'FileUploader'];

	angular.module('veazyControllers').controller('userManagementCtrl', userManagementCtrl);
})();