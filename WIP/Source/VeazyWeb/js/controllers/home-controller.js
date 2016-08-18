;(function() {
	'usestrict';
	var homeCtrl = function($scope, User, localStorageService) {
		$scope.username = localStorageService.get('username');

		$scope.makeEditor = function() {
			var user = new User();
			user.username = 'wanda_maximoff';
			user.$makeEditor(function(response) {
				console.log(response);
			}, function() {

			});
		};
	};

	homeCtrl.$inject = ['$scope', 'User', 'localStorageService'];

	angular.module('veazyControllers').controller('homeCtrl', homeCtrl);
})();