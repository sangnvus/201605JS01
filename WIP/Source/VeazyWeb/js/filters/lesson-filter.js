;(function() {
	var lessonFilter = function($filter) {
		return function(input, courseId) {
			if (input == null) {
				return;
			}

			var queryObj = {};

			if (courseId && courseId != 0) {
				queryObj.courseId = courseId;
			}

			// console.log(queryObj);

			return $filter('filter')(input, queryObj);
		};
	};

	lessonFilter.$inject = ['$filter'];
	angular.module('veazyDirectives').filter('lesson', lessonFilter);
})();