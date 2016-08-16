;(function() {
	var LessonService = function(Lesson, $q, $timeout, veazyConfig) {
		var CODE = veazyConfig.CODE;
		var MESSAGE = veazyConfig.MESSAGE;

		return {
			getLessonsInCourse: function(courseId) {
				var lessonFactory = new Lesson({
					courseId: courseId
				});
				var deferred = $q.defer();

				$timeout(function() {
					lessonFactory.$getLessonsInCourse(function(response) {
						console.log(response);
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							default:{
								deferred.reject(MESSAGE[response.code]);
							}
						}
					}, function() {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			getAllLessons: function() {
				var deferred = $q.defer();
				var lessonFactory = new Lesson();

				$timeout(function() {
					lessonFactory.$getAllLessons(function(response) {
						console.log(response);
						switch (response.code) {
							case CODE.SUCCESS: {
								// deferred.resolve(response);
								// break;
							}
							case CODE.UNAUTHORIZED: {
								deferred.resolve(response);
								break;
							}
							default:{
								deferred.reject(MESSAGE[response.code]);
							}
						}
					}, function() {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			getDetail: function(lessonId) {
				var lessonFactory = new Lesson({
					lessonId: lessonId
				});
				var deferred = $q.defer();

				$timeout(function() {
					lessonFactory.$getDetail(function(response) {
						console.log(response);
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							default:{
								deferred.reject(MESSAGE[response.code]);
							}
						}
					}, function() {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			getDraft: function(lessonId) {
				var lessonFactory = new Lesson({
					lessonId: lessonId
				});
				var deferred = $q.defer();

				$timeout(function() {
					lessonFactory.$getDraft(function(response) {
						console.log(response);
						switch (response.code) {
							case CODE.SUCCESS: {
								// deferred.resolve(response);
								// break;
							}
							case CODE.UNAUTHORIZED: {
								deferred.resolve(response);
								break;
							}
							default:{
								deferred.reject(MESSAGE[response.code]);
							}
						}
					}, function() {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			create: function(lesson) {
				var deferred = $q.defer();
				var lessonFactory = new Lesson(lesson);
				$timeout(function() {
					lessonFactory.$create(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			report: function(lessonId, content) {
				var deferred = $q.defer();
				var lessonFactory = new Lesson({
					lessonId: lessonId, 
					content: content
				});
				$timeout(function() {
					lessonFactory.$report(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject();
					});
				});
				return deferred.promise;
			}
		}
	};

	LessonService.$inject = ['Lesson', '$q', '$timeout', 'veazyConfig'];
	angular.module('veazyServices').service('LessonService', LessonService);
})();