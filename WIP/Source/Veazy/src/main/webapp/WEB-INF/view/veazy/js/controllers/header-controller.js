;(function() {
	'use strict';
	var headerCtrl = function($scope, $location) {
		$scope.showCourseListPage = function() {
			$location.path('/courselist');
		};

		$scope.showHomePage = function() {
			$location.path('/home');
		};

		$scope.showLoginPage = function() {
			$location.path('/login');
		};

		$scope.showDoTestPage = function() {
			$location.path('/test');
		};
	};

	headerCtrl.$inject = ['$scope', '$location'];

	angular.module('veazyControllers').controller('headerCtrl', headerCtrl);
})();