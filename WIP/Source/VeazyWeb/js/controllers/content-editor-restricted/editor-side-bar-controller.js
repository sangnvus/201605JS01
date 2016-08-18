;(function() {
	editorSideBarCtrl = function($scope, $state, $translate, User, veazyConfig, localStorageService) {
		$scope.username =localStorageService.get('username');
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

		$scope.logout = function() {
			var CODE = veazyConfig.CODE;
			var user = new User();
			user.$logout(function(response) {
				console.log(response);

				switch (response.code) {
					case CODE.SUCCESS: {
						localStorageService.remove('username');
						$state.go('login');
						break;
					}
					default: {
						
					}
				}
			}, function() {

			})
		}
	};

	editorSideBarCtrl.$inject = ['$scope', '$state', '$translate', 'User', 'veazyConfig', 'localStorageService'];

	angular.module('veazyControllers').controller('editorSideBarCtrl', editorSideBarCtrl);
})();