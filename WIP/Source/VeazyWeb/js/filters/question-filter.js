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

			for (var i = 0; i < input.length; i++) {
				var question = input[i];
				//reading skill
				var found = false;
				if (question.questionSkill === veazyConfig.CODE.READING_SKILL) {
					
					//check content
					if (question.question.includes(keyword)) {
						filteredList.push(question);
						found = true;
						// break;
					}

					var listQuestions = question.listQuestions;
					for (var j = 0; j < listQuestions.length; j++) {
						//check questions
						if (listQuestions[j].question.includes(keyword) && found === false) {
							filteredList.push(question);
							found = true;
							// break;
						}

						var listAnswers = listQuestions[j].listAnswers;
						for (var k = 0; k < listAnswers.length; k++) {
							//check answers
							if (listAnswers[k].answer.includes(keyword) && found === false) {
								filteredList.push(question);
								found = true;
								// break;
							}
						}
					}
				} else {
					if (question.question.includes(keyword)) {
						filteredList.push(question);
						found = true;
						// break;
					}
					var listAnswers = question.listAnswers;
					for (var m = 0; m < listAnswers.length; m++) {
						//check answers
						if (listAnswers[m].answer.includes(keyword) && found === false) {
							filteredList.push(question);
							found = true;
							// break;
						}
					}
				}
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