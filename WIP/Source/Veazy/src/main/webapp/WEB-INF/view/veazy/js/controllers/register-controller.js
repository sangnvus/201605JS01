;(function() {
	'use strict';
	var registerCtrl = function($scope, $location) {
		$scope.showHomePage = function() {
			$location.path('/home');
		};

		$scope.showLoginPage = function() {
			$location.path('/login');
		};
	};

	registerCtrl.$inject = ['$scope', '$location'];

	angular.module('veazyControllers').controller('registerCtrl', registerCtrl);
})();