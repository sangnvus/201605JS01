;(function() {
	'use strict';

	var loginCtrl = function($scope, $state, UserService, md5, veazyConfig, localStorageService) {
		
		$scope.login = function() {
			var CODE = veazyConfig.CODE;
			var user = {
				username: $scope.username,
				password: md5.createHash($scope.password || '')
			};

			UserService.login(user).then(function(response) {
				localStorageService.set('username', $scope.username);
				switch (response.data.role) {
					//if user --> redirect to home
					case CODE.USER: {
						$state.go('home');
						break;
					}

					//if editor --> redirect to editor dashboard
					case CODE.EDITOR: {
						$state.go('editordashboard.reportlist');
						break;
					}

					//if admin --> redirect to admin dashboard
					case CODE.ADMIN: {
						$state.go('admin.dashboard');
						break;
					}
					default: {

					}
				}
			}, function(reject) {
				$scope.errorMsg = reject;
			});
		};
	};

	loginCtrl.$inject = ['$scope', '$state', 'UserService', 'md5', 'veazyConfig', 'localStorageService'];

	angular.module('veazyControllers').controller('loginCtrl', loginCtrl);
})();