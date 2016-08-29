;(function() {
	'use strict';
	var veazyFilters = angular.module('veazyFilters');

	veazyFilters.filter('question', ['veazyConfig', '$filter', function(veazyConfig, $filter) {
		return function(input, questionType, courseId, questionSkill, keyword) {
			if (input == null) {
				return;
			}

			// console.log(keyword);
			var filteredList = [];
			var question;
			var listQuestions;
			var listAnswers;
			var found;

			//if there are keyword then search all answers that have keyword
			if (keyword) {
				for (var i = 0; i < input.length; i++) {
					question = input[i];
					found = false;
					if (question.question.toLowerCase().includes(keyword.toLowerCase())) {
						filteredList.push(question);
						found = true;
					}
					if (question.questionSkill === veazyConfig.CODE.READING_SKILL) {
						listQuestions = question.listQuestions;
						if ($filter('filter')(listQuestions, {question: keyword}).length > 0 && found === false) {
							filteredList.push(question);
							found = true;
						}

						listQuestions.forEach(function(singleQuestion) {
							listAnswers = singleQuestion.listAnswers;
							if ($filter('filter')(listAnswers, {answer: keyword}).length > 0 && found === false) {
								filteredList.push(question);
								found = true;
							}
						});
					} else {
						listAnswers = question.listAnswers;
						if ($filter('filter')(listAnswers, {answer: keyword}).length > 0 && found === false) {
							filteredList.push(question);
							found = true;
						}
					}
				}
			} else {
				filteredList = input;
			}

			console.log(filteredList);

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
			// return $filter('filter')(input, queryObj);
			return $filter('filter')(filteredList, queryObj);
		};
	}]);
})();