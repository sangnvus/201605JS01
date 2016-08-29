;(function() {
	'use strict';
	var addLessonCtrl = function($scope, $state, veazyConfig, $filter, LessonService, Validator, ngDialog) {
		$scope.sections = veazyConfig.lessonSections;
		$scope.levels = veazyConfig.levels;
		$scope.selectedLevel = $scope.levels[0];

		$scope.editorOptions = {
			vocabulary: {
				imageUploadURL: '/Veazy/uploadfile',
				placeholderText: $filter('translate')('VOCABULARY_SECTION_PLACEHOLDER')
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

		$scope.lesson = {};

		$scope.addLesson = function() {
			var CODE = veazyConfig.CODE;
			var lesson = $scope.lesson;
			lesson.courseId = $scope.selectedLevel.id;

			//validate if lesson data is null or has blank content
			if (Validator.isNullLesson(lesson)) {
				$scope.errorMsg = 'MISSING_ADD_LESSON_FIELD';
			} else {
				//send request to server
				LessonService.create(lesson).then(function(response) {
					// console.log(response);
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

						case CODE.NO_PERMISSON: {
							$state.go('forbidden');
						}
						default: {
							
						}
					}
				});
			}
		};
	};

	addLessonCtrl.$inject = ['$scope', '$state', 'veazyConfig', '$filter', 'LessonService', 'Validator', 'ngDialog'];
	angular.module('veazyControllers').controller('addLessonCtrl', addLessonCtrl);
})();