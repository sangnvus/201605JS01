;(function() {
	'use strict';
	var courseListCtrl = function($scope, $location) {
		$scope.showLessonDetailPage = function() {
			$location.path('/courselist/lessondetail');
		};
	};

	courseListCtrl.$inject = ['$scope', '$location'];

	angular.module('veazyControllers').controller('courseListCtrl', courseListCtrl);
})();