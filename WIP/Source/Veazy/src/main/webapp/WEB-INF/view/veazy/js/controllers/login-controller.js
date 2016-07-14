;(function() {
	'use strict';

	var loginCtrl = function($scope, $location) {
		$scope.showHomePage = function() {
			$location.path('/home');
		};

		$scope.showRegisterPage = function() {
			$location.path('/register');
		};
	};

	loginCtrl.$inject = ['$scope', '$location'];

	angular.module('veazyControllers').controller('loginCtrl', loginCtrl);
})();