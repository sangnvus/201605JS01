;(function() {
	var Helper = function($sce, veazyConfig) {
		return new function() {
			var self = this;

			this.calculateSingleQuestionMark = function(question) {

				if (!question || !question.listAnswers || question.listAnswers.length === 0) {
					return false;
				}
				var rightAnswerNo = 0;			//the number of right answer(s) in a question
				var checkedAnswerNo = 0;		//the number of answer(s) checked by user
				var checkedRightAnswerNo = 0;	//the number of right answer(s) checked by user

				var questionPts;
				var answer;

				for (var i = 0; i < question.listAnswers.length; i++) {
					answer = question.listAnswers[i];

					if (answer.isRight) {
						rightAnswerNo++;
					}

					if (answer.isSelected) {
						checkedAnswerNo++;
					
						//the number of correct answers ticked by user
						if (answer.isRight) {
							checkedRightAnswerNo++;
						} 
					}

					//user check bot right and wrong answers
					if (checkedRightAnswerNo < checkedAnswerNo) {
						checkedRightAnswerNo = 0;
					}

					//user does not check all right answers
					if (checkedRightAnswerNo < rightAnswerNo) {
						checkedRightAnswerNo = 0;
					}
				}

				questionPts = checkedRightAnswerNo / rightAnswerNo;
				return questionPts;
			};

			this.calculateGroupQuestionMark = function(groupQuestion) {
				if (!groupQuestion || !groupQuestion.listQuestions || groupQuestion.listQuestions === 0) {
					return false;
				}

				var question;
				var questionPts;			//mark of sing question in group
				var groupQuestionPts = 0;	//total mark of group question

				for (var i = 0; i < groupQuestion.listQuestions.length; i++) {
					question = groupQuestion.listQuestions[i];
					questionPts = self.calculateSingleQuestionMark(question);
					// question.questionPts = questionPts;
					groupQuestionPts += questionPts;
				}
				return groupQuestionPts;
			}

			this.calculateExamMark = function(exam) {
				if (exam == null || exam.listQuestions == null || exam.listQuestions.length === 0) {
					return false;
				}
				var CODE = veazyConfig.CODE;
				var skill = exam.skill;
				var totalPts = 0;
				var questionPts;
				var question;

				for (var i = 0; i < exam.listQuestions.length; i++) {
					question = exam.listQuestions[i];
					if (skill === CODE.READING_SKILL) {
						questionPts = self.calculateGroupQuestionMark(question);
					} else {
						questionPts = self.calculateSingleQuestionMark(question);
					}
					// console.log(questionPts)
					// question.questionPts = questionPts;
					// question.questionPts = self.calculateSingleQuestionMark(question);
					totalPts += questionPts;
					// console.log(questionPts, totalPts);
				}

				return (totalPts.toFixed(2)) * 100;
			};

			this.calculateTotalNumberOfQuestion = function(exam) {
				if (exam == null) {
					return false;
				}

				var CODE = veazyConfig.CODE;
				var skill = exam.skill;
				var totalNumberOfQuestions = 0;
				if (skill === CODE.READING_SKILL) {
					var listQuestions = exam.listQuestions;

					for (var i = 0; i < listQuestions.length; i++) {
						var question = listQuestions[i];

						totalNumberOfQuestions += question.listQuestions.length;
					}
				} else {
					totalNumberOfQuestions = exam.listQuestions.length;
				}
				return totalNumberOfQuestions;
			}

			this.trustAsHtml = function(htmlString) {
				return $sce.trustAsHtml(htmlString);
			};
		};
	};

	Helper.$inject = ['$sce', 'veazyConfig'];

	angular.module('veazyServices').service('Helper', Helper);
})();