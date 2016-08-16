;(function() {
	'use strict';
	var testResultCtrl = function($scope, $state, veazyConfig, ngDialog, Helper) {
		$scope.CODE = veazyConfig.CODE;
		$scope.exam = $state.params.examResult;
		$scope.exam.totalNumberOfQuestions = Helper.calculateTotalNumberOfQuestion($scope.exam);
		$scope.trustAsHtml = Helper.trustAsHtml;
		// console.log($scope.examResult);
		// console.log($state);
		// console.log($scope.examResult);

		$scope.showReportDialog = function(question) {
			ngDialog.open({
				template: 'partials/logged-user-restricted/report-question-dialog.html',
				className: 'ngdialog-theme-default report-dialog',
				controller: 'reportQuestionDialogCtrl',
				showClose: true,
				closeByDocument: false,
				width: 400,
				data: question
			});
		};
	};

	testResultCtrl.$inject = ['$scope', '$state', 'veazyConfig', 'ngDialog', 'Helper'];
	angular.module('veazyControllers').controller('testResultCtrl', testResultCtrl);
})();