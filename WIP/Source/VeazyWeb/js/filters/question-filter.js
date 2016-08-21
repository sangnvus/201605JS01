;(function() {
	'use strict';
	var veazyFilters = angular.module('veazyFilters');

	veazyFilters.filter('question', ['veazyConfig', '$filter', function(veazyConfig, $filter) {
		return function(input, questionType, courseId, questionSkill, queryStr) {
			if (input == null) {
				return;
			}

			var queryObj = {};
			if (questionType && questionType != 0) {
				queryObj.questionType = questionType;
			}
			if (courseId && courseId != 0) {
				queryObj.courseId = courseId;
			}
			if (questionSkill && questionSkill != 0) {
				queryObj.questionSkill = questionSkill;
			}
			// console.log(queryObj);
			return $filter('filter')(input, queryObj);
		};
	}]);
})();