;(function() {
	'use strict';

	var searchLessonCtrl = function($scope, $location, ngDialog) {
		//Handle data
		$scope.showLessonDetailPage = function() {
			$location.path('/courselist/lessondetail');
			ngDialog.close();
		};
	};

	searchLessonCtrl.$inject = ['$scope', '$location', 'ngDialog'];


	angular.module('veazyControllers').controller('searchLessonCtrl', searchLessonCtrl);
})();