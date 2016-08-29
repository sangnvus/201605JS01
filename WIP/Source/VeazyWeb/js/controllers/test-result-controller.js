;(function() {
	'use strict';
	var testResultCtrl = function($scope, $state, veazyConfig, ngDialog, Helper, UserService) {
		$scope.CODE = veazyConfig.CODE;
		var CODE = $scope.CODE;

		//do not show Report hyperlink if user is not logged in
		$scope.isLoggedIn = false;
		//check if user is logged in
		UserService.login().then(function(response) {
			switch (response.code) {
				case CODE.SUCCESS: {
					//logged in, then show Report hyperlink
					$scope.isLoggedIn = true;
					break;
				}

				default: {
					$scope.isLoggedIn = false;
				}
			}
		}, function() {
			$scope.isLoggedIn = false;
		});


		$scope.exam = $state.params.examResult;
		$scope.exam.totalNumberOfQuestions = Helper.calculateTotalNumberOfQuestion($scope.exam);

		if (!$scope.exam.result) {
			var mark = Helper.calculateExamMark($scope.exam);
			var numberOfQuestions = Helper.calculateTotalNumberOfQuestion($scope.exam);
			$scope.exam.result = mark / numberOfQuestions;
		}

		// $scope.grade = $scope.exam.totalPts / $scope.exam.totalNumberOfQuestions;
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

	testResultCtrl.$inject = ['$scope', '$state', 'veazyConfig', 'ngDialog', 'Helper', 'UserService'];
	angular.module('veazyControllers').controller('testResultCtrl', testResultCtrl);
})();