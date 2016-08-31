;(function() {
	'use strict';
	var reportListCtrl = function($scope, $translate, $filter, $state, ReportService, veazyConfig) {
		//current language
		$scope.currentLang = $translate.use();
		var CODE = veazyConfig.CODE;

		//values of report type select box
		$scope.reportTypes = [{
			id: 1,
			name: 'ALL_REPORTS'
		}, {
			id: 2,
			name: 'UNREAD_ONLY'
		}];

		//selected report type to be displayed
		$scope.selectedReportType = $scope.reportTypes[1];

		//call service to get report list
		ReportService.getList().then(function(response) {
			// console.log(response);
			switch (response.code) {
				case CODE.SUCCESS: {
					$scope.reportList = response.data;
					$scope.filteredReportList = $filter('filter')($scope.reportList, {readFlag: false});

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

				case CODE.UNAUTHORIZED: {
					$state.go('login');
					break;
				}

				case CODE.NO_PERMISSION: {
					$state.go('forbidden');
					break;
				}

				default: {
					//handling other cases
				}
			}
		});

		//mark a report as read
		$scope.markReportAsRead = function(report) {
			var reportId = report.id;
			// console.log(report.id)

			ReportService.markAsRead(reportId).then(function(response) {
				console.log(response.data);

				switch (response.code) {
					case CODE.SUCCESS: {
						$state.reload();
						break;
					}

					case CODE.UNAUTHORIZED: {
						$state.go('login');
						break;
					}

					case CODE.NO_PERMISSION: {
						$state.go('forbidden');
						break;
					}

					default: {
						//handling other cases
					}
				}
			});
		};

		//filter type of reports to be displayed
		$scope.filterReport = function() {
			var reportTypeId = $scope.selectedReportType.id;

			switch (reportTypeId) {
				//show all reports
				case $scope.reportTypes[0].id: {
					$scope.filteredReportList = $scope.reportList;
					break;
				}

				//show only unread report
				case $scope.reportTypes[1].id: {
					$scope.filteredReportList = $scope.filteredReportList = $filter('filter')($scope.reportList, {readFlag: false});
					break;
				}
			}
		}
	};

	reportListCtrl.$inject = ['$scope', '$translate', '$filter', '$state', 'ReportService', 'veazyConfig'];
	angular.module('veazyControllers').controller('reportListCtrl', reportListCtrl);
})();