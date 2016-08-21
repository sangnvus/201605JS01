;(function() {
	'use strict';
	var registerSuccessCtrl = function($scope, getUser) {
		$scope.user = getUser.data;
	};

	registerSuccessCtrl.$inject = ['$scope', 'getUser'];
	angular.module('veazyControllers').controller('registerSuccessCtrl', registerSuccessCtrl);
})();