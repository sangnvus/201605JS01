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
					$state.go('register.success');
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