(function() {
	'use strict';
	var userManagementCtrl = function($scope, UserService, veazyConfig, ngDialog, $state, $filter) {
		// $scope.uploader = new FileUploader();
		$scope.CODE = veazyConfig.CODE;
		var CODE = $scope.CODE;

		$scope.roles = veazyConfig.roles.slice(0);
		$scope.roles.splice(0, 0, {id: 0, name: 'ALL_ROLES'});
		$scope.selectedRole = $scope.roles[0];

		$scope.statuses = veazyConfig.statuses.slice(0);
		$scope.statuses.splice(0, 0, {id: 0, name: 'ALL_STATUSES'});
		$scope.selectedStatus = $scope.statuses[0];

		UserService.getUserList().then(function(response) {
			switch (response.code) {
				case CODE.SUCCESS: {
					// console.log(response.data);
					$scope.userList = response.data.listUsers;
					for (var i = 0; i < $scope.userList.length; i++) {
						if ($scope.userList[i].role === CODE.ADMIN) {
							$scope.userList.splice(i, 1);
						}
					}
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
		$scope.openUserDetailDialog = function(userId) {
			ngDialog.open({
				template: 'partials/admin-restricted/user-detail-dialog.html',
				className: 'ngdialog-theme-default update-profile-dialog',
				showClose: true,
				closeByDocument: false,
				controller: 'userDetailCtrl',
				width: 700,
				resolve: {
					getUser: function($timeout, $q, veazyConfig, UserService) {
						// UserService.getUserDetail()
						// return UserService.getUserDetail(userId);
						var CODE = veazyConfig.CODE;
						var deferred = $q.defer();
						$timeout(function() {
							UserService.getUserDetail(userId).then(function(response) {
								switch (response.code) {
									case CODE.SUCCESS: {
										deferred.resolve(response.data);
										break;
									}
									case CODE.UNAUTHORIZED: {
										deferred.reject();
										$state.go('login');
										break;
									}
									case CODE.NO_PERMISSION: {
										deferred.reject();
										$state.go('forbidden');
										break;
									}
								}
							}, function(reject) {
								deferred.reject();
							});
						});
						return deferred.promise;
					}
				}
			}).closePromise.then(function() {
				$state.reload();
			});
		};

		$scope.filterUser = function() {
			var role = $scope.selectedRole.id;
			var status = $scope.selectedStatus.id;
			var keyword = $scope.keyword;

			$scope.filteredUserList = $filter('user')($scope.userList, role, status, keyword);
		};

		$scope.banUser = function(user) {
			UserService.ban(user.id).then(function(response) {
				switch (response.code) {
					case CODE.SUCCESS: {
						user.isBanned = true;
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

		$scope.unbanUser = function(user) {
			UserService.unban(user.id).then(function(response) {
				switch (response.code) {
					case CODE.SUCCESS: {
						user.isBanned = false;
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

		$scope.assignAsUser = function(user) {
			var userCode = veazyConfig.CODE.USER;
			var userId = user.id;
			UserService.changeRole(userId, userCode).then(function(response) {
				// console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						// user.role = userCode;
						$state.reload();
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

		$scope.assignAsEditor = function(user) {
			var editorCode = veazyConfig.CODE.EDITOR;
			var userId = user.id;
			UserService.changeRole(userId, editorCode).then(function(response) {
				// console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						// user.role = editorCode;
						$state.reload();
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

		// $scope.assignAsAdmin = function(user) {
		// 	var adminCode = veazyConfig.CODE.ADMIN;
		// 	var userId = user.id;
		// 	UserService.changeRole(userId, adminCode).then(function(response) {
		// 		// console.log(response);
		// 		switch (response.code) {
		// 			case CODE.SUCCESS: {
		// 				user.role = adminCode;
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

	userManagementCtrl.$inject = ['$scope', 'UserService', 'veazyConfig', 'ngDialog', '$state', '$filter'];

	angular.module('veazyControllers').controller('userManagementCtrl', userManagementCtrl);
})();