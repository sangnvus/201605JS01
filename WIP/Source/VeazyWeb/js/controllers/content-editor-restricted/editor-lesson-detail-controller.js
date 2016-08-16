;(function() {
	'use strict';
	var editorLessonDetailCtrl = function($scope, $sce, getLesson, ngDialog) {
		$scope.lesson = getLesson.data;

		$scope.trustAsHtml = function(str) {
			return $sce.trustAsHtml(str);
		};

		$scope.openConfirmDeleteDialog = function() {
			ngDialog.open({
				template: 'partials/content-editor-restricted/delete-lesson.html',
				className: 'ngdialog-theme-default delete-lesson-dialog',
				showClose: true,
				closeByDocument: false,
				width: 500,
				controller: 'deleteLessonDialogCtrl',
				data: {
					lesson: $scope.lesson
				}
			})
		};
	};

	editorLessonDetailCtrl.$inject = ['$scope', '$sce', 'getLesson', 'ngDialog'];
	angular.module('veazyControllers').controller('editorLessonDetailCtrl', editorLessonDetailCtrl);
})();