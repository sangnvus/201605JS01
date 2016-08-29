;(function() {
	'use strict';
	var veazyFactories = angular.module('veazyFactories');

	veazyFactories.factory('User', ['$resource', '$httpParamSerializer', function($resource, $httpParamSerializer) {
		var url = '/Veazy/api';

		function transformUrlEncoded(data) {
		    return $httpParamSerializer(data); 
		}
		//$resource (url, {}, {method_name: {method: type_of_method, params: {param_id: param_value}}})
		return $resource(url, {}, {
			register: {
				url: url + '/register',
				method: 'POST'
			},
			login: {
				url: url + '/login',
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				transformRequest: transformUrlEncoded
			},
			logout: {
				url: url + '/logout',
				method: 'GET'
			},
			getCurrentUser: {
				url: url + '/user',
				method: 'GET'
			},
			updateProfile: {
				url: url + '/user/update',
				method: 'POST',
				headers: {
					'Content-Type': 'application/json; charset=UTF-8'
				}
			},
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
			},
			changePassword: {
				url: url + '/user/chgpwd',
				method: 'POST'
			},
			getUserList: {
				url: url + '/users',
				method: 'GET'
			},
			getUserDetail: {
				url: url + '/users/:userId',
				method: 'GET',
				params: {
					userId: '@userId'
				}
			},
			ban: {
				url: url + '/users/ban/:userId',
				method: 'GET',
				params: {
					userId: '@userId'
				}
			},
			unban: {
				url: url + '/users/unban/:userId',
				method: 'GET',
				params: {
					userId: '@userId'
				}
			},
			changeRole: {
				url: url + '/users/change_roll/:userId',
				method: 'POST',
				params: {
					userId: '@userId'
				}
			},
			makeEditor: {
				url: '/Veazy/mked/:username',
				method: 'GET',
				params: {
					username: '@username'
				}
			}
		});
	}]);
})();