;(function() {
	'use strict';
	var testCtrl = function($scope, $state, veazyConfig, ExamService) {
		var CODE = veazyConfig.CODE;

		$scope.minNumberOfQuestions = veazyConfig.REGULATIONS.MIN_QUESTION_NUMBER_IN_EXAM;

		$scope.numberOfQuestion = $scope.minNumberOfQuestions;
		$scope.levels = veazyConfig.levels;
		$scope.selectedLevel = $scope.levels[0];

		$scope.testSkills = veazyConfig.testSkills;
		$scope.selectedTestSkill = $scope.testSkills[0];

		$scope.createExam = function() {
			//input of user when setup creating exam
			var examInput = {
				courseId: $scope.selectedLevel.id,
				skill: $scope.selectedTestSkill.id,
				numberOfQuestion: $scope.numberOfQuestion
			};

			$scope.error = null;

			//call to service
			ExamService.create(examInput).then(function(response) {
				console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						var exam = response.data;
						$state.go('test.taketest', {
							exam: response.data
						}, {
							reload:'test.taketest'
						});

						//no question of this level & skill added yet
						if (exam.etaTime === 0) {
							$scope.error = 'No question of this level & skill are added yet!';
						}
						break;
					}

					default: {
						//handling other cases
					}
				}
			}, function(reject) {
				//handling errors
			});
		};
	};

	testCtrl.$inject = ['$scope', '$state', 'veazyConfig', 'ExamService'];
	angular.module('veazyControllers').controller('testCtrl', testCtrl);
})();