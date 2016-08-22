;(function() {
	'use strict';
	var takeTestCtrl = function($scope, $state, $interval, veazyConfig, getExam, ExamService, Helper) {
		$scope.CODE = veazyConfig.CODE;

		// $scope.exam = getExam.data;
		$scope.exam = $state.params.exam || getExam.data;

		$scope.trustAsHtml = Helper.trustAsHtml;

		//time duration of test in seconds
		var seconds = $scope.exam.etaTime;
		var countDown;

		if (seconds > 0) {
			$scope.duration = moment.duration(seconds, 'seconds').format('hh:mm:ss', {trim: false});

			//countdown interval
			countDown = $interval(function() {
				seconds--;

				//if running out of time --> auto submit test
				if (seconds < 0) {
					seconds++;
					// $interval.cancel(countDown);
					$scope.submitTest();
				} else {
					$scope.duration = moment.duration(seconds, 'seconds').format('hh:mm:ss', {trim: false});
				}
			}, 1000);
		}

		//make sure to stop interval on leaving
		$scope.$on('$destroy', function() {
			$interval.cancel(countDown);
		});

		$scope.submitTest = function() {
			$interval.cancel(countDown);
			var exam = $scope.exam;
			exam.takenTime = seconds;

			console.log(exam);
			if (exam.offlineCheck) {
				exam.totalPts = Helper.calculateExamMark($scope.exam);
				$state.go('test.testresult', {
					examResult: exam
				});
			} else {
				ExamService.submit(exam).then(function(response) {
					console.log(response);
					$state.go('test.testresult', {
						examResult: response.data
					});
				});
			}
		};
	};

	takeTestCtrl.$inject = ['$scope', '$state', '$interval', 'veazyConfig', 'getExam', 'ExamService', 'Helper'];
	angular.module('veazyControllers').controller('takeTestCtrl', takeTestCtrl);
})();