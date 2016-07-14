;(function() {
	'use strict';
	var testCtrl = function($scope, veazyConfig) {
		// $scope.courses = ['初級', '', '', '', '', ''];
		$scope.levels = veazyConfig.levels;
		$scope.selectedLevel = $scope.levels[0];

		$scope.testSkills = veazyConfig.testSkills;
		$scope.selectedTestSkill = $scope.testSkills[0];

		$scope.setCourse = function(course) {
			$scope.selectedCourse = course;
		};

		$scope.questions = [{
			content: 'ベトナムで「犬」というのは読んでいますか。',
			answers: ['Dog', 'Bird', 'Chimpanzee', 'Gorilla']
		}, {
			content: 'ベトナムで「犬」というのは読んでいますか。',
			answers: ['Dog', 'Bird', 'Gorilla', 'Chimpanzee']
		}];
	};

	testCtrl.$inject = ['$scope', 'veazyConfig'];
	angular.module('veazyControllers').controller('testCtrl', testCtrl);
})();