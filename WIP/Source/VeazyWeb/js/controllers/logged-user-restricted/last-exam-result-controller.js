;(function() {
	var lastExamResultCtrl = function($scope, getLastExamResult, veazyConfig, Helper) {
		$scope.exam = getLastExamResult.data;
		$scope.CODE = veazyConfig.CODE;

		$scope.trustAsHtml = Helper.trustAsHtml;
		console.log($scope.exam);
	};

	lastExamResultCtrl.$inject = ['$scope', 'getLastExamResult', 'veazyConfig', 'Helper'];
	angular.module('veazyControllers').controller('lastExamResultCtrl', lastExamResultCtrl);
})();