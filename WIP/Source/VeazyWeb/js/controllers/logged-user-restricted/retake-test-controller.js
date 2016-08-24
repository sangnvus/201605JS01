;(function() {
	var retakeTestCtrl = function($scope, getExam, $state, Helper, $interval, $filter, ExamService, veazyConfig) {
		$scope.CODE = veazyConfig.CODE;
		var CODE = $scope.CODE;

		// $scope.exam = getExam.data;
		$scope.exam = getExam.data;

		//set all answer to unselected state
		var skill = $scope.exam.skill;
		switch (skill) {
			//reading question
			case CODE.READING_SKILL: {
				$scope.exam.listQuestions.forEach(function(groupQuestion) {
					groupQuestion.listQuestions.forEach(function(singleQuestion) {
						singleQuestion.listAnswer.forEach(function(answer) {
							answer.isSelected = false;
						});
					});
				});
				break;
			}
			//vocab, grammar & listening question
			default: {
				$scope.exam.listQuestions.forEach(function(singleQuestion) {
					singleQuestion.listAnswers.forEach(function(answer) {
						answer.isSelected = false;
					});
				});
			}
		}

		$scope.trustAsHtml = Helper.trustAsHtml;

		//time duration of test in seconds
		var seconds = $scope.exam.etaTime;
		var countDown;

		if (seconds > 0) {
			$scope.duration = $filter('timeDuration')(seconds);

			//countdown interval
			countDown = $interval(function() {
				seconds--;

				//if running out of time --> auto submit test
				if (seconds < 0) {
					seconds++;
					$interval.cancel(countDown);
					$scope.submitTest();
				} else {
					$scope.duration = $filter('timeDuration')(seconds);
				}
			}, 1000);
		}

		//make sure to destroy interval upon leaving
		$scope.$on('$destroy', function() {
			$interval.cancel(countDown);
		});

		$scope.submitTest = function() {
			$interval.cancel(countDown);
			var exam = $scope.exam;
			exam.takenTime = seconds;

			ExamService.submit(exam).then(function(response) {
				console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						//go to page which display result of test retaken
						$state.go('test.retakeresult', {
							examId: $scope.exam.examId, 
							examResult: response.data
						});
						break;
					}
					case CODE.UNAUTHORIZED: {
						$state.go('login');
						break;
					}
					default: {
						//handling other case
					}
				}
			});
		};
	};

	retakeTestCtrl.$inject = ['$scope', 'getExam', '$state', 'Helper', '$interval', '$filter', 'ExamService', 'veazyConfig'];
	angular.module('veazyControllers').controller('retakeTestCtrl', retakeTestCtrl);
})();