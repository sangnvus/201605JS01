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
			}
		});
	};

	Exam.$inject = ['$resource', '$httpParamSerializer'];
	veazyFactories.factory('Exam', Exam);
})();