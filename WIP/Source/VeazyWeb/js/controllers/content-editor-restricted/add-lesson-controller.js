;(function() {
	'use strict';
	var addLessonCtrl = function($scope, $state, veazyConfig, $filter, LessonService, ValidateHelper, ngDialog) {
		$scope.sections = veazyConfig.lessonSections;
		$scope.levels = veazyConfig.levels;
		$scope.selectedLevel = $scope.levels[0];

		$scope.editorOptions = {
			vocabulary: {
				placeholderText: $filter('translate')('VOCABULARY_SECTION_PLACEHOLDER'),
				imageUploadURL: '/Veazy/uploadfile'
			},
			grammar: {
				imageUploadURL: '/Veazy/uploadfile',
				placeholderText: $filter('translate')('GRAMMAR_SECTION_PLACEHOLDER')
			},
			conversation: {
				imageUploadURL: '/Veazy/uploadfile',
				placeholderText: $filter('translate')('CONVERSATION_SECTION_PLACEHOLDER')
			},
			listening: {
				imageUploadURL: '/Veazy/uploadfile',
				placeholderText: $filter('translate')('LISTENING_SECTION_PLACEHOLDER')
			},
			practice: {
				imageUploadURL: '/Veazy/uploadfile',
				placeholderText: $filter('translate')('PRACTICE_SECTION_PLACEHOLDER')
			},
			reading: {
				imageUploadURL: '/Veazy/uploadfile',
				placeholderText: $filter('translate')('READING_SECTION_PLACEHOLDER')
			},
		};

		$scope.addLesson = function() {
			var CODE = veazyConfig.CODE;
			var lesson = {
				courseId: $scope.selectedLevel.id,
				title: $scope.title,
				description: $scope.description,
				vocabulary: $scope.vocabulary,
				grammar: $scope.grammar,
				conversation: $scope.conversation,
				listening: $scope.listening,
				practice: $scope.practice,
				reading: $scope.reading
			};

			//validate if data is null
			var isNullLesson = ValidateHelper.isNullLesson(lesson);
			// console.log(isNullLesson);

			if (isNullLesson) {
				//alert error
				ngDialog.open({
					template: 'partials/content-editor-restricted/error-alert.html',
					className: 'ngdialog-theme-default error-dialog',
					showClose: false,
					closeByDocument: false,
					disableAnimation: true,
					overlay: false,
					width: 400,
					data: {
						errorMsg: 'MISSING_ADD_LESSON_FIELD'
					},
					controller: 'errorDialogCtrl'
				});
			} else {
				//send request to server
				LessonService.create(lesson).then(function(response) {
					console.log(response);
					switch (response.code) {
						case CODE.SUCCESS: {
							$state.go('editor.lesson.detail', {
								id: response.data.lessonId
							});
							break;
						}
						case CODE.UNAUTHORIZED: {
							$state.go('login');
							break;
						}
						default: {
							
						}
					}
				}, function(reject) {

				});
			}
		};
	};

	addLessonCtrl.$inject = ['$scope', '$state', 'veazyConfig', '$filter', 'LessonService', 'ValidateHelper', 'ngDialog'];
	angular.module('veazyControllers').controller('addLessonCtrl', addLessonCtrl);
})();