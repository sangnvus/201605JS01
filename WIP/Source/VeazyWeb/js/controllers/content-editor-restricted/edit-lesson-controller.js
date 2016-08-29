;(function() {
	var editLessonCtrl = function($scope, $state, LessonService, veazyConfig, getLesson, Validator, ngDialog) {
		$scope.lesson = getLesson.data;

		$scope.levels = veazyConfig.levels;
		for (var i = 0; i < $scope.levels.length; i++) {
			if ($scope.lesson.courseId === $scope.levels[i].id) {
				$scope.selectedLevel = $scope.levels[i];
				break;
			}
		};

		$scope.updateLesson = function() {
			// console.log($scope.lesson.update)
			var lesson = $scope.lesson;
			lesson.title = $scope.lesson.lessonTitle;
			lesson.courseId = $scope.selectedLevel.id;

			if (Validator.isNullLesson(lesson)) {
				$scope.errorMsg = 'MISSING_UPDATE_LESSON_FIELD';
			} else {
				LessonService.update(lesson).then(function(response) {
					console.log(response);
					var CODE = veazyConfig.CODE;
					switch (response.code) {
						case CODE.SUCCESS: {
							$state.go('editor.lesson.detail', {
								id: $scope.lesson.lessonId
							});
							break;
						}

						case CODE.UNAUTHORIZED: {
							$state.go('login');
							break;
						}

						case CODE.NO_PERMISSION: {
							$state.go('forbidden');
							break;
						}
					}
				});
			}
		};

		$scope.saveDraft = function() {
			var lesson = $scope.lesson;
			lesson.title = $scope.lesson.lessonTitle;
			lesson.courseId = $scope.selectedLevel.id;

			if (Validator.isNullLesson(lesson)) {
				$scope.errorMsg = 'MISSING_SAVE_DRAFT_FIELD';
			} else {
				LessonService.saveDraft(lesson).then(function(response) {
					console.log(response);
					var CODE = veazyConfig.CODE;
					switch (response.code) {
						case CODE.SUCCESS: {
							$state.go('editor.lesson.detail', {
								id: $scope.lesson.lessonId
							});
							break;
						}
						case CODE.UNAUTHORIZED: {
							$state.go('login');
							break;
						}
						case CODE.NO_PERMISSION: {
							$state.go('forbidden');
							break;
						}
					}
				});
			};
		};

		$scope.editor = {
			imageUploadURL: '/Veazy/uploadfile'
		};
	};

	editLessonCtrl.$inject = ['$scope', '$state', 'LessonService', 'veazyConfig', 'getLesson', 'Validator', 'ngDialog'];
	angular.module('veazyControllers').controller('editLessonCtrl', editLessonCtrl);
})();