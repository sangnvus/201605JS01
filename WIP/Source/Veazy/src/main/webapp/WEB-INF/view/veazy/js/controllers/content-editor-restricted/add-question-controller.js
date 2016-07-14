;(function() {
	'use strict';
	var addQuestionCtrl = function($scope, veazyConfig) {
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

		//options for shared content text editor
		$scope.sharedContentEditorOptions = {
			placeholderText: 'Here comes the shared content (e.g, reading essay)',
			toolbarButtons: ['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', 'fontFamily', 'fontSize', 'color', 'emoticons', 'inlineStyle', 'paragraphStyle', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', 'insertHR', '-', 'insertImage', 'insertTable', 'undo', 'redo', 'clearFormatting', 'selectAll']
		};


		function createNewSingleQuestion() {
			$scope.newQuestion = {
				answers: []
			};

			//add answers to question
			for (var i = 0; i < $scope.MIN_ANSWER_NUMBER; i++) {
				$scope.newQuestion.answers.push({});
			}
			console.log($scope.newQuestion);
		}
		createNewSingleQuestion();		//default is single question

		function createNewGroupQuestion() {
			$scope.newQuestion = {
				questions: []
			};

			//add questions to group
			var questions = $scope.newQuestion.questions;
			for (var i = 0; i < $scope.MIN_QUESTION_NUMBER_IN_GROUP; i++) {
				questions.push({
					answers: []
				});

				//add answers to question in group
				for (var j = 0; j < $scope.MIN_ANSWER_NUMBER; j++) {
					questions[i].answers.push({});
				}
			}
			console.log($scope.newQuestion);
		}

		$scope.toggleQuestionType = function() {
			var types = $scope.questionTypes;
			var skills = $scope.testSkills;
			switch ($scope.selectedQuestionType.id) {
				//user selects single question type
				case types[0].id: {
					createNewSingleQuestion();
					break;
				}
				//user selects group question type
				case types[types.length - 1].id: {
					createNewGroupQuestion();
					$scope.selectedTestSkill = skills[skills.length - 1];	//group question type allows only reading skill
					break;
				}
				default: {

				}
			}
		};

		//when click on check box of answer
		$scope.toggleCorrectAnswer = function(answer) {
			console.log(answer);
		};

		//add one more answer to a single question or a question in group of questions
		$scope.addAnswer = function(question) {
			var answers = question.answers;
			answers.push({});
			console.log(question);
		};

		//remove the corresponding answer of a single question or a question in group of questions
		$scope.removeAnswer = function(question, index) {
			var answers = question.answers;
			answers.splice(index, 1);
			console.log(question);
		};

		//add one more question to group of questions
		$scope.addQuestion = function(groupQuestion) {
			var questions = groupQuestion.questions;
			questions.push({
				answers: []
			});

			for (var i = 0; i < $scope.MIN_ANSWER_NUMBER; i++) {
				questions[questions.length - 1].answers.push({});
			}
		};

		$scope.removeQuestion = function(groupQuestion, index) {
			var questions = groupQuestion.questions;
			questions.splice(index, 1);
			console.log(groupQuestion);
		}

		//submit
		$scope.submitNewQuestion = function() {
			console.log($scope.newQuestion);
		};

		$scope.froalaOptions = {
		    placeholderText: 'Edit Your Content Here!'
		}
	};

	addQuestionCtrl.$inject = ['$scope', 'veazyConfig'];

	angular.module('veazyControllers').controller('addQuestionCtrl', addQuestionCtrl);
})();