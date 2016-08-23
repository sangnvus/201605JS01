;(function() {
	'use strict';
	var questionDetailCtrl = function($scope, $state, $stateParams, veazyConfig, QuestionService, Helper, ngDialog) {
		$scope.CODE = veazyConfig.CODE;
		var CODE = veazyConfig.CODE;
		var MESSAGE = veazyConfig.MESSAGE;

		$scope.showSharedContent = false;

		var questionId = $stateParams.questionId;

		QuestionService.getDetail(questionId).then(function(response) {
			switch (response.code) {
				case CODE.SUCCESS: {
					$scope.question = response.data;
					console.log($scope.question);
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

		});
		
		$scope.trustAsHtml = Helper.trustAsHtml;

		// $scope.question = getQuestion;
		$scope.openConfirmDeleteDialog = function() {
			ngDialog.open({
				template: 'partials/content-editor-restricted/delete-question.html',
				className: 'ngdialog-theme-default delete-lesson-dialog',
				showClose: true,
				closeByDocument: false,
				width: 500,
				controller: 'deleteQuestionCtrl',
				data: {
					question: $scope.question
				}
			});
		}
	};

	questionDetailCtrl.$inject = ['$scope', '$state', '$stateParams', 'veazyConfig', 'QuestionService', 'Helper', 'ngDialog'];

	angular.module('veazyControllers').controller('questionDetailCtrl', questionDetailCtrl);
})();