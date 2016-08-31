;(function() {
	'use strict';
	var courseListCtrl = function($scope, getLessonList) {
		$scope.lessonList = getLessonList.data;
	};

	courseListCtrl.$inject = ['$scope', 'getLessonList'];

	angular.module('veazyControllers').controller('courseListCtrl', courseListCtrl);
})();