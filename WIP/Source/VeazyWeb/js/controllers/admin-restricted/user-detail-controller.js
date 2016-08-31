;(function() {
	'use strict';

	var userDetailCtrl = function($scope, $state, ngDialog, veazyConfig, getUser, UserService) {
		$scope.CODE = veazyConfig.CODE;
		var CODE = $scope.CODE;

		$scope.user = getUser;
		// console.log($scope.user);

		$scope.closeDialog = function() {
			ngDialog.close();
		};

		$scope.banUser = function() {
			UserService.ban($scope.user.id).then(function(response) {
				switch (response.code) {
					case CODE.SUCCESS: {
						$scope.user.isBanned = true;
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
				}
			});
		};

		$scope.unbanUser = function() {
			UserService.unban($scope.user.id).then(function(response) {
				switch (response.code) {
					case CODE.SUCCESS: {
						$scope.user.isBanned = false;
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
				}
			});
		};

		$scope.assignAsUser = function() {
			var userCode = veazyConfig.CODE.USER;
			var userId = $scope.user.id;
			UserService.changeRole(userId, userCode).then(function(response) {
				// console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						$scope.user.role = userCode;
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
				}
			});
		};

		$scope.assignAsEditor = function() {
			var editorCode = veazyConfig.CODE.EDITOR;
			var userId = $scope.user.id;
			UserService.changeRole(userId, editorCode).then(function(response) {
				// console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						$scope.user.role = editorCode;
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
				}
			});
		};

		// $scope.assignAsAdmin = function() {
		// 	var adminCode = veazyConfig.CODE.ADMIN;
		// 	var userId = $scope.user.id;
		// 	UserService.changeRole(userId, adminCode).then(function(response) {
		// 		// console.log(response);
		// 		switch (response.code) {
		// 			case CODE.SUCCESS: {
		// 				$scope.user.role = adminCode;
		// 				break;
		// 			}
		// 			case CODE.UNAUTHORIZED: {
		// 				$state.go('login');
		// 				break;
		// 			}
		// 			case CODE.NO_PERMISSION: {
		// 				$state.go('forbidden');
		// 				break;
		// 			}
		// 		}
		// 	});
		// };
	};

	userDetailCtrl.$inject = ['$scope', '$state', 'ngDialog', 'veazyConfig', 'getUser', 'UserService'];
	angular.module('veazyControllers').controller('userDetailCtrl', userDetailCtrl);
})();