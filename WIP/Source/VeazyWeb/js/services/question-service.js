;(function() {
	var QuestionService = function(Question, $q, $timeout, veazyConfig) {
		var CODE = veazyConfig.CODE;
		var MESSAGE = veazyConfig.MESSAGE;
		
		return {
			create: function(question) {
				var deferred = $q.defer();
				var questionFactory = new Question(question);

				$timeout(function() {
					questionFactory.$create(function(response) {
						deferred.resolve(response);
					}, function() {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			getAllQuestions: function() {
				var deferred = $q.defer();
				var questionFactory = new Question();

				$timeout(function() {
					questionFactory.$getAllQuestions(function(response) {
						deferred.resolve(response);
					}, function() {
						deferred.reject(MESSAGE[response.code]);
					});
				});
				return deferred.promise;
			},
			getDetail: function(questionId) {
				var deferred = $q.defer();
				var questionFactory = new Question({
					questionId: questionId
				});

				$timeout(function() {
					questionFactory.$getDetail(function(response) {
						deferred.resolve(response);
					}, function() {
						deferred.reject();
					});
				});
				return deferred.promise;
			},
			update: function(question) {
				var deferred = $q.defer();
				var questionFactory = new Question(question);

				$timeout(function() {
					questionFactory.$update(function(response) {
						deferred.resolve(response);
					}, function() {
						deferred.reject();
					});
				});
				return deferred.promise;
			},
			delete: function(questionId) {
				var deferred = $q.defer();
				var questionFactory = new Question({
					questionId: questionId
				});

				$timeout(function() {
					questionFactory.$delete(function(response) {
						deferred.resolve(response);
					}, function() {
						deferred.reject();
					});
				});
				return deferred.promise;
			},
			report: function(questionId, content) {
				var deferred = $q.defer();
				var questionFactory = new Question({
					questionId: questionId,
					content: content
				});

				$timeout(function() {
					questionFactory.$report(function(response) {
						deferred.resolve(response);
					}, function() {
						deferred.reject();
					});
				});
				return deferred.promise;
			}
		};
	};

	QuestionService.$inject = ['Question', '$q', '$timeout', 'veazyConfig'];

	angular.module('veazyServices').service('QuestionService', QuestionService);
})();