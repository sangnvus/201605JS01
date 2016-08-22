;(function() {
	'use strict';
	var veazyFactories = angular.module('veazyFactories');

	var Exam = function($resource, $httpParamSerializer) {
		var url = '/Veazy/api/exams';
		return $resource(url, {}, {
			create: {
				url: url,
				method: 'POST'
			},
			submit: {
				url: url + '/submit',
				method: 'POST' 
			},
			getHistoryList: {
				url: '/Veazy/api/user/exams',
				method: 'GET'
			},
			getHistory: {
				url: url + '/:examId',
				method: 'GET',
				params: {
					examId: '@examId'
				}
			},
			redo: {
				url: url + '/:examId/redo',
				method: 'GET',
				params: {
					examId: '@examId'
				}
			}
		});
	};

	Exam.$inject = ['$resource', '$httpParamSerializer'];
	veazyFactories.factory('Exam', Exam);
})();