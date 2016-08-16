;(function() {
	var userCtrl =  function($scope, localStorageService) {
		$scope.username = localStorageService.get('username');
	};

	userCtrl.$inject = ['$scope', 'localStorageService'];
	angular.module('veazyControllers').controller('userCtrl', userCtrl);
})();