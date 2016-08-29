;(function() {
	var lessonFilter = function($filter) {
		return function(input, courseId, keyword) {
			if (input == null || input.length === 0) {
				return;
			}

			var ALL_LEVEL_CODE = 0;
			var lessonArr;
			var queryObj = {};

			//search lessons that have the keyword
			if (keyword) {
				console.log(input);
				lessonArr = $filter('filter')(input, keyword);
			} else {
				lessonArr = input;
			}
			console.log(lessonArr);

			if (courseId && courseId != ALL_LEVEL_CODE) {
				queryObj.courseId = courseId;
			}

			//return lessons of that course level and have the keyword
			return $filter('filter')(lessonArr, queryObj);
		};
	};

	lessonFilter.$inject = ['$filter'];
	angular.module('veazyDirectives').filter('lesson', lessonFilter);
})();