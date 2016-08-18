;(function() {
	var app = angular.module('veazyApp');

	app.config(['$translateProvider', function($translateProvider) {	
		$translateProvider.useStaticFilesLoader({
			prefix: 'lang/locale-',
			suffix: '.json'
		});

		$translateProvider.useSanitizeValueStrategy('escape');
		$translateProvider.preferredLanguage('en');
		// console.log('here')
		// $translateProvider.useCookieStorage();
		$translateProvider.useLocalStorage();
	}]);
})();