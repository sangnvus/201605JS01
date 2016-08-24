;(function() {
	questionListCtrl = function($scope, $state, veazyConfig, $filter, ngDialog, QuestionService) {
		var CODE = veazyConfig.CODE;
		var MESSAGE = veazyConfig.MESSAGE;

		//call service to get question list
		QuestionService.getAllQuestions().then(function(response) {
			console.log(response);
			switch (response.code) {
				case CODE.SUCCESS: {
					$scope.questionList = $filter('orderBy')(response.data, 'questionId');
					$scope.filteredQuestionList = $scope.questionList;

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
		});

		//values of questionType select box
		$scope.questionTypes = veazyConfig.questionTypes.slice(0);
		$scope.questionTypes.splice(0, 0, {
			id: 0, 
			name: 'ALL_TYPES'
		});
		$scope.selectedQuestionType = $scope.questionTypes[0];

		//values of levelType select box
		$scope.levels = veazyConfig.levels.slice(0);
		$scope.levels.splice(0, 0, {
			id: 0, 
			name: 'ALL_LEVELS'
		});
		$scope.selectedLevel = $scope.levels[0];

		//value of testSkill select box
		$scope.testSkills = veazyConfig.testSkills.slice(0);
		$scope.testSkills.splice(0, 0, {
			id: 0, 
			name: 'ALL_SKILLS'
		});
		$scope.selectedTestSkill = $scope.testSkills[0];

		//click filter button
		$scope.filter = function() {
			var type = $scope.selectedQuestionType.id;
			var level = $scope.selectedLevel.id;
			var skill = $scope.selectedTestSkill.id;
			var keyword = $scope.keyword;
			$scope.filteredQuestionList = $filter('question')($scope.questionList, type, level, skill, keyword);
			$scope.currentPage = 1;
		};

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

	questionListCtrl.$inject = ['$scope', '$state', 'veazyConfig', '$filter', 'ngDialog', 'QuestionService'];

	angular.module('veazyControllers').controller('questionListCtrl', questionListCtrl);
})();