;(function() {
	'use strict';
	var addQuestionCtrl = function($scope, $state, veazyConfig, QuestionService, ValidateHelper, ngDialog, FileUploader) {
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
		var MESSAGE = veazyConfig.MESSAGE;
		var singleQuestionTypeId = CODE.SINGLE_QUESTION_TYPE;
		var groupQuestionTypeId = CODE.GROUP_QUESTION_TYPE;
		var readingSkillId = CODE.READING_SKILL;

		//watch value of question Type select box
		$scope.$watch('selectedQuestionType', function(newValue, oldValue) {
			var testSkills = $scope.testSkills;

			//if select group question type then skill changes to Reading
			if (newValue.id === groupQuestionTypeId) {
				$scope.selectedTestSkill = testSkills[readingSkillId - 1];
				createNewGroupQuestion();
			} else if (newValue.id === singleQuestionTypeId) {
				//if select single type & skill is reading --> change to vocab 
				if ($scope.selectedTestSkill.id === readingSkillId) {
					$scope.selectedTestSkill = testSkills[0];
				}
				createNewSingleQuestion();
			}
		});

		//watch value of skill select box
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

		//editor config
		$scope.froalaOptions = {
		    // placeholderText: 'Edit Your Content Here!'
		    imageUploadURL: '/Veazy/uploadfile'
		};

		//file uploader config
		$scope.uploader = new FileUploader({
			url: '/Veazy/uploadfile',
			autoUpload: true,
			withCredentials: true,
			filters: [{						//filter file upload, only allow mp3
				name: 'audio',
				fn: function(item) {
					if (item.type === 'audio/mp3') {
						$scope.uploadErrorMsg = null;
						return true;
					} else {
						$scope.uploadErrorMsg = 'NOT_VALID_AUDIO_FILE_ERROR_MSG';
					}
				}
			}]
		});

		//bind uploaded file url to question's attachment
		$scope.uploader.onSuccessItem = function(item, response) {
			$scope.attachment = response.link;
		};

		function createNewSingleQuestion() {
			$scope.listAnswers = [];
			$scope.errorMsg = null;
			for (var i = 0; i < $scope.MIN_ANSWER_NUMBER; i++) {
				$scope.listAnswers.push({
					isRight: false
				});
			}
		}
		createNewSingleQuestion();

		function createNewGroupQuestion() {
			$scope.questions = [];
			$scope.errorMsg = null;
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
		// $scope.toggleQuestionType = function(answer) {
		// 	switch ($scope.selectedQuestionType.id) {
		// 		case $scope.CODE.SINGLE_QUESTION_TYPE: {
		// 			createNewSingleQuestion();
		// 			break;
		// 		}
		// 		case $scope.CODE.GROUP_QUESTION_TYPE: {
		// 			createNewGroupQuestion();
		// 			break;
		// 		}
		// 		default: {
		// 			break;
		// 		}
		// 	}
		// };

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

		//remove a question from group
		$scope.removeQuestion = function(groupQuestion, index) {
			groupQuestion.splice(index, 1);
		};

		//submit
		$scope.submitNewQuestion = function() {
			var timepicker = $scope.vm.date;

			//bind data
			var question = {
				questionAnswerType: 1,
				questionType: $scope.selectedQuestionType.id,
				courseId: $scope.selectedLevel.id,
				questionSkill: $scope.selectedTestSkill.id,
				etaTime: parseInt(moment.duration(timepicker.minutes(), 'minute').format('ss')) + timepicker.seconds()
			};

			if ($scope.selectedTestSkill.id === CODE.LISTENING_SKILL) {
				question.attachment = $scope.attachment;
			}

			switch ($scope.selectedQuestionType.id) {
				case CODE.SINGLE_QUESTION_TYPE: {

					//bind data
					question.question = $scope.content;
					question.listAnswers = $scope.listAnswers;

					//validate & alert error
					var isNullSingleQuestion = ValidateHelper.isNullSingleQuestion(question);
					var hasNoRightAnswer = ValidateHelper.hasNoRightAnswer(question);

					if (isNullSingleQuestion) {
						$scope.errorMsg = 'MISSING_QUESTION_FIELD_MSG';
						return;
					} else {
						if (hasNoRightAnswer) {
							$scope.errorMsg = 'REQUIRED_ANSWERS_MSG';
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
						$scope.errorMsg = 'MISSING_QUESTION_FIELD_MSG';
						return;
					} else {
						if (hasNoRightAnswerInGroup) {
							$scope.errorMsg = 'REQUIRED_ANSWERS_MSG';
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
						$state.go('editor.question.list');
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
	};

	addQuestionCtrl.$inject = ['$scope', '$state', 'veazyConfig', 'QuestionService', 'ValidateHelper', 'ngDialog', 'FileUploader'];

	angular.module('veazyControllers').controller('addQuestionCtrl', addQuestionCtrl);
})();