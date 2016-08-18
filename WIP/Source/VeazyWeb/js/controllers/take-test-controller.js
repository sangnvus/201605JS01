;(function() {
	'use strict';
	var takeTestCtrl = function($scope, $state, $interval, veazyConfig, getExam, ExamService, Helper) {
		$scope.CODE = veazyConfig.CODE;
		// var CODE = veazyConfig.CO/DE;

		// $scope.exam = getExam.data;
		$scope.exam = $state.params.exam;
		// console.log($state.params);

		$scope.trustAsHtml = Helper.trustAsHtml;

		//time duration of test in seconds
		var seconds = $scope.exam.etaTime;

		if (seconds > 0) {
			$scope.duration = moment.duration(seconds, 'seconds').format('hh:mm:ss');

			//countdown interval
			var countDown = $interval(function() {
				// console.log('interval');
				seconds--;
				if (seconds < 0) {
					seconds++;
					$interval.cancel(countDown);
					$scope.submitTest();
				} else {
					$scope.duration = moment.duration(seconds, 'seconds').format('hh:mm:ss');
				}
			}, 1000);
		}

		//make sure to stop interval on leaving
		$scope.$on('$destroy', function() {
			$interval.cancel(countDown);
		});

		$scope.submitTest = function() {
			var exam = $scope.exam;
			exam.totalPts = Helper.calculateExamMark($scope.exam);
			// console.log($scope.exam);
			// console.log(Helper.checkIfUserCheckedAllQuestions(exam));

				// console.log('here');
			console.log(exam);
			if (exam.offlineCheck) {
				$state.go('test.testresult', {
					examResult: exam
				});
			} else {

			}
		};
	};

	takeTestCtrl.$inject = ['$scope', '$state', '$interval', 'veazyConfig', 'getExam', 'ExamService', 'Helper'];
	angular.module('veazyControllers').controller('takeTestCtrl', takeTestCtrl);
})();