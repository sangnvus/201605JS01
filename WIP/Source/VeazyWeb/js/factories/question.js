;(function() {
	'use strict';
	var veazyFactories = angular.module('veazyFactories');

	veazyFactories.factory('Question', ['$resource', function($resource) {
		var url = '/Veazy/api/questions';
		//$resource (url, {}, {method_name: {method: type_of_method, params: {param_id: param_value}}})
		return $resource(url, {}, {
			create: {
				url: url + '/new',
				method: 'POST',
				headers: {
					'Content-Type': 'application/json; charset=UTF-8'
				}
			},
			getAllQuestions: {
				url: url,
				method: 'GET'
			},
			getDetail: {
				url: url + '/:questionId',
				method: 'GET',
				params: {
					questionId: '@questionId'
				}
			},
			update: {
				url: url + '/update/:questionId',
				method: 'POST',
				headers: {
					'Content-Type': 'application/json; charset=UTF-8'
				},
				params: {
					questionId: '@questionId'
				}
			},
			delete: {
				url: url + '/delete/:questionId',
				method: 'GET',
				params: {
					questionId: '@questionId'
				}
			}
		});
	}]);
})();