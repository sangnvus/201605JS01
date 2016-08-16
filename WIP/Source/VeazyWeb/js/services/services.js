;(function() {
	var veazyServices = angular.module('veazyServices', []);

	// veazyServices.service('QuestionService', ['Question', '$q', '$timeout', 'veazyConfig', function(Question, $q, $timeout, veazyConfig) {
	// 	var CODE = veazyConfig.CODE;
	// 	var MESSAGE = veazyConfig.MESSAGE;
		
	// 	return {
	// 		create: function(question) {
	// 			var deferred = $q.defer();
	// 			var questionFactory = new Question(question);

	// 			$timeout(function() {
	// 				questionFactory.$create(function(response) {
	// 					deferred.resolve(response);
	// 				}, function() {
	// 					deferred.reject('ERROR_CONNECTION');
	// 				});
	// 			});
	// 			return deferred.promise;
	// 		},
	// 		getAllQuestions: function() {
	// 			var deferred = $q.defer();
	// 			var questionFactory = new Question();

	// 			$timeout(function() {
	// 				questionFactory.$getAllQuestions(function(response) {
	// 					deferred.resolve(response);
	// 				}, function() {
	// 					deferred.reject(MESSAGE[response.code]);
	// 				});
	// 			});
	// 			return deferred.promise;
	// 		},
	// 		getDetail: function(questionId) {
	// 			var deferred = $q.defer();
	// 			var questionFactory = new Question({
	// 				questionId: questionId
	// 			});

	// 			$timeout(function() {
	// 				questionFactory.$getDetail(function(response) {
	// 					deferred.resolve(response);
	// 				}, function() {
	// 					deferred.reject();
	// 				});
	// 			});
	// 			return deferred.promise;
	// 		},
	// 		update: function(question) {
	// 			var deferred = $q.defer();
	// 			var questionFactory = new Question(question);

	// 			$timeout(function() {
	// 				questionFactory.$update(function(response) {
	// 					deferred.resolve(response);
	// 				}, function() {
	// 					deferred.reject();
	// 				});
	// 			});
	// 			return deferred.promise;
	// 		},
	// 		delete: function(questionId) {
	// 			var deferred = $q.defer();
	// 			var questionFactory = new Question({
	// 				questionId: questionId
	// 			});

	// 			$timeout(function() {
	// 				questionFactory.$delete(function(response) {
	// 					deferred.resolve(response);
	// 				}, function() {
	// 					deferred.reject();
	// 				});
	// 			});
	// 			return deferred.promise;
	// 		}
	// 	};
	// }]);

	// veazyServices.service('ValidateHelper', [function() {
	// 	return new function() {
	// 		var self = this;

	// 		//check if a string is null
	// 		this.isNullString = function(input) {
	// 			if (!input || typeof input !== 'string') {
	// 				return true;
	// 			}
	// 			if (input.trim().length === 0) {
	// 				return true;
	// 			}
	// 			return false;
	// 		};

	// 		//check if a HTML string is null or have no text content
	// 		this.isNullHTMLString = function(input) {
	// 			if (self.isNullString(input)) {
	// 				return true;
	// 			}
	// 			var text = input.replace(/<(?:.|\n)*?>/gm, '').split('&nbsp;').join('').trim();
	// 			if (text.length === 0) {
	// 				return true;
	// 			}
	// 			return false;
	// 		};

	// 		//check if a lesson has a null or blank content
	// 		this.isNullLesson = function(lesson) {
	// 			var isNullString = self.isNullString;
	// 			var isNullHTMLString = self.isNullHTMLString;
	// 			if (isNullString(lesson.title) || isNullString(lesson.description)) {
	// 				// console.log('1');
	// 				return true;
	// 			}

	// 			if (isNullHTMLString(lesson.vocabulary) || isNullHTMLString(lesson.grammar) || 
	// 				isNullHTMLString(lesson.conversation) || isNullHTMLString(lesson.listening) || 
	// 				isNullHTMLString(lesson.practice) || isNullHTMLString(lesson.reading)) {
	// 				// console.log('2');
	// 				return true;
	// 			}
	// 			return false;
	// 		};

	// 		//check if a question has a null or blank content
	// 		this.isNullSingleQuestion = function(question) {
	// 			if (!question) {
	// 				return true;
	// 			}

	// 			var isNullString = self.isNullString;
	// 			if (isNullString(question.question)) {
	// 				return true;
	// 			}

	// 			var listAnswers = question.listAnswers;

	// 			for (var i = 0; i < listAnswers.length; i++) {
	// 				var answer = listAnswers[i].answer;
	// 				if (isNullString(answer)) {
	// 					return true;
	// 				}
	// 			}
	// 			return false;
	// 		},

	// 		this.isNullGroupQuestion = function(question) {
	// 			if (!question) {
	// 				// console.log('1');
	// 				return true;
	// 			}

	// 			var isNullString = self.isNullString;
	// 			var isNullHTMLString = self.isNullHTMLString;
				
	// 			//check reading content
	// 			var sharedContent = question.question;
	// 			if (isNullString(sharedContent)) {
	// 				// console.log('2');
	// 				return true;
	// 			}
	// 			if (isNullHTMLString(sharedContent)) {
	// 				// console.log('3');
	// 				return true;
	// 			}

	// 			//check questions in group
	// 			var listQuestions = question.listQuestions;
	// 			if (!listQuestions || listQuestions.length === 0) {
	// 				// console.log('4');
	// 				return true;
	// 			}

	// 			for (var i = 0; i < listQuestions.length; i++) {
	// 				var singleQuestion = listQuestions[i];
	// 				if (isNullString(singleQuestion.question)) {
	// 					// console.log('5');
	// 					return true;
	// 				}

	// 				//check answers in a single question
	// 				var listAnswers = singleQuestion.listAnswers;
	// 				if (!listAnswers || listAnswers.length === 0) {
	// 					// console.log('6');
	// 					return true;
	// 				}
	// 				for (var j = 0; j < listAnswers.length; j++) {
	// 					var answer = listAnswers[j].answer;
	// 					if (isNullString(answer)) {
	// 						// console.log('7');
	// 						return true;
	// 					}
	// 				}
	// 			}
	// 			return false;
	// 		},

	// 		this.hasNoRightAnswer = function(question) {
	// 			if (!question) {
	// 				return true;
	// 			}

	// 			var listAnswers = question.listAnswers;
	// 			if (!listAnswers || listAnswers.length === 0) {
	// 				return true;
	// 			}

	// 			var numberOfRightAnswers = 0;

	// 			listAnswers.forEach(function(answer) {
	// 				if (answer.isRight) {
	// 					numberOfRightAnswers++;
	// 				}
	// 			});

	// 			if (numberOfRightAnswers === 0) {
	// 				return true;
	// 			} else {
	// 				return false;
	// 			}
	// 		},

	// 		this.hasNoRightAnswerInGroup = function(groupQuestion) {
	// 			var listQuestions = groupQuestion.listQuestions;
	// 			var listAnswers;
	// 			var singleQuestion;
	// 			var answer;
	// 			var numberOfRightAnswers;

	// 			if (!listQuestions || listQuestions.length === 0) {
	// 				return true;
	// 			}

	// 			for (var i = 0; i < listQuestions.length; i++) {
	// 				singleQuestion = listQuestions[i];
	// 				listAnswers = singleQuestion.listAnswers;
	// 				numberOfRightAnswers = 0;

	// 				for (var j = 0; j < listAnswers.length; j++) {
	// 					answer = listAnswers[j];
	// 					if (answer.isRight) {
	// 						numberOfRightAnswers++;
	// 					}
	// 				}

	// 				if (numberOfRightAnswers === 0) {
	// 					return true;
	// 				}
	// 			}
	// 			return false;
	// 		}
	// 	};
	// }]);

	// veazyServices.service('ExamService', ['$q', '$timeout', 'Exam', function($q, $timeout, Exam) {
	// 	return {
	// 		create: function(examInput) {
	// 			var deferred = $q.defer();
	// 			var ExamFactory = new Exam(examInput);

	// 			$timeout(function() {
	// 				ExamFactory.$create(function(response) {
	// 					deferred.resolve(response);
	// 				}, function(reject) {
	// 					deferred.reject();
	// 				});
	// 			});
	// 			return deferred.promise;
	// 		},
	// 		submit: function(examOutput) {
	// 			var deferred = $q.defer();
	// 			var ExamFactory = new Exam(examOutput);

	// 			$timeout(function() {
	// 				ExamFactory.$submit(function(response) {
	// 					deferred.resolve(response);
	// 				}, function(reject) {
	// 					deferred.reject();
	// 				});
	// 			});
	// 			return deferred.promise;
	// 		}
	// 	};
	// }]);
})();