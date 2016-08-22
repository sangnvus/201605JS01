;(function() {
	editQuestionCtrl = function($scope, $state, veazyConfig, filterFilter, ngDialog, getQuestion, QuestionService, 
		ValidateHelper, ngDialog) {
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

		// console.log(moment().startOf('day').seconds($scope.question.etaTime).minutes())

		$scope.vm = {
			options: {
			    widgetPositioning: {
					horizontal: 'left',
					vertical: 'top'
				},
				useCurrent: false,
				format: 'mm:ss',
				// defaultDate: moment().minutes(0).seconds(30)
				defaultDate: moment().startOf('day').seconds($scope.question.etaTime),
				minDate: moment().startOf('day').seconds(30),
				maxDate: moment().startOf('day').minutes(10)
			}
		};

		//add one more answer to a single question or a question in group of questions
		$scope.addAnswer = function(answers) {
			// var answers = question.answers;
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
			// var questions = groupQuestion.questions;
			questions.push({
				listAnswers: []
			});

			for (var i = 0; i < $scope.MIN_ANSWER_NUMBER; i++) {
				questions[questions.length - 1].listAnswers.push({});
			}
		};

		$scope.removeQuestion = function(groupQuestion, index) {
			// console.log(groupQuestion)
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
				questionSkill: $scope.selectedTestSkill.id
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

			var timepicker = $scope.vm.date;
			var etaTime = parseInt(moment.duration(timepicker.minutes(), 'minute').format('ss')) + timepicker.seconds();
			question.etaTime = etaTime;

			switch ($scope.selectedQuestionType.id) {
				case CODE.SINGLE_QUESTION_TYPE: {
					question.question = $scope.question.question;
					question.listAnswers = $scope.question.listAnswers;

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
					question.question = $scope.sharedContent;
					question.listQuestions = $scope.question.listQuestions;

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
				}
			}

			console.log(question);
			QuestionService.update(question).then(function(response) {
				console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						$state.go('editordashboard.question.detail', {
							questionId: question.questionId
						});
						break;
					}
					case CODE.UNAUTHORIZED: {
						$state.go('login');
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

	editQuestionCtrl.$inject = ['$scope', '$state', 'veazyConfig', 'filterFilter', 'ngDialog', 'getQuestion', 
	'QuestionService', 'ValidateHelper', 'ngDialog'];

	angular.module('veazyControllers').controller('editQuestionCtrl', editQuestionCtrl);
})();