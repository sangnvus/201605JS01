;(function() {
	'use strict';
	var veazyFactories = angular.module('veazyFactories');

	var Report = function($resource, $httpParamSerializer) {
		var url = '/Veazy/api/report';
		return $resource(url, {}, {
			getList: {
				url: url + '/all',
				method: 'GET'
			},
			getDetail: {
				url: url + '/get/:reportId',
				method: 'GET',
				params: {
					reportId: '@reportId'
				}
			}
		});
	};

	Report.$inject = ['$resource', '$httpParamSerializer'];
	veazyFactories.factory('Report', Report);
})();