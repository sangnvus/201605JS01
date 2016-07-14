;(function() {
	;'use strict';

	var app = angular.module('veazyApp', ['ngRoute', 'veazyControllers', 'veazyDirectives', 'ngAnimate', 'ngDialog', 'ngSanitize', 'froala', 'ui.router']);

	app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
		//ngRoute
		// $routeProvider.when('/home', {
		// 	templateUrl: 'partials/home.html',
		// 	controller: 'homeCtrl'
		// }).when('/login', {
		// 	templateUrl: 'partials/login.html',
		// 	controller: 'loginCtrl'
		// }).when('/register', {
		// 	templateUrl: 'partials/register.html',
		// 	controller: 'registerCtrl'
		// }).when('/courselist', {
		// 	templateUrl: 'partials/course-list.html',
		// 	controller: 'courseListCtrl'
		// }).when('/courselist/lessondetail', {
		// 	templateUrl: 'partials/lesson-detail.html',
		// 	controller: 'lessonDetailCtrl'
		// }).when('/test', {
		// 	templateUrl: 'partials/test.html',
		// 	controller: 'testCtrl'
		// }).when('/addlesson', {
		// 	templateUrl: 'partials/content-editor-restricted/add-lesson.html',
		// 	controller: 'addLessonCtrl'
		// }).when('/addquestion', {
		// 	templateUrl: 'partials/content-editor-restricted/add-question.html',
		// 	controller: 'addQuestionCtrl'
		// }).when('/editordashboard', {
		// 	templateUrl: 'partials/content-editor-restricted/editor-dashboard.html'
		// });

		//nested state & view
		$urlRouterProvider.otherwise("/home");

		$stateProvider.state('home', {
			url: '/home',
			templateUrl: 'partials/home.html',
			controller: 'homeCtrl'
		}).state('login', {
			url: '/login',
			templateUrl: 'partials/login.html',
			controller: 'loginCtrl'
		}).state('register', {
			url: '/register',
			templateUrl: 'partials/register.html',
			controller: 'registerCtrl'
		}).state('courselist', {
			url: '/courselist',
			templateUrl: 'partials/course-list.html',
			controller: 'courseListCtrl'
		}).state('lessondetail', {
			url: '/lessondetail',
			templateUrl: 'partials/lesson-detail.html',
			controller: 'lessonDetailCtrl'
		}).state('test', {
			url: '/test',
			templateUrl: 'partials/test.html',
			controller: 'testCtrl'
		}).state('editordashboard', {
			url: '/editordashboard',
			templateUrl: 'partials/content-editor-restricted/editor-dashboard.html'
		}).state('editordashboard.addlesson', {
			url: '/addlesson',
			templateUrl: 'partials/content-editor-restricted/add-lesson.html',
			controller: 'addLessonCtrl'
		}).state('editordashboard.questionlist', {
			url: '/questionlist',
			templateUrl: 'partials/content-editor-restricted/question-list.html',
			controler: 'questionListCtrl'
		}).state('editordashboard.addquestion', {
			url: '/addquestion',
			templateUrl: 'partials/content-editor-restricted/add-question.html',
			controller: 'addQuestionCtrl'
		});
	}]);

	// app.run(['$rootScope', '$location', '$anchorScroll', function($rootScope, $location, $anchorScroll) {
	// 	//when the route is changed, scroll to the proper element
	// 	$rootScope.$on('$routeChangeSuccess', function(newRoute, oldRoute) {
	// 		if ($location.hash()) {
	// 			$anchorScroll();
	// 		}
	// 	});
	// }]);

	app.constant('veazyConfig', {
		levels: [{
			id: 1,
			name: 'Beginner'
		}, {
			id: 2,
			name: 'Upper-beginner'
		}, {
			id: 3,
			name: 'Intermediate'
		}, {
			id: 4,
			name: 'Upper-intermediate'
		}, {
			id: 5,
			name: 'Advanced'
		}, {
			id: 6,
			name: 'Master'
		}],
		lessonSections: [{
			id: 1,
			name: '語彙'
		}, {
			id: 2,
			name: '文法'
		}, {
			id: 3,
			name: '会話'
		}, {
			id: 4,
			name: '聴解'
		}, {
			id: 5,
			name: '練習'
		}],
		questionTypes: [{
			id: 1,
			name: 'Single'
		}, {
			id: 2,
			name: 'Group'
		}],
		testSkills: [{
			id: 1,
			name: 'Vocabulary'
		}, {
			id: 2,
			name: 'Grammar'
		}, {
			id: 3,
			name: 'Listening'
		}, {
			id: 4,
			name: 'Reading'
		}]
	});

	app.value('froalaConfig', {
		language: 'ja',
		imageUploadURL: 'http://haha.com'
	});
})();