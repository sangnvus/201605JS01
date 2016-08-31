;(function() {
	'use strict';
	var retakeTestResultCtrl = function($scope, $state, veazyConfig, ngDialog, Helper, UserService) {
		$scope.CODE = veazyConfig.CODE;

		//exam result
		$scope.exam = $state.params.examResult;
		$scope.exam.takenDate = moment().valueOf();
		$scope.exam.totalNumberOfQuestions = Helper.calculateTotalNumberOfQuestion($scope.exam);

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

	retakeTestResultCtrl.$inject = ['$scope', '$state', 'veazyConfig', 'ngDialog', 'Helper', 'UserService'];
	angular.module('veazyControllers').controller('retakeTestResultCtrl', retakeTestResultCtrl);
})();