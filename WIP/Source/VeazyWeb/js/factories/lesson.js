;(function() {
	'use strict';
	var veazyFactories = angular.module('veazyFactories');

	veazyFactories.factory('Lesson', ['$resource', '$httpParamSerializer', function($resource, $httpParamSerializer) {
		var url = '/Veazy/api';

		function transformUrlEncoded(data) {
		    return $httpParamSerializer(data); 
		}
		//$resource (url, {}, {method_name: {method: type_of_method, params: {param_id: param_value}}})
		return $resource(url, {}, {
			getLessonsInCourse: {
				url: url + '/courses/:courseId/lessons',
				method: 'GET',
				// headers: {
				// 	'Content-Type': 'application/x-www-form-urlencoded'
				// },
				// transformRequest: transformUrlEncoded,
				params: {
					courseId: '@courseId'
				}
			},
			getAllLessons: {
				url: url + '/lessons',
				method: 'GET'
			},
			getDetail: {
				url: url + '/lessons/:lessonId',
				method: 'GET',
				params: {
					lessonId: '@lessonId'
				}
			},
			getDraft: {
				url: url + '/lessons/:lessonId',
				method: 'GET',
				params: {
					lessonId: '@lessonId',
					action: 'edit'
				}
			},
			create: {
				url: url + '/lessons/new',
				method: 'POST',
				headers: {
					'Content-Type': 'application/json; charset=UTF-8'
				}
			},
			saveDraft: {
				url: url + '/lessons/savedraft/:lessonId',
				method: 'POST',
				headers: {
					'Content-Type': 'application/json; charset=UTF-8'
				},
				params: {
					lessonId: '@lessonId'
				}
			},
			update: {
				url: url + '/lessons/update/:lessonId',
				method: 'POST',
				headers: {
					'Content-Type': 'application/json; charset=UTF-8'
				},
				params: {
					lessonId: '@lessonId'
				}
			},
			delete: {
				url: url + '/lessons/delete/:lessonId',
				method: 'GET',
				params: {
					lessonId: '@lessonId'
				}
			},
			report: {
				url: url + '/lessons/report/:lessonId',
				method: 'POST',
				headers: {
					'Content-Type': 'application/json; charset=UTF-8'
				},
				params: {
					lessonId: '@lessonId'
				}
			}
		});
	}]);
})();