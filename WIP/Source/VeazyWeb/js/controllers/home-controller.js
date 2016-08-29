;(function() {
	'usestrict';
	var homeCtrl = function($scope, User, localStorageService) {
		$scope.username = localStorageService.get('username');
	};

	homeCtrl.$inject = ['$scope', 'User', 'localStorageService'];

	angular.module('veazyControllers').controller('homeCtrl', homeCtrl);
})();