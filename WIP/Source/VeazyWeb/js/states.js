;(function() {
	var app = angular.module('veazyApp');
	app.config(['$stateProvider', '$urlRouterProvider', 'veazyConfig', function($stateProvider, $urlRouterProvider, veazyConfig) {
		//nested state & view
		$urlRouterProvider.otherwise('/home');

		// var authorizeUser = function(UserService, $state, $q, veazyConfig) {
		// 	var CODE = veazyConfig.CODE;
		// 	var deferred = $q.defer();
		// 	var role;
		// 	UserService.checkLoggedIn().then(function(response) {
		// 		console.log(response);
		// 		switch (response.code) {
		// 			//already login
		// 			case CODE.SUCCESS: {
		// 				role = response.data.role;
		// 				switch (role) {
		// 					case CODE.USER: {
		// 						$state.go('home');
		// 						break;
		// 					}
		// 					case CODE.EDITOR: {
		// 						$state.go('editordashboard.reportlist');
		// 						break;
		// 					}
		// 					default: {

		// 					}
		// 				}
		// 				deferred.reject();
		// 			}

		// 			default: {
		// 				deferred.resolve();
		// 			}
		// 		}
		// 		// deferred.reject();
		// 	}, function() {
		// 		// deferred.resolve();
		// 		deferred.reject();
		// 	});
		// 	return deferred.promise;
		// };
		// authorizeUser.$inject = ['UserService', '$state', '$q', 'veazyConfig'];

		$stateProvider.state('pagenotfound', {
			url: '/error',
			templateUrl: 'partials/404-error.html'
		}).state('home', {
			url: '/home',
			templateUrl: 'partials/home.html',
			controller: 'homeCtrl'
		}).state('login', {
			url: '/login',
			templateUrl: 'partials/login.html',
			controller: 'loginCtrl',
			checkLoggedIn: true,
			resolve: {}
			// resolve: {
			// 	// authorizeUser: authorizeUser
			// }
		}).state('register', {
			abstract: true,
			url: '/register',
			template: '<div ui-view></div>'
		}).state('register.form', {
			url: '',
			templateUrl: 'partials/register.html',
			controller: 'registerCtrl',
			checkLoggedIn: true,
			resolve: {}
			// resolve: {
			// 	// authorizeUser: authorizeUser
			// }
		}).state('register.success', {
			url: '/success',
			params: {
				username: null
			},
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
		}).state('courselist', {
			url: '/courses/:courseId',
			templateUrl: 'partials/course-list.html',
			controller: 'courseListCtrl'
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
							}
						}
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}
			}
		}).state('test', {
			url: '/test',
			templateUrl: 'partials/test.html',
			controller: 'testCtrl'
		}).state('test.taketest', {
			url: '/take',
			params: {
				exam: null
				// level: null,
				// skill: null,
				// numberOfQuestion: null
			},
			resolve: {
				getExam: function($timeout, $state, $stateParams, $q, ExamService, veazyConfig) {
					
					var deferred = $q.defer();
					$timeout(function() {
						if ($stateParams.exam) {
							deferred.resolve();
						} else {
							deferred.reject();
							$state.go('test');
						}
					});
					return deferred.promise;
				// 	var CODE = veazyConfig.CODE;
				// 	var levelId = $stateParams.level;
				// 	var skillId = $stateParams.skill;
				// 	var numberOfQuestion = parseInt($stateParams.numberOfQuestion);
				// 	var deferred = $q.defer();

				// 	// console.log(levelId, skillId, numberOfQuestion);
					
				// 	$timeout(function() {
				// 		//in case of reload page, redirect to test setup
				// 		if (!levelId || !skillId || !numberOfQuestion) {
				// 			deferred.reject();
				// 			$state.go('test');
				// 		} else {
				// 			//create test
				// 			var exam = {
				// 				courseId: levelId,
				// 				skill: skillId,
				// 				numberOfQuestion: numberOfQuestion
				// 			};
				// 			ExamService.create(exam).then(function(response) {
				// 				console.log(response);
				// 				switch (response.code) {
				// 					case CODE.SUCCESS: {
				// 						deferred.resolve(response);
				// 						break;
				// 					}
				// 					default: {
				// 						$state.go('test');
				// 						deferred.reject();
				// 					}
				// 				}
				// 			}, function(reject) {
				// 				deferred.reject()
				// 			});
				// 		}
				// 	});

				// 	return deferred.promise;
				}
			},
			templateUrl: 'partials/take-test.html',
			controller: 'takeTestCtrl'
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
		}).state('user', {
			url: '/user',
			abstract: true,
			templateUrl: 'partials/logged-user-restricted/user.html',
			controller: 'userCtrl'
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
		}).state('user.statistics', {
			url: '/statistics',
			templateUrl: 'partials/logged-user-restricted/user-statistics.html',
			controller: 'userStatisticsCtrl'
		})
		//dashboard of editor
		.state('editordashboard', {
			// url: '',
			url: '/dashboard',
			abstract: true,
			templateUrl: 'partials/content-editor-restricted/editor-dashboard.html',
			controller: 'editorSideBarCtrl'
		}).state('editordashboard.reportlist', {
			url: '/reports',
			templateUrl: 'partials/content-editor-restricted/report-list.html',
			controller: 'reportListCtrl'
		})
		//lesson management
		.state('editordashboard.lesson', {
			url: '/lessons',
			abstract: true,
			template: '<div ui-view></div>'
		}).state('editordashboard.lesson.add', {
			url: '/add',
			templateUrl: 'partials/content-editor-restricted/add-lesson.html',
			controller: 'addLessonCtrl',
			ncyBreadcrumb: {
				label: 'New',
				parent: 'editordashboard.lesson.list'
			}
		}).state('editordashboard.lesson.list', {
			url: '',
			templateUrl: 'partials/content-editor-restricted/lesson-list.html',
			controller: 'lessonListCtrl',
			ncyBreadcrumb: {
				label: 'Lesson'
			}
		}).state('editordashboard.lesson.detail', {
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
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}]
			}
		}).state('editordashboard.lesson.edit', {
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
								$state.go('login');
								deferred.reject();
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
		//question
		.state('editordashboard.question', {
			abstract: true,
			url: '/questions',
			template: '<div ui-view><div>'
		}).state('editordashboard.question.add', {
			url: '/add',
			templateUrl: 'partials/content-editor-restricted/add-question.html',
			controller: 'addQuestionCtrl'
		}).state('editordashboard.question.list', {
			url: '',
			templateUrl: 'partials/content-editor-restricted/question-list.html',
			controller: 'questionListCtrl'
		}).state('editordashboard.question.detail', {
			url: '/:questionId',
			controller: 'questionDetailCtrl',
			templateUrl: 'partials/content-editor-restricted/question-detail.html'
		}).state('editordashboard.question.edit', {
			url: '/:questionId/edit',
			templateUrl: 'partials/content-editor-restricted/edit-question.html',
			controller: 'editQuestionCtrl',
			resolve: {
				getQuestion: ['$state', '$stateParams', '$q','veazyConfig', 'QuestionService',
				function($state, $stateParams, $q, veazyConfig, QuestionService) {
					var deferred = $q.defer();
					var questionId = $stateParams.questionId;
					QuestionService.getDetail(questionId).then(function(response) {
						deferred.resolve(response);
					}, function(reject) {
						deferred.reject();
					});
					return deferred.promise;
				}]
			}
		})

		//dashboard of admin
		.state('admin', {
			// url: '',
			url: '/admin',
			// abstract: true,
			templateUrl: 'partials/admin-restricted/admin.html',
			controller: 'adminSideBarCtrl'
		})
		.state('admin.dashboard', {
			url: '/dashboard',
			templateUrl: 'partials/admin-restricted/dashboard.html'
		})
		.state('admin.users', {
			url: '/users',
			templateUrl: 'partials/admin-restricted/users.html'
		})
		;
	}]);

	app.run(['$rootScope', '$state', function($rootScope, $state) {
		$rootScope.$on('$stateChangeStart', function(event, newState, newStateParams, oldState, oldStateParams) {

			//check if user logged in, if yes --> prevent from going back to login/register page
			if (newState.checkLoggedIn) {
				newState.resolve.checkLoggedIn = function(UserService, $state, $q, veazyConfig) {
					var CODE = veazyConfig.CODE;
					var deferred = $q.defer();
					var role;
					UserService.checkLoggedIn().then(function(response) {
						console.log(response);
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
										$state.go('editordashboard.reportlist');
										break;
									}
									default: {

									}
								}
								deferred.reject();
							}

							default: {
								deferred.resolve();
							}
						}
						// deferred.reject();
					}, function() {
						// deferred.resolve();
						deferred.reject();
					});
					return deferred.promise;
				};
			}

			// if (newState.autho)
		});
	}]);
})();