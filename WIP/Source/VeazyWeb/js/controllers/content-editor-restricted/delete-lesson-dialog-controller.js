;(function() {
	var deleteLessonDialogCtrl = function($scope, $state, ngDialog, Lesson, veazyConfig) {
		$scope.lesson = $scope.ngDialogData.lesson;

		$scope.deleteLesson = function() {
			var lesson = new Lesson();
			var CODE = veazyConfig.CODE;

			lesson.lessonId = $scope.lesson.lessonId;
			lesson.$delete(function(response) {
				console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						ngDialog.close();
						$state.go('editor.lesson.list', {}, {reload: true});
						break;
					}
					default: {
						
					}
				}
			}, function() {

			});
		};

		$scope.closeDialog = function() {
			ngDialog.close();
		};
	};
	

	deleteLessonDialogCtrl.$inject = ['$scope', '$state', 'ngDialog', 'Lesson', 'veazyConfig'];
	angular.module('veazyControllers').controller('deleteLessonDialogCtrl', deleteLessonDialogCtrl);
})();