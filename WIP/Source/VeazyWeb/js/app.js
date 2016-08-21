;(function() {
	'use strict';

	var app = angular.module('veazyApp', ['ngRoute', 'veazyControllers', 'veazyDirectives', 'veazyFilters', 'veazyFactories', 
		'veazyServices', 'ngAnimate', 'ngDialog', 'ngSanitize', 'froala', 'ui.router', 'ui.bootstrap', 'pascalprecht.translate', 
		'ngCookies', 'chart.js', 'ngResource', 'angular-md5', 'validation.match', 'ui.validate', 'LocalStorageModule', 
		'ae-datetimepicker', 'angularFileUpload', 'ui.checkbox', 'ncy-angular-breadcrumb']);

	
	// app.run(['$rootScope', '$state','ngDialog', function($rootScope, $state, ngDialog) {
	// 	$rootScope.$on('$stateChangeSuccess', function(event, newState, newStateParams, oldState, oldStateParams) {
	// 		// ngDialog.close();
	// 	});
	// }]);

	app.config(['ChartJsProvider', function(ChartJsProvider) {
		ChartJsProvider.setOptions({ colours : [ '#ee4035', '#f37736', '#fdf498', '#7bc043', '#0392cf', '#949FB1', '#4D5360'] });
	}]);

	app.config(['$httpProvider', function($httpProvider) {
		$httpProvider.defaults.withCredentials = true;
	}]);

	// app.config(['$provide', function($provide) {
	// 	$provide.decorator('$uiViewScroll')
	// }])

	// app.run(['$rootScope', function($rootScope){
	// 	$rootScope.$on('$stateChangeSuccess', function() {
	// 	    document.body.scrollTop = 0;
	// 	    document.documentElement.scrollTop = 0;
	// 	});
	// }]);

	app.value('froalaConfig', {
		language: 'en',
		placeholderText: ''
		// imageUploadURL: 'http://163.44.172.52:8080/Veazy/api/uploadfile'
	});
})();
