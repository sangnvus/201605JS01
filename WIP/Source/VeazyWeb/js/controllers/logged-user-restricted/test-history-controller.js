;(function() {
	var testHistoryCtrl = function($scope, $state, localStorageService, veazyConfig, ExamService) {
		var CODE = veazyConfig.CODE;
		ExamService.getHistoryList().then(function(response) {
			console.log(response);
			switch (response.code) {
				case CODE.SUCCESS: {
					$scope.testHistoryList = response.data.exams.reverse();
					// console.log($scope.testHistoryList);

					//pagination
					$scope.totalItems = $scope.testHistoryList.length;
					$scope.currentPage = 1;
					$scope.numPerPage = 5;
					$scope.paginate = function(value) {
						var begin, end, index;
						begin = ($scope.currentPage - 1) * $scope.numPerPage;
						end = begin + $scope.numPerPage;
						index = $scope.testHistoryList.indexOf(value);
						return (begin <= index && index < end);
					};
					break;
				}
				case CODE.UNAUTHORIZED: {
					$state.go('login');
					localStorageService.remove('username');
				}
			}
		});

		$scope.getTest = function(exam) {
			var examId = exam.examId;
			ExamService.getHistory(examId).then(function(response) {
				console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						exam.listQuestion = response.data.listQuestions;
						break;
					}

					case CODE.UNAUTHORIZED: {

					}

					default: {

					}
				} 
			});
		};
	};

	testHistoryCtrl.$inject = ['$scope', '$state', 'localStorageService', 'veazyConfig', 'ExamService'];

	angular.module('veazyControllers').controller('testHistoryCtrl', testHistoryCtrl);
})();