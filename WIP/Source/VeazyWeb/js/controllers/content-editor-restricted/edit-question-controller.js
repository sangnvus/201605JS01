;(function() {
	editQuestionCtrl = function($scope, $state, veazyConfig, FileUploader, getQuestion, QuestionService, Validator) {
		$scope.CODE = veazyConfig.CODE;
		$scope.MIN_ANSWER_NUMBER = 2;				//the min number of answers required in a question
		$scope.MIN_QUESTION_NUMBER_IN_GROUP = 2;	//the min number of questions required in a group question

		$scope.question = getQuestion.data;
		$scope.sharedContent = getQuestion.data.question;
		//values of questionType select box
		$scope.questionTypes = veazyConfig.questionTypes;
		for (var i = 0; i < $scope.questionTypes.length; i++) {
			var type = $scope.questionTypes[i];
			if ($scope.question.questionType === type.id) {
				$scope.selectedQuestionType = type;
				break;
			}
		};

		//values of levelType select box
		$scope.levels = veazyConfig.levels;
		for (var i = 0; i < $scope.levels.length; i++) {
			var level = $scope.levels[i];
			if ($scope.question.courseId === level.id) {
				$scope.selectedLevel = level;
				break;
			}
		};

		//value of testSkill select box
		$scope.testSkills = veazyConfig.testSkills;
		for (var i = 0; i < $scope.testSkills.length; i++) {
			var skill = $scope.testSkills[i];
			if ($scope.question.questionSkill === skill.id) {
				$scope.selectedTestSkill = skill;
				break;
			}
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
			$scope.question.attachment = response.link;
		};

		$scope.vm = {
			options: {
			    widgetPositioning: {
					horizontal: 'left',
					vertical: 'top'
				},
				useCurrent: false,
				format: 'mm:ss',
				defaultDate: moment().startOf('day').seconds($scope.question.etaTime),
				minDate: moment().startOf('day').seconds(30),
				maxDate: moment().startOf('day').minutes(10)
			}
		};

		//add one more answer to a single question or a question in group of questions
		$scope.addAnswer = function(answers) {
			answers.push({});
		};

		//remove the corresponding answer of a single question or a question in group of questions
		$scope.removeAnswer = function(answers, index) {
			// var answers = question.answers;
			answers.splice(index, 1);
			console.log(answers);
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
			var questions = groupQuestion.listQuestions;
			questions.splice(index, 1);
			console.log(groupQuestion);
		};

		$scope.updateQuestion = function() {
			var CODE = $scope.CODE;
			var question = {
				questionId: $scope.question.questionId,
				questionAnswerType: 1,
				questionType: $scope.selectedQuestionType.id,
				courseId: $scope.selectedLevel.id,
				questionSkill: $scope.selectedTestSkill.id,
				attachment: $scope.question.attachment
			};

			var timepicker = $scope.vm.date;
			var etaTime = parseInt(moment.duration(timepicker.minutes(), 'minute').format('ss')) + timepicker.seconds();
			question.etaTime = etaTime;

			switch ($scope.selectedQuestionType.id) {
				case CODE.SINGLE_QUESTION_TYPE: {
					question.question = $scope.question.question;
					question.listAnswers = $scope.question.listAnswers;

					//validate & alert error
					var isNullSingleQuestion = Validator.isNullSingleQuestion(question);
					var hasNoRightAnswer = Validator.hasNoRightAnswer(question);

					if (isNullSingleQuestion) {
						$scope.errorMsg = 'MISSING_QUESTION_FIELD_MSG';
						// ngDialog.open(dialogConfigObj);
						return;
					} else {
						if (hasNoRightAnswer) {
							$scope.errorMsg = 'REQUIRED_ANSWERS_MSG'
							// ngDialog.open(dialogConfigObj);
							return;
						};
					}

					// if ($scope.selectedTestSkill.id === CODE.LISTENING_SKILL) {
					// 	// question.attachment = $scope.attachment;
					// 	if (question.attachment == null) {
					// 		$scope.uploadErrorMsg = 'NO_AUDIO_FILE';
					// 		return;
					// 	}
					// }
					// break;
					break;
				}
				case CODE.GROUP_QUESTION_TYPE: {
					question.question = $scope.sharedContent;
					question.listQuestions = $scope.question.listQuestions;

					//validate & alert error
					var isNullGroupQuestion = Validator.isNullGroupQuestion(question);
					var hasNoRightAnswerInGroup = Validator.hasNoRightAnswerInGroup(question);

					if (isNullGroupQuestion) {
						$scope.errorMsg = 'MISSING_QUESTION_FIELD_MSG';
						// ngDialog.open(dialogConfigObj);
						return;
					} else {
						if (hasNoRightAnswerInGroup) {
							$scope.errorMsg = 'REQUIRED_ANSWERS_MSG'
							// ngDialog.open(dialogConfigObj);
							return;
						}
					}
				}
			}

			console.log(question);
			QuestionService.update(question).then(function(response) {
				console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						$state.go('editor.question.detail', {
							questionId: question.questionId
						});
						break;
					}
					case CODE.UNAUTHORIZED: {
						$state.go('login');
						break;
					}

					case CODE.NO_PERMISSION: {
						$state.go('forbidden');
						break;
					}
				}
			}, function(reject) {

			});
		};

		$scope.froalaOptions = {
		    // placeholderText: 'Edit Your Content Here!'
		    imageUploadURL: '/Veazy/uploadfile'
		};
	};

	editQuestionCtrl.$inject = ['$scope', '$state', 'veazyConfig', 'FileUploader', 'getQuestion', 'QuestionService', 'Validator'];

	angular.module('veazyControllers').controller('editQuestionCtrl', editQuestionCtrl);
})();