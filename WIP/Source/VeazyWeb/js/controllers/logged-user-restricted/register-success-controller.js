;(function() {
	'use strict';
	var registerSuccessCtrl = function($scope, getUser) {
		$scope.user = getUser.data;
		console.log($scope.user);
	};

	registerSuccessCtrl.$inject = ['$scope', 'getUser'];
	angular.module('veazyControllers').controller('registerSuccessCtrl', registerSuccessCtrl);
})();