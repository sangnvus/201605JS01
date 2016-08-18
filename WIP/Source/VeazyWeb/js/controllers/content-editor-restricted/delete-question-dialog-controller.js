;(function() {
	var deleteQuestionCtrl = function($scope, $state, ngDialog, QuestionService, veazyConfig) {
		var CODE = veazyConfig.CODE;
		$scope.deleteQuestion = function() {
			QuestionService.delete($scope.ngDialogData.question.questionId).then(function(response) {
				console.log(response.code);
				switch (response.code) {
					case CODE.SUCCESS: {
						$state.go('editordashboard.question.list', {}, {
							reload: true
						});
						ngDialog.close();
						break;
					}
					case CODE.UNAUTHORIZED: {
						$state.go('login');
						break;
					}
					default: {

					}
				}
			}, function(reject) {

			});
		};

		$scope.closeDialog = function() {
			ngDialog.close();
		};
	};
	

	deleteQuestionCtrl.$inject = ['$scope', '$state', 'ngDialog', 'QuestionService', 'veazyConfig'];
	angular.module('veazyControllers').controller('deleteQuestionCtrl', deleteQuestionCtrl);
})();