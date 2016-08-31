;(function() {
	'use strict';

	var app = angular.module('veazyApp', ['ngRoute', 'veazyControllers', 'veazyDirectives', 'veazyFilters', 'veazyFactories', 
		'veazyServices', 'ngAnimate', 'ngDialog', 'ngSanitize', 'froala', 'ui.router', 'ui.bootstrap', 'pascalprecht.translate', 
		'ngCookies', 'chart.js', 'ngResource', 'angular-md5', 'validation.match', 'LocalStorageModule', 
		'ae-datetimepicker', 'angularFileUpload', 'ui.checkbox']);

	//always close dialog when changing page
	app.run(['$rootScope', '$state','ngDialog', function($rootScope, $state, ngDialog) {
		$rootScope.$on('$stateChangeSuccess', function(event, newState, newStateParams, oldState, oldStateParams) {
			ngDialog.close();
		});
	}]);

	//chart color
	app.config(['ChartJsProvider', function(ChartJsProvider) {
		ChartJsProvider.setOptions({
			colours: ['#ee4035', '#f37736', '#fdf498', '#7bc043', '#0392cf', '#949FB1', '#4D5360']
		});
	}]);

	//multi-lang
	app.config(['$translateProvider', function($translateProvider) {	
		$translateProvider.useStaticFilesLoader({
			prefix: 'lang/locale-',
			suffix: '.json'
		});

		$translateProvider.useSanitizeValueStrategy('escape');
		$translateProvider.preferredLanguage('en');
		$translateProvider.useLocalStorage();
	}]);

	//always bind cookies to http request
	app.config(['$httpProvider', function($httpProvider) {
		$httpProvider.defaults.withCredentials = true;
	}]);

	//editor global config
	app.value('froalaConfig', {
		language: 'en',
		// imageUploadURL: 'http://163.44.172.52:8080/Veazy/api/uploadfile'
	});

	//states
	app.config(['$stateProvider', '$urlRouterProvider', 'veazyConfig', function($stateProvider, $urlRouterProvider, veazyConfig) {
		//nested state & view
		$urlRouterProvider.otherwise('/home');

		//404 page
		$stateProvider.state('pagenotfound', {
			url: '/error/notfound',
			templateUrl: 'partials/404-error.html'
		//403 page
		}).state('forbidden', {
			url: '/error/forbidden',
			templateUrl: 'partials/403-forbidden.html',
		//home page
		}).state('home', {
			url: '/home',
			templateUrl: 'partials/home.html',
			controller: 'homeCtrl'
		//login page
		}).state('login', {
			url: '/login',
			templateUrl: 'partials/login.html',
			controller: 'loginCtrl',
			checkLoggedIn: true,
			resolve: {}
		}).state('register', {
			abstract: true,
			url: '/register',
			template: '<div ui-view></div>'
		//register page
		}).state('register.form', {
			url: '',
			templateUrl: 'partials/register.html',
			controller: 'registerCtrl',
			checkLoggedIn: true,
			resolve: {}
		//register success page
		}).state('register.success', {
			url: '/success',
			templateUrl: 'partials/logged-user-restricted/register-success.html',
			controller: 'registerSuccessCtrl',
			resolve: {
				getUser: function($q, $state, UserService, veazyConfig) {
					var CODE = veazyConfig.CODE;
					var deferred = $q.defer();

					//call service
					UserService.getCurrentUser().then(function(response) {
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}

							//in case user logged out from another tab
							case CODE.UNAUTHORIZED: {
								deferred.reject();
								$state.go('login');
								break;
							}
							default: {
								//handling other cases
							}
						}
					}, function(reject) {
						//handling error
					});
					return deferred.promise;
				}
			}
		//lesson list in course
		}).state('courselist', {
			url: '/courses/:courseId',
			templateUrl: 'partials/course-list.html',
			controller: 'courseListCtrl',
			resolve: {
				getLessonList: function($stateParams, $q, LessonService, veazyConfig) {
					var CODE = veazyConfig.CODE;
					var courseId = $stateParams.courseId;
					var deferred = $q.defer();
					LessonService.getLessonsInCourse(courseId).then(function(response) {
						switch (response.code) {
							case CODE.SUCCESS: {
								// console.log(response);
								deferred.resolve(response);
								break;
							}
							default: {
								deferred.reject();
							}
						}
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}
			}
		//lesson detail
		}).state('lessondetail', {
			url: '/lessons/:lessonId',
			templateUrl: 'partials/lesson-detail.html',
			controller: 'lessonDetailCtrl',
			resolve: {
				getLesson: function($stateParams, $q, LessonService, veazyConfig) {
					var CODE = veazyConfig.CODE;
					var lessonId = $stateParams.lessonId;
					var deferred = $q.defer();

					LessonService.getDetail(lessonId).then(function(response) {
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}

							default: {
								deferred.reject();
								break;
							}
						}
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}
			}
		//test setup page
		}).state('test', {
			url: '/test',
			templateUrl: 'partials/test.html',
			controller: 'testCtrl'
		//do test page
		}).state('test.taketest', {
			url: '/take/:examId',
			params: {
				exam: null
			},
			resolve: {
				getExam: function($timeout, $state, $stateParams, $q, ExamService, veazyConfig) {
					console.log($stateParams.exam);
					var CODE = veazyConfig.CODE;
					var deferred = $q.defer();
					$timeout(function() {
						if ($stateParams.exam) {
							deferred.resolve();
						} else if ($stateParams.examId) {
							ExamService.getHistory($stateParams.examId).then(function(response) {
								// deferred.resolve();
								console.log(response);
								switch (response.code) {
									case CODE.SUCCESS: {
										deferred.resolve(response);
										break;
									}
									case CODE.UNAUTHORIZED: {
										deferred.reject();
										$state.go('login');
										break;
									}
									default: {
										deferred.reject();
										$state.go('test');
									}
								}
							});
						} else {
							deferred.reject();
							$state.go('test');
						}
					});
					return deferred.promise;
				}
			},
			templateUrl: 'partials/take-test.html',
			controller: 'takeTestCtrl'
		//test result page
		}).state('test.testresult', {
			url: '/result',
			params: {
				examResult: null
			},
			templateUrl: 'partials/test-result.html',
			controller: 'testResultCtrl',
			resolve: {
				getExamResult: function($timeout, $state, $stateParams, $q, ExamService) {
					var deferred = $q.defer();
					$timeout(function() {
						if ($stateParams.examResult) {
							deferred.resolve();
						} else {
							deferred.reject();
							$state.go('test');
						}
					});
					return deferred.promise;
				}
			}
		}).state('test.firsttimeresult', {
			url: '/:examId/first-result',
			templateUrl: 'partials/logged-user-restricted/first-result.html',
			controller: 'lastExamResultCtrl',
			resolve: {
				getLastExamResult: function($state, $stateParams, $q, ExamService, veazyConfig) {
					var CODE = veazyConfig.CODE;
					var deferred = $q.defer();
					var examId = $stateParams.examId;
					ExamService.getHistory(examId).then(function(response) {
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							case CODE.UNAUTHORIZED: {
								deferred.reject();
								$state.go('login');
								break;
							}
							default: {
								deferred.reject();
							}
						}
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}
			}
		//test result page for logged user
		}).state('test.savedresult', {
			url: '/:examId/result',
			templateUrl: 'partials/logged-user-restricted/last-result.html',
			controller: 'lastExamResultCtrl',
			resolve: {
				getLastExamResult: function($state, $stateParams, $q, ExamService, veazyConfig) {
					var CODE = veazyConfig.CODE;
					var deferred = $q.defer();
					var examId = $stateParams.examId;
					ExamService.getHistory(examId).then(function(response) {
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							case CODE.UNAUTHORIZED: {
								deferred.reject();
								$state.go('login');
								break;
							}
							default: {
								deferred.reject();
							}
						}
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}
			}
		//retake test page
		}).state('test.retake', {
			url: '/:examId/revision',
			templateUrl: 'partials/logged-user-restricted/retake-test.html',
			controller: 'retakeTestCtrl',
			resolve: {
				getExam: function($state, $stateParams, $q, ExamService, veazyConfig) {
					var CODE = veazyConfig.CODE;
					var deferred = $q.defer();
					var examId = $stateParams.examId;

					ExamService.redoHistory($stateParams.examId).then(function(response) {
						console.log(response);
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							case CODE.UNAUTHORIZED: {
								deferred.reject();
								$state.go('login');
								break;
							}
							default: {
								deferred.reject();
								$state.go('test');
							}
						}
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}
			}
		})
		.state('test.retakeresult', {
			url: '/:examId/revision/result',
			templateUrl: 'partials/logged-user-restricted/retake-test-result.html',
			controller: 'retakeTestResultCtrl',
			params: {
				examResult: null
			},
			resolve: {
				getExamResult: function($timeout, $state, $stateParams, $q, ExamService) {
					var deferred = $q.defer();
					$timeout(function() {
						if ($stateParams.examResult) {
							deferred.resolve();
						} else {
							deferred.reject();
							$state.go('test.retake', {examId: $stateParams.examId});
						}
					});
					return deferred.promise;
				}
			}
		})

		.state('user', {
			url: '/user',
			abstract: true,
			templateUrl: 'partials/logged-user-restricted/user.html',
			controller: 'userCtrl'
		//user profile page
		}).state('user.profile', {
			url: '/profile',
			templateUrl: 'partials/logged-user-restricted/user-profile.html',
			controller: 'userProfileCtrl',
			resolve: {
				getUser: ['$q', '$state', 'UserService', 'veazyConfig', 
				function($q, $state, UserService, veazyConfig) {
					var deferred = $q.defer();
					var CODE = veazyConfig.CODE;

					UserService.getCurrentUser().then(function(response) {
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							case CODE.UNAUTHORIZED: {
								$state.go('login');
								deferred.reject();
							}
							default: {
								deferred.reject();
							}
						}
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}]
			}
		//statistics page
		}).state('user.statistics', {
			url: '/statistics',
			templateUrl: 'partials/logged-user-restricted/user-statistics.html',
			controller: 'userStatisticsCtrl'
		//test history page
		}).state('user.testhistory', {
			url: '/test-history',
			templateUrl: 'partials/logged-user-restricted/test-history.html',
			controller: 'testHistoryCtrl'
		})

		.state('editor', {
			url: '/editor',
			abstract: true,
			templateUrl: 'partials/content-editor-restricted/editor.html',
			controller: 'editorSideBarCtrl'
		//report list page
		}).state('editor.reportlist', {
			url: '/reports',
			templateUrl: 'partials/content-editor-restricted/report-list.html',
			controller: 'reportListCtrl',
			authorizeEditor: true,
			resolve: {}
		})
		.state('editor.lesson', {
			url: '/lessons',
			abstract: true,
			template: '<div ui-view></div>'
		//add lesson page
		}).state('editor.lesson.add', {
			url: '/add',
			templateUrl: 'partials/content-editor-restricted/add-lesson.html',
			controller: 'addLessonCtrl',
			authorizeEditor: true,
			resolve: {}
		//lesson list page
		}).state('editor.lesson.list', {
			url: '',
			templateUrl: 'partials/content-editor-restricted/lesson-list.html',
			controller: 'lessonListCtrl',
			authorizeEditor: true,
			resolve: {}
		//lesson detail
		}).state('editor.lesson.detail', {
			url: '/:id',
			templateUrl: 'partials/content-editor-restricted/lesson-detail.html',
			controller: 'editorLessonDetailCtrl',
			resolve: {
				getLesson: ['$stateParams', '$q', 'LessonService', 'veazyConfig', 
				function($stateParams, $q, LessonService, veazyConfig) {
					var CODE = veazyConfig.CODE;
					var lessonId = $stateParams.id;
					var deferred = $q.defer();

					LessonService.getDetail(lessonId).then(function(response) {
						// console.log(response);
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							case CODE.UNAUTHORIZED: {
								deferred.reject();
								$state.go('login');
								break;
							}
						}
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}]
			}
		//edit lesson page
		}).state('editor.lesson.edit', {
			url: '/:id/edit',
			templateUrl: 'partials/content-editor-restricted/edit-lesson.html',
			controller: 'editLessonCtrl',
			resolve: {
				getLesson: ['$q', '$state', '$stateParams', 'LessonService', 'veazyConfig', 
				function($q, $state, $stateParams, LessonService, veazyConfig) {
					var deferred = $q.defer();
					var lessonId = $stateParams.id;
					var CODE = veazyConfig.CODE;

					LessonService.getDraft(lessonId).then(function(response) {
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							case CODE.UNAUTHORIZED: {
								deferred.reject();
								$state.go('login');
								break;
							}
							case CODE.NO_PERMISSION: {
								deferred.reject();
								$state.go('forbidden');
								break;
							}
						}
					}, function() {
						deferred.reject();
					});
					return deferred.promise;
				}]
			}
		})
		.state('editor.question', {
			abstract: true,
			url: '/questions',
			template: '<div ui-view><div>'
		//add question page
		}).state('editor.question.add', {
			url: '/add',
			templateUrl: 'partials/content-editor-restricted/add-question.html',
			controller: 'addQuestionCtrl',
			authorizeEditor: true,
			resolve: {}
		//list question page
		}).state('editor.question.list', {
			url: '',
			templateUrl: 'partials/content-editor-restricted/question-list.html',
			controller: 'questionListCtrl',
			authorizeEditor: true,
			resolve: {}
		//question detail page
		}).state('editor.question.detail', {
			url: '/:questionId',
			controller: 'questionDetailCtrl',
			templateUrl: 'partials/content-editor-restricted/question-detail.html',
			authorizeEditor: true,
			resolve: {}
		//edit question page
		}).state('editor.question.edit', {
			url: '/:questionId/edit',
			templateUrl: 'partials/content-editor-restricted/edit-question.html',
			controller: 'editQuestionCtrl',
			resolve: {
				getQuestion: ['$state', '$stateParams', '$q','veazyConfig', 'QuestionService',
				function($state, $stateParams, $q, veazyConfig, QuestionService) {
					var CODE = veazyConfig.CODE;
					var deferred = $q.defer();
					var questionId = $stateParams.questionId;
					QuestionService.getDetail(questionId).then(function(response) {
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							case CODE.UNAUTHORIZED: {
								deferred.resolve(response);
								break;
							}
							case CODE.NO_PERMISSION: {
								deferred.reject();
								$state.go('forbidden');
							}
							default: {
								deferred.reject();
							}
						}
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}]
			}
		//editor profile page
		}).state('editor.profile', {
			url: '/profile',
			controller: 'userProfileCtrl',
			templateUrl: 'partials/content-editor-restricted/editor-profile.html',
			resolve: {
				getUser: function($q, $state, UserService, veazyConfig) {
					var deferred = $q.defer();
					var CODE = veazyConfig.CODE;

					UserService.getCurrentUser().then(function(response) {
						console.log(response);
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							case CODE.UNAUTHORIZED: {
								$state.go('login');
								deferred.reject();
							}
							case CODE.NO_PERMISSION: {
								$state.go('forbidden');
								deferred.reject();
							}
							default: {
								deferred.reject();
							}
						}
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}
			}
		})

		//admin
		.state('admin', {
			url: '/admin',
			abstract: true,
			templateUrl: 'partials/admin-restricted/admin.html',
			controller: 'adminSideBarCtrl',
		})
		//admin dashboard page
		.state('admin.dashboard', {
			url: '/dashboard',
			templateUrl: 'partials/admin-restricted/dashboard.html',
			controller: 'adminDashboadCtrl',
			authorizeAdmin: true,
			resolve: {}
		})
		//user list page
		.state('admin.users', {
			url: '/users',
			templateUrl: 'partials/admin-restricted/users.html',
			controller: 'userManagementCtrl',
			authorizeAdmin: true,
			resolve: {}
		}).state('admin.profile', {
			url: '/profile',
			controller: 'adminProfileCtrl',
			templateUrl: 'partials/admin-restricted/admin-profile.html',
			resolve: {
				getUser: function($q, $state, UserService, veazyConfig) {
					var deferred = $q.defer();
					var CODE = veazyConfig.CODE;

					UserService.getCurrentUser().then(function(response) {
						console.log(response);
						switch (response.code) {
							case CODE.SUCCESS: {
								deferred.resolve(response);
								break;
							}
							case CODE.UNAUTHORIZED: {
								$state.go('login');
								deferred.reject();
							}
							case CODE.NO_PERMISSION: {
								$state.go('forbidden');
								deferred.reject();
							}
							default: {
								deferred.reject();
							}
						}
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}
			}
		});
	}]);

	//authorization
	app.run(['$rootScope', '$state', function($rootScope, $state) {
		$rootScope.$on('$stateChangeStart', function(event, newState, newStateParams, oldState, oldStateParams) {

			//check if user logged in, if yes --> prevent from going back to login/register page
			if (newState.checkLoggedIn) {
				newState.resolve.checkLoggedIn = function(UserService, $state, $q, veazyConfig) {
					var CODE = veazyConfig.CODE;
					var deferred = $q.defer();
					var role;
					UserService.checkLoggedIn().then(function(response) {
						switch (response.code) {
							//already login
							case CODE.SUCCESS: {
								role = response.data.role;
								switch (role) {
									case CODE.USER: {
										$state.go('home');
										break;
									}
									case CODE.EDITOR: {
										$state.go('editor.reportlist');
										break;
									}
									case CODE.ADMIN: {
										$state.go('admin.dashboard')
									}
								}
								deferred.reject();
								break;
							}

							//haven't logged in  yet
							default: {
								deferred.resolve();
							}
						}
					}, function() {
						deferred.reject();
					});
					return deferred.promise;
				};
			}

			//check if editor --> resolve, if user/admin --> 403
			if (newState.authorizeEditor) {
				newState.resolve.checkLoggedIn = function(UserService, $state, $q, veazyConfig) {
					var CODE = veazyConfig.CODE;
					var deferred = $q.defer();
					var role;
					UserService.checkLoggedIn().then(function(response) {
						switch (response.code) {
							//already login
							case CODE.SUCCESS: {
								role = response.data.role;
								if (role === CODE.EDITOR) {
									deferred.resolve();
								} else {
									deferred.reject();
									$state.go('forbidden');
								}
								break;
							}

							//haven't logged in  yet
							default: {
								deferred.reject();
								$state.go('login');
							}
						}
					}, function() {
						deferred.reject();
					});
					return deferred.promise;
				};
			}

			//check if editor --> resolve, if user/admin --> 403
			if (newState.authorizeAdmin) {
				newState.resolve.checkLoggedIn = function(UserService, $state, $q, veazyConfig) {
					var CODE = veazyConfig.CODE;
					var deferred = $q.defer();
					var role;
					UserService.checkLoggedIn().then(function(response) {
						switch (response.code) {
							//already login
							case CODE.SUCCESS: {
								role = response.data.role;
								if (role === CODE.ADMIN) {
									deferred.resolve();
								} else {
									deferred.reject();
									$state.go('forbidden');
								}
								break;
							}

							//haven't logged in  yet
							default: {
								deferred.reject();
								$state.go('login');
							}
						}
					}, function() {
						deferred.reject();
					});
					return deferred.promise;
				};
			}
		});
	}]);

	//constants definition
	app.constant('veazyConfig', {
		CODE: {
			BEGINNER_LEVEL: 1,
			UPPER_BEGINNER_LEVEL: 2,
			INTERMEDIATE_LEVEL: 3,
			UPPER_INTERMEDIATE_LEVEL: 4,
			ADVANCED_LEVEL: 5,
			MASTER_LEVEL: 6,
			SINGLE_QUESTION_TYPE: 1,
			GROUP_QUESTION_TYPE: 2,
			VOCABULARY_SKILL: 1,
			GRAMMAR_SKILL: 2,
			LISTENING_SKILL: 3,
			READING_SKILL: 4,

			ADMIN: 1,
			EDITOR: 2,
			USER: 3,

			SUCCESS: 200,
			UNAUTHORIZED: 401,
			NO_PERMISSION: 403,
			INCORRECT_USERNAME_PASSWORD: 470,
			// INVALID_EMAIL: 410,
			// DUPPLICATED_EMAIL: 411,
			// DUPPLICATED_USERNAME: 413,
			BAD_REQUEST: 500
		},
		levels: [{
			id: 1,
			name: 'BEGINNER_LEVEL'
		}, {
			id: 2,
			name: 'UPPER_BEGINNER_LEVEL'
		}, {
			id: 3,
			name: 'INTERMEDIATE_LEVEL'
		}, {
			id: 4,
			name: 'UPPER_INTERMEDIATE_LEVEL'
		}, {
			id: 5,
			name: 'ADVANCED_LEVEL'
		}, {
			id: 6,
			name: 'MASTER_LEVEL'
		}],
		questionTypes: [{
			id: 1,
			name: 'SINGLE_QUESTION_TYPE'
		}, {
			id: 2,
			name: 'GROUP_QUESTION_TYPE'
		}],
		testSkills: [{
			id: 1,
			name: 'VOCABULARY_SKILL'
		}, {
			id: 2,
			name: 'GRAMMAR_SKILL'
		}, {
			id: 3,
			name: 'LISTENING_SKILL'
		}, {
			id: 4,
			name: 'READING_SKILL'
		}],
		lessonStates: [{
			id: 1,
			name: 'Published'
		}, {
			id: 2,
			name: 'Draft'
		}],
		roles: [
		// {
			// id: 1, 
			// name: 'ADMIN'
		// }, 
		{
			id: 2, 
			name: 'EDITOR'
		}, {
			id: 3, 
			name: 'USER'
		}],
		statuses: [{
			id: 1,
			name: 'BANNED'
		}, {
			id: 2,
			name: 'ACTIVE'
		}],
		REGULATIONS: {
			USERNAME_MIN_LENGTH: 6,
			USERNAME_MAX_LENGTH: 30,
			PASSWORD_MIN_LENGTH: 6,
			PASSWORD_MAX_LENGTH: 32,
			MIN_QUESTION_NUMBER_IN_EXAM: 5
		},
		MESSAGE: {
			470: 'INCORRECT_USERNAME_PASSWORD',
			410: 'INVALID_EMAIL',
			411: 'DUPPLICATED_EMAIL',
			413: 'DUPPLICATED_USERNAME',
			500: 'BAD_REQUEST'
		}
	});
})();
