;(function() {
	var ChartService = function(Chart, $q, $timeout, veazyConfig) {
		var CODE = veazyConfig.CODE;
		var MESSAGE = veazyConfig.MESSAGE;

		return {
			getSkillStats: function() {
				var deferred = $q.defer();
				var chartFactory = new Chart();

				$timeout(function() {
					chartFactory.$getSkillStats(function(response){
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			getExamMarkStats: function(numberOfExams) {
				var deferred = $q.defer();
				var chartFactory = new Chart();
				chartFactory.numberOfExams = numberOfExams;

				$timeout(function() {
					chartFactory.$getExamMarkStats(function(response){
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			getLevelStats: function() {
				var deferred = $q.defer();
				var chartFactory = new Chart();

				$timeout(function() {
					chartFactory.$getLevelStats(function(response){
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			}
		};
	};

	ChartService.$inject = ['Chart', '$q', '$timeout','veazyConfig'];

	angular.module('veazyServices').service('ChartService', ChartService);
})();