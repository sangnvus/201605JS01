;(function() {
	var ExamService = function($q, $timeout, Exam) {
		return {
			create: function(examInput) {
				var deferred = $q.defer();
				var ExamFactory = new Exam(examInput);

				$timeout(function() {
					ExamFactory.$create(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject();
					});
				});
				return deferred.promise;
			},
			submit: function(examOutput) {
				var deferred = $q.defer();
				var ExamFactory = new Exam(examOutput);

				$timeout(function() {
					ExamFactory.$submit(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject();
					});
				});
				return deferred.promise;
			},
			getHistoryList: function() {
				var examFactory = new Exam();
				var deferred = $q.defer();

				$timeout(function() {
					examFactory.$getHistoryList(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject();
					})
				});
				return deferred.promise;
			},
			getHistory: function(examId) {
				var examFactory = new Exam({examId: examId});
				var deferred = $q.defer();

				$timeout(function() {
					examFactory.$getHistory(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject();
					})
				});
				return deferred.promise;
			},
			redo: function(examId) {
				var examFactory = new Exam({examId: examId});
				var deferred = $q.defer();

				$timeout(function() {
					examFactory.$redo(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject();
					})
				});
				return deferred.promise;
			}
		};
	};

	ExamService.$inject = ['$q', '$timeout', 'Exam'];
	angular.module('veazyServices').service('ExamService', ExamService);
})();