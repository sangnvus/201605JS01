;(function() {
	editorSideBarCtrl = function($scope, $state, $translate, UserService, veazyConfig, localStorageService) {
		var CODE = veazyConfig.CODE;

		$scope.username = localStorageService.get('username');
		
		//click change language
		$scope.changeLanguage = function() {
			var currentLang = $translate.use();
			if (currentLang === 'en') {
				$translate.use('ja').then(function() {
					$state.reload();
				});
			} else if (currentLang === 'ja') {
				$translate.use('en').then(function() {
					$state.reload();
				});
			}
		};

		//click logout
		$scope.logout = function() {
			UserService.logout().then(function(response) {

				switch (response.code) {
					case CODE.SUCCESS: {
						localStorageService.remove('username');
						$state.go('login');
						break;
					}
					default: {
						
					}
				}
			});
		};
	};

	editorSideBarCtrl.$inject = ['$scope', '$state', '$translate', 'UserService', 'veazyConfig', 'localStorageService'];

	angular.module('veazyControllers').controller('editorSideBarCtrl', editorSideBarCtrl);
})();