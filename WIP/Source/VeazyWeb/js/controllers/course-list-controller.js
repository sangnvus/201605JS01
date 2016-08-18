;(function() {
	'use strict';
	var courseListCtrl = function($scope, $stateParams, $filter, LessonService, veazyConfig) {
		// $scope.lessonList = getLessonList.data;
		var courseId = $stateParams.courseId;
		var CODE = veazyConfig.CODE;
		var MESSAGE = veazyConfig.MESSAGE;

		$scope.courseName = $filter('translate')($filter('level')(courseId));

		LessonService.getLessonsInCourse(courseId).then(function(response) {
			$scope.lessonList = response.data;
		}, function(reject) {
			$scope.errorMsg = reject;
		});
	};

	courseListCtrl.$inject = ['$scope', '$stateParams', '$filter', 'LessonService', 'veazyConfig'];

	angular.module('veazyControllers').controller('courseListCtrl', courseListCtrl);
})();