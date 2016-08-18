;(function() {
	'use strict';
	var headerCtrl = function($scope, $translate, $state, User, localStorageService, veazyConfig) {
		var CODE = veazyConfig.CODE;
		var currentLang = $translate.use();
		
		$scope.showEnglishBrand = currentLang === 'ja';
		$scope.showJapaneseBrand = currentLang === 'en';
		// console.log($scope.showEnglishBrand, $scope.showJapaneseBrand);

		//click change language
		$scope.changeLanguage = function(keyLang) {
			$translate.use(keyLang).then(function() {
				$state.reload();
			});
		};

		//if not logged in, this = null
		$scope.username = localStorageService.get('username');

		//click log out
		$scope.logout = function() {
			var user = new User();
			user.$logout(function(response) {
				console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {

					}

					case CODE.UNAUTHORIZED: {
						localStorageService.remove('username');
						$state.go('login');
					}
					default: {

					}
				}
			}, function() {
				console.log('error');
			});
		};
	};
	headerCtrl.$inject = ['$scope', '$translate', '$state', 'User', 'localStorageService', 'veazyConfig'];

	angular.module('veazyControllers').controller('headerCtrl', headerCtrl);
})();