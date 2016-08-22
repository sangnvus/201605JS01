;(function() {
	var ReportService = function($q, $timeout, Report) {
		return {
			getList: function() {
				var deferred = $q.defer();
				var reportFactory = new Report();

				$timeout(function() {
					reportFactory.$getList(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject();
					});
				});
				return deferred.promise;
			},
			getDetail: function(reportId) {
				var deferred = $q.defer();
				var reportFactory = new Report({
					report: reportId
				});

				$timeout(function() {
					reportFactory.$getDetail(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject();
					});
				});
				return deferred.promise;
			}
		};
	};

	ReportService.$inject = ['$q', '$timeout', 'Report'];
	angular.module('veazyServices').service('ReportService', ReportService);
})();