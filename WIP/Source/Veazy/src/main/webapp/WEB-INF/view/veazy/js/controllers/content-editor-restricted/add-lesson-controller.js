;(function() {
	'use strict';
	var addLessonCtrl = function($scope, $timeout, veazyConfig) {
		$scope.sections = veazyConfig.lessonSections;
		$scope.levels = veazyConfig.levels;
		$scope.selectedLevel = $scope.levels[0];

		$scope.newLesson = {};

		$scope.froalaEditorOptions = [{
			placeholderText: 'Here comes the content of vocabulary section'
		}, {
			placeholderText: 'Here comes the content of grammar section'
		}, {
			placeholderText: 'Here comes the content of conversation section'
		}, {
			placeholderText: 'Here comes the content of listening section'
		}, {
			placeHolderText: 'Here comes the content of practice section'
		}];
	};

	addLessonCtrl.$inject = ['$scope', '$timeout','veazyConfig'];
	angular.module('veazyControllers').controller('addLessonCtrl', addLessonCtrl);
})();