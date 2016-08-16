;(function() {
	'use strict';
	var addQuestionCtrl = function($scope, $state, veazyConfig, QuestionService, ValidateHelper, ngDialog) {
		$scope.CODE = veazyConfig.CODE;
		$scope.MIN_ANSWER_NUMBER = 2;				//the min number of answers required in a question
		$scope.MIN_QUESTION_NUMBER_IN_GROUP = 2;	//the min number of questions required in a group question

		//values of questionType select box
		$scope.questionTypes = veazyConfig.questionTypes;
		$scope.selectedQuestionType = $scope.questionTypes[0];

		//values of levelType select box
		$scope.levels = veazyConfig.levels;
		$scope.selectedLevel = $scope.levels[0];

		//value of testSkill select box
		$scope.testSkills = veazyConfig.testSkills;
		$scope.selectedTestSkill = $scope.testSkills[0];

		var CODE = $scope.CODE;
		var singleQuestionTypeId = CODE.SINGLE_QUESTION_TYPE;
		var groupQuestionTypeId = CODE.GROUP_QUESTION_TYPE;
		var readingSkillId = CODE.READING_SKILL;

		$scope.$watch('selectedQuestionType', function(newValue, oldValue) {
			var testSkills = $scope.testSkills;

			//if select group question type then skill changes to Reading
			if (newValue.id === groupQuestionTypeId) {
				$scope.selectedTestSkill = testSkills[readingSkillId - 1];
			} else if (newValue.id === singleQuestionTypeId) {
				//if select single type & skill is reading --> change to vocab 
				if ($scope.selectedTestSkill.id === readingSkillId) {
					$scope.selectedTestSkill = testSkills[0];
				} 
				// else {
				// 	$scope.selectedTestSkill = testSkills[$scope.selectedTestSkill.id - 1];	
				// }
			}
		});

		$scope.$watch('selectedTestSkill', function(newValue, oldValue) {
			//if select reading skill then type changes to group
			if (newValue.id === readingSkillId) {
				$scope.selectedQuestionType = $scope.questionTypes[groupQuestionTypeId - 1];
			} else {
				//if select other skills then types change to single
				$scope.selectedQuestionType = $scope.questionTypes[singleQuestionTypeId - 1];
			}
		});

		//time duration picker config
		$scope.vm = {
			options: {
			    widgetPositioning: {
					horizontal: 'left',
					vertical: 'top'
				},
				useCurrent: false,
				format: 'mm:ss',
				defaultDate: moment().startOf('day').seconds(30),
				maxDate: moment().startOf('day').minutes(10),
				minDate: moment().startOf('day').seconds(30)
			}
		};

		function createNewSingleQuestion() {
			$scope.listAnswers = [];
			for (var i = 0; i < $scope.MIN_ANSWER_NUMBER; i++) {
				$scope.listAnswers.push({
					isRight: false
				});
			}
		}
		createNewSingleQuestion();

		function createNewGroupQuestion() {
			$scope.questions = [];
			for (var i = 0; i < $scope.MIN_QUESTION_NUMBER_IN_GROUP; i++) {
				$scope.questions.push({
					listAnswers: []
				});
				for (var j = 0; j < $scope.MIN_ANSWER_NUMBER; j++) {
					$scope.questions[i].listAnswers.push({
						isRight: false
					})
				}
			}
		}

		//when click on check box of answer
		$scope.toggleQuestionType = function(answer) {
			switch ($scope.selectedQuestionType.id) {
				case $scope.CODE.SINGLE_QUESTION_TYPE: {
					createNewSingleQuestion();
					break;
				}
				case $scope.CODE.GROUP_QUESTION_TYPE: {
					createNewGroupQuestion();
					break;
				}
				default: {
					break;
				}
			}
		};

		//add one more answer to a single question or a question in group of questions
		$scope.addAnswer = function(listAnswers) {
			listAnswers.push({});
		};

		//remove the corresponding answer of a single question or a question in group of questions
		$scope.removeAnswer = function(listAnswers, index) {
			listAnswers.splice(index, 1);
		};

		//add one more question to group of questions
		$scope.addQuestion = function(questions) {
			questions.push({
				listAnswers: []
			});

			for (var i = 0; i < $scope.MIN_ANSWER_NUMBER; i++) {
				questions[questions.length - 1].listAnswers.push({});
			}
		};

		$scope.removeQuestion = function(groupQuestion, index) {
			groupQuestion.splice(index, 1);
		};

		//submit
		$scope.submitNewQuestion = function() {
			// var passValidation = true;
			var timepicker = $scope.vm.date;
			var CODE = $scope.CODE;
			var MESSAGE = veazyConfig.MESSAGE;
			var question = {
				questionAnswerType: 1,
				questionType: $scope.selectedQuestionType.id,
				courseId: $scope.selectedLevel.id,
				questionSkill: $scope.selectedTestSkill.id,
				etaTime: parseInt(moment.duration(timepicker.minutes(), 'minute').format('ss')) + timepicker.seconds()
			};

			var dialogConfigObj = {
				template: 'partials/content-editor-restricted/error-alert.html',
				className: 'ngdialog-theme-default error-dialog',
				showClose: false,
				closeByDocument: false,
				disableAnimation: true,
				overlay: false,
				width: 400,
				controller: 'errorDialogCtrl',
				data: {}
			};

			switch ($scope.selectedQuestionType.id) {
				case CODE.SINGLE_QUESTION_TYPE: {

					//bind data
					question.question = $scope.content;
					question.listAnswers = $scope.listAnswers;

					//validate & alert error
					var isNullSingleQuestion = ValidateHelper.isNullSingleQuestion(question);
					var hasNoRightAnswer = ValidateHelper.hasNoRightAnswer(question);

					if (isNullSingleQuestion) {
						dialogConfigObj.data.errorMsg = 'MISSING_QUESTION_FIELD_MSG';
						ngDialog.open(dialogConfigObj);
						return;
					} else {
						if (hasNoRightAnswer) {
							dialogConfigObj.data.errorMsg = 'REQUIRED_ANSWERS_MSG'
							ngDialog.open(dialogConfigObj);
							return;
						};
					}
					break;
				}
				case CODE.GROUP_QUESTION_TYPE: {

					//bind data
					question.question = $scope.sharedContent;
					question.listQuestions = $scope.questions;
					question.listQuestions.forEach(function(ques) {
						ques.questionAnswerType = 1;
						ques.questionType = $scope.CODE.SINGLE_QUESTION_TYPE;
						ques.questionSkill = $scope.selectedTestSkill.id;
						ques.courseId = $scope.selectedLevel.id
					});

					//validate & alert error
					var isNullGroupQuestion = ValidateHelper.isNullGroupQuestion(question);
					var hasNoRightAnswerInGroup = ValidateHelper.hasNoRightAnswerInGroup(question);

					if (isNullGroupQuestion) {
						dialogConfigObj.data.errorMsg = 'MISSING_QUESTION_FIELD_MSG';
						ngDialog.open(dialogConfigObj);
						return;
					} else {
						if (hasNoRightAnswerInGroup) {
							dialogConfigObj.data.errorMsg = 'REQUIRED_ANSWERS_MSG'
							ngDialog.open(dialogConfigObj);
							return;
						}
					}
					break;
				}
			}
			// console.log(question);
			QuestionService.create(question).then(function(response) {
				console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						$state.go('editordashboard.question.list');
						break;
					}
					case CODE.UNAUTHORIZED: {
						$state.go('login');
						break;
					}
					default: {
						$scope.errorMsg = MESSAGE[response.code];
						console.log($scope.errorMsg)
					}
				}
			}, function(reject) {
				$scope.errorMsg = reject;
			});
		};

		$scope.froalaOptions = {
		    // placeholderText: 'Edit Your Content Here!'
		    imageUploadURL: '/Veazy/uploadfile'
		};
	};

	addQuestionCtrl.$inject = ['$scope', '$state', 'veazyConfig', 'QuestionService', 'ValidateHelper', 'ngDialog'];

	angular.module('veazyControllers').controller('addQuestionCtrl', addQuestionCtrl);
})();