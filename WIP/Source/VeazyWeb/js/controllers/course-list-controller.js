;(function() {
	'use strict';
	var courseListCtrl = function($scope, getLessonList, $stateParams) {
		$scope.lessonList = getLessonList.data;

		$scope.courseId = $stateParams.courseId;
	};

	courseListCtrl.$inject = ['$scope', 'getLessonList', '$stateParams'];

	angular.module('veazyControllers').controller('courseListCtrl', courseListCtrl);
})();