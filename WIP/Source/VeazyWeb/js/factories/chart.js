;(function() {
	'use strict';

	var Chart = function($resource) {
		var url = '/Veazy/api';
		return $resource(url, {}, {
			getSkillStats: {
				url: url + '/stats/skill/avg',
				method: 'GET'
			},
			getExamMarkStats: {
				url: url + '/stats/exam/last/:numberOfExams',
				method: 'GET',
				params: {
					numberOfExams: '@numberOfExams'
				}
			},
			getLevelStats: {
				url: url + '/stats/course/avg',
				method: 'GET'
			}
		});
	};

	Chart.$inject = ['$resource'];
	angular.module('veazyFactories').factory('Chart', Chart);
})();