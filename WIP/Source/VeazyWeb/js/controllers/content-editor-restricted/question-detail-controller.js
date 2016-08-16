;(function() {
	'use strict';
	var questionDetailCtrl = function($scope, $state, $stateParams, veazyConfig, QuestionService, Helper) {
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
	};

	questionDetailCtrl.$inject = ['$scope', '$state', '$stateParams', 'veazyConfig', 'QuestionService', 'Helper'];

	angular.module('veazyControllers').controller('questionDetailCtrl', questionDetailCtrl);
})();