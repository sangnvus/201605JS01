;(function() {
	'use strict';
	var headerCtrl = function($scope, $translate, $state, UserService, localStorageService, veazyConfig) {
		var CODE = veazyConfig.CODE;

		//check current lang
		var currentLang = $translate.use();
		$scope.showEnglishBrand = currentLang === 'ja';
		$scope.showJapaneseBrand = currentLang === 'en';

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
			UserService.logout().then(function(response) {
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
			});
		};
	};
	headerCtrl.$inject = ['$scope', '$translate', '$state', 'UserService', 'localStorageService', 'veazyConfig'];

	angular.module('veazyControllers').controller('headerCtrl', headerCtrl);
})();