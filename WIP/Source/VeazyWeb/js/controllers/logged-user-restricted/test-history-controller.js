;(function() {
	var testHistoryCtrl = function($scope, $state, localStorageService, veazyConfig, ExamService) {
		var CODE = veazyConfig.CODE;
		ExamService.getHistory().then(function(response) {
			switch (response.code) {
				case CODE.SUCCESS: {
					$scope.testHistoryList = response.data.exams;
					// console.log($scope.testHistoryList);
					break;
				}
				case CODE.UNAUTHORIZED: {
					$state.go('login');
					localStorageService.remove('username');
				}
			}
		});
	};

	testHistoryCtrl.$inject = ['$scope', '$state', 'localStorageService', 'veazyConfig', 'ExamService'];

	angular.module('veazyControllers').controller('testHistoryCtrl', testHistoryCtrl);
})();