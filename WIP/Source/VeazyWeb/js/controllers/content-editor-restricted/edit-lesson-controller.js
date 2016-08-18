;(function() {
	var editLessonCtrl = function($scope, $state, Lesson, veazyConfig, getLesson, ValidateHelper, ngDialog) {
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
			var lesson = new Lesson({
				lessonId: $scope.lesson.lessonId,
				courseId: $scope.selectedLevel.id,
				title: $scope.lesson.lessonTitle,
				description: $scope.lesson.description,
				vocabulary: $scope.lesson.vocabulary,
				grammar: $scope.lesson.grammar,
				conversation: $scope.lesson.conversation,
				listening: $scope.lesson.listening,
				practice: $scope.lesson.practice,
				reading: $scope.lesson.reading
				// version: $scope.lesson.version
			});

			//validate if data is null
			// var isNullLesson = ValidateHelper.isNullLesson(lesson);
			// console.log(isNullLesson);

			// if (isNullLesson) {
			// 	//alert error
			// 	ngDialog.open({
			// 		template: 'partials/content-editor-restricted/error-alert.html',
			// 		className: 'ngdialog-theme-default error-dialog',
			// 		showClose: false,
			// 		closeByDocument: false,
			// 		disableAnimation: true,
			// 		overlay: false,
			// 		width: 400,
			// 		data: {
			// 			errorMsg: 'MISSING_UPDATE_LESSON_FIELD'
			// 		},
			// 		controller: 'errorDialogCtrl'
			// 	});
			// } else {
				lesson.$update(function(response) {
					console.log(response);
					var CODE = veazyConfig.CODE;
					switch (response.code) {
						case CODE.SUCCESS: {
							$state.go('editordashboard.lesson.detail', {
								id: $scope.lesson.lessonId
							});
							break;
						}
					}
				}, function() {

				});
			// }
		};

		$scope.saveDraft = function() {
			var lesson = new Lesson({
				lessonId: $scope.lesson.lessonId,
				courseId: $scope.selectedLevel.id,
				title: $scope.lesson.lessonTitle,
				description: $scope.lesson.description,
				vocabulary: $scope.lesson.vocabulary,
				grammar: $scope.lesson.grammar,
				conversation: $scope.lesson.conversation,
				listening: $scope.lesson.listening,
				practice: $scope.lesson.practice,
				reading: $scope.lesson.reading
				// version: $scope.lesson.version
			});

			console.log(lesson);

			lesson.$saveDraft(function(response) {
				console.log(response);
				var CODE = veazyConfig.CODE;
				switch (response.code) {
					case CODE.SUCCESS: {
						$state.go('editordashboard.lesson.detail', {
							id: $scope.lesson.lessonId
						});
						break;
					}
				}
			}, function() {

			});
		};

		$scope.editorOptions = {
			vocabulary: {
				// placeholderText: $filter('translate')('VOCABULARY_SECTION_PLACEHOLDER')
				// imageUploadMethod: 'POST',
				// imageUploadParam: 'files',
				imageUploadURL: 'http://163.44.172.52:8080/Veazy/uploadfile'
			},
			grammar: {
				imageUploadURL: 'http://163.44.172.52:8080/Veazy/uploadfile'
				// placeholderText: $filter('translate')('GRAMMAR_SECTION_PLACEHOLDER')
			},
			conversation: {
				imageUploadURL: 'http://163.44.172.52:8080/Veazy/uploadfile'
				// placeholderText: $filter('translate')('CONVERSATION_SECTION_PLACEHOLDER')
			},
			listening: {
				imageUploadURL: 'http://163.44.172.52:8080/Veazy/uploadfile'
				// placeholderText: $filter('translate')('LISTENING_SECTION_PLACEHOLDER')
			},
			practice: {
				imageUploadURL: 'http://163.44.172.52:8080/Veazy/uploadfile'
				// placeholderText: $filter('translate')('PRACTICE_SECTION_PLACEHOLDER')
			},
			reading: {
				imageUploadURL: 'http://163.44.172.52:8080/Veazy/uploadfile'
				// placeholderText: $filter('translate')('READING_SECTION_PLACEHOLDER')
			},
		};
	};

	editLessonCtrl.$inject = ['$scope', '$state', 'Lesson', 'veazyConfig', 'getLesson', 'ValidateHelper', 'ngDialog'];
	angular.module('veazyControllers').controller('editLessonCtrl', editLessonCtrl);
})();