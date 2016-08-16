;(function() {
	questionListCtrl = function($scope, $state, veazyConfig, filterFilter, ngDialog, QuestionService) {
		var CODE = veazyConfig.CODE;
		var MESSAGE = veazyConfig.MESSAGE;

		QuestionService.getAllQuestions().then(function(response) {
			console.log(response);
			switch (response.code) {
				case CODE.SUCCESS: {
					$scope.questionList = response.data;

					//pagination
					$scope.totalItems = $scope.questionList.length;
					$scope.currentPage = 1;
					$scope.numPerPage = 5;

					$scope.paginate = function(value) {
						var begin, end, index;
						begin = ($scope.currentPage - 1) * $scope.numPerPage;
						end = begin + $scope.numPerPage;
						index = $scope.questionList.indexOf(value);
						return (begin <= index && index < end);
					};
					break;
				}

				case CODE.UNAUTHORIZED: {
					$state.go('login');
					break;
				}

				default: {
					$scope.errorMsg = MESSAGE[response.code];
				}
			}
		}, function(reject) {
			$scope.errorMsg = reject;
		});

		$scope.$watch('search.keyword', function(value) {
			// $scope.filteredQuestionList = filterFilter($scope.questionList, value);
			// $scope.currentPage = 1;
		});

		$scope.openConfirmDeleteDialog = function(question) {
			ngDialog.open({
				template: 'partials/content-editor-restricted/delete-question.html',
				className: 'ngdialog-theme-default delete-lesson-dialog',
				showClose: true,
				closeByDocument: false,
				width: 500,
				controller: 'deleteQuestionCtrl',
				data: {
					question: question
				}
			})
		};
	};

	questionListCtrl.$inject = ['$scope', '$state', 'veazyConfig', 'filterFilter', 'ngDialog', 'QuestionService'];

	angular.module('veazyControllers').controller('questionListCtrl', questionListCtrl);
})();