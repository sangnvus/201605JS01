;(function() {
	'use strict';
	var reportListCtrl = function($scope, $translate, ReportService, veazyConfig) {
		$scope.currentLang = $translate.use();
		var CODE = veazyConfig.CODE;

		ReportService.getList().then(function(response) {
			console.log(response);
			switch (response.code) {
				case CODE.SUCCESS: {
					$scope.reportList = response.data;

					//pagination
					$scope.totalItems = $scope.reportList.length;
					$scope.currentPage = 1;
					$scope.numPerPage = 5;
					$scope.paginate = function(value) {
						var begin, end, index;
						begin = ($scope.currentPage - 1) * $scope.numPerPage;
						end = begin + $scope.numPerPage;
						index = $scope.reportList.indexOf(value);
						return (begin <= index && index < end);
					};
					break;
				}

				// case CODE.UNAUTHORIZED: {
				// 	$state.go('login');
				// }

				default: {

				}
			}
		});
	};

	reportListCtrl.$inject = ['$scope', '$translate', 'ReportService', 'veazyConfig'];
	angular.module('veazyControllers').controller('reportListCtrl', reportListCtrl);
})();