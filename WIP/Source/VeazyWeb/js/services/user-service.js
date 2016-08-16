;(function() {
	var UserService = function(User, $q, $timeout, veazyConfig) {
		var CODE = veazyConfig.CODE;
		var MESSAGE = veazyConfig.MESSAGE;

		return {
			checkLoggedIn: function() {
				var deferred = $q.defer();
				var userFactory = new User();
				$timeout(function() {
					userFactory.$login(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject();
					})
				});
				return deferred.promise;
			},
			login: function(user) {
				var userFactory = new User(user);
				var deferred = $q.defer();
				$timeout(function() {
					userFactory.$login(function(response) {
						console.log(response);
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							default: {
								deferred.reject(MESSAGE[response.code]);
							}
						}
					}, function() {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			register: function(user) {
				var userFactory = new User(user);
				var deferred = $q.defer();
				$timeout(function() {
					userFactory.$register(function(response) {
						console.log(response);
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							default: {
								deferred.reject(MESSAGE[response.code]);
							}
						}
					}, function() {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			getCurrentUser: function() {
				var deferred = $q.defer();
				var userFactory = new User();

				$timeout(function() {
					userFactory.$getCurrentUser(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			updateProfile: function(user) {
				var deferred = $q.defer();
				var userFactory = new User(user);

				$timeout(function() {
					userFactory.$updateProfile(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			getSkillStats: function() {
				var deferred = $q.defer();
				var userFactory = new User();

				$timeout(function() {
					userFactory.$getSkillStats(function(response){
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			getExamMarkStats: function(numberOfExams) {
				var deferred = $q.defer();
				var userFactory = new User();
				userFactory.numberOfExams = numberOfExams;

				$timeout(function() {
					userFactory.$getExamMarkStats(function(response){
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			getLevelStats: function() {
				var deferred = $q.defer();
				var userFactory = new User();

				$timeout(function() {
					userFactory.$getLevelStats(function(response){
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject('ERROR_CONNECTION');
					});
				});
				return deferred.promise;
			},
			makeEditor: function(username) {
				var userFactory = new User({
					username: username
				});

				var deferred = $q.defer();
				$timeout(function() {
					userFactory.$makeEditor(function(response) {
						deferred.resolve(response);
					}, function() {
						deferred.reject();
					});
				});
				return deferred.promise;
			}
		};
	}

	UserService.$inject = ['User', '$q', '$timeout','veazyConfig'];

	angular.module('veazyServices').service('UserService', UserService);
})();