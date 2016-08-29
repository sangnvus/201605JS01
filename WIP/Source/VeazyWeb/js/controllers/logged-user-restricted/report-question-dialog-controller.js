;(function() {
	'use strict';
	var reportQuestionDialogCtrl = function($scope, $state, veazyConfig, ngDialog, QuestionService) {
		var CODE = veazyConfig.CODE;
		$scope.reportSent = false;

		//click send report button
		$scope.submitReport = function() {
			var questionId = $scope.ngDialogData.question.questionId;
			var content = $scope.content;

			//call service
			QuestionService.report(questionId, content).then(function(response) {
				console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						$scope.reportSent = true;
						break;
					}

					case CODE.UNAUTHORIZED: {
						$state.go('login');
						break;
					}

					default: {
						//handling error
					}
				}
			});
		};

		$scope.closeDialog = function() {
			ngDialog.close();
		};
	};

	reportQuestionDialogCtrl.$inject = ['$scope', '$state', 'veazyConfig', 'ngDialog', 'QuestionService'];
	angular.module('veazyControllers').controller('reportQuestionDialogCtrl', reportQuestionDialogCtrl);
})();