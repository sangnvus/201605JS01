;(function() {
	'use strict';
	var registerCtrl = function($scope, $state, veazyConfig, UserService, md5, localStorageService) {
		$scope.REGULATIONS = veazyConfig.REGULATIONS;

		$scope.register = function() {
			var CODE = veazyConfig.CODE;
			var user = {};
			user.username = $scope.username;
			user.password = md5.createHash($scope.password || '');
			user.email = $scope.email;

			UserService.register(user).then(function(registerResponse) {
				UserService.login(user).then(function(loginResponse) {
					localStorageService.set('username', $scope.username);
					switch (loginResponse.data.role) {
						case CODE.USER: {
							$state.go('home');
							break;
						}
						case CODE.EDITOR: {
							$state.go('editordashboard.reportlist');
							break;
						}
						default: {

						}
					}
				}, function(reject) {
					$scope.errorMsg = reject;
				});
			}, function(reject) {
				$scope.errorMsg = reject;
			});
		};
	};

	registerCtrl.$inject = ['$scope', '$state', 'veazyConfig','UserService', 'md5', 'localStorageService'];

	angular.module('veazyControllers').controller('registerCtrl', registerCtrl);
})();