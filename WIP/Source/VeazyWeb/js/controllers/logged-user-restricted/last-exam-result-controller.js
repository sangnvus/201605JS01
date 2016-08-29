;(function() {
	var lastExamResultCtrl = function($scope, getLastExamResult, veazyConfig, Helper, ngDialog) {
		$scope.exam = getLastExamResult.data;
		$scope.CODE = veazyConfig.CODE;

		// console.log($scope.exam);
		$scope.calculateTotalNumberOfQuestion = Helper.calculateTotalNumberOfQuestion;
		$scope.trustAsHtml = Helper.trustAsHtml;

		$scope.showReportDialog = function(question) {
			ngDialog.open({
				template: 'partials/logged-user-restricted/report-question-dialog.html',
				className: 'ngdialog-theme-default report-dialog',
				controller: 'reportQuestionDialogCtrl',
				showClose: true,
				closeByDocument: false,
				width: 400,
				data: {
					question: question
				}
			});
		};
	};

	lastExamResultCtrl.$inject = ['$scope', 'getLastExamResult', 'veazyConfig', 'Helper', 'ngDialog'];
	angular.module('veazyControllers').controller('lastExamResultCtrl', lastExamResultCtrl);
})();