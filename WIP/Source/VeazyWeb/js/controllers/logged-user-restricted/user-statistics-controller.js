;(function() {
	'use strict';
	var userStatisticsCtrl = function($scope, $filter, veazyConfig, ChartService) {
		var CODE = veazyConfig.CODE;
		// var currentLang = $translate.use;

		// if (currentLang === 'en') {
		// 	$scope.levelLabels = 
		// }

		// $scope.skillData = [
		// 	[65, 59, 90, 81, 0]
		// ];

		// $scope.testGradeLabels = ['Test 1', 'Test 2', 'Test3', 'Test 4', 'Test 5', 'Test 6', 'Test 7', 'Test 8', 'Test 9', 'Test 10'];
		// $scope.testGradeData = [
		// 	[65, 59, 48, 81, 56, 55, 40, 78, 100, 57]
		// ];
		// console.log($translate('BEGINNER_LEVEL'))
		
		$scope.levelLabels = [
			$filter('translate')('BEGINNER_LEVEL'), 
			$filter('translate')('UPPER_BEGINNER_LEVEL'),
			$filter('translate')('INTERMEDIATE_LEVEL'),
			$filter('translate')('UPPER_INTERMEDIATE_LEVEL'),
			$filter('translate')('ADVANCED_LEVEL'),
			$filter('translate')('MASTER_LEVEL')
		];

		$scope.skillLabels = [
			$filter('translate')('VOCABULARY_SKILL'),
			$filter('translate')('GRAMMAR_SKILL'),
			$filter('translate')('LISTENING_SKILL'),
			$filter('translate')('READING_SKILL'),
			$filter('translate')('WRITING_SKILL')
		]

		//get stats of skills
		ChartService.getSkillStats().then(function(response) {
			// console.log(response);
			switch (response.code) {
				case CODE.SUCCESS: {
					var data = response.data;
					$scope.skillLabels =['Vocabulary', 'Grammar', 'Listening', 'Reading', 'Writing'];
					$scope.skillData = [
						[data.vocabulary, data.grammar, data.listening, data.reading, 0]
					];

					//get marks of 10 recently exams
					// ChartService.getExamMarkStats(10).then(function(response) {
					// 	// console.log(response);
					// 	switch (response.code) {
					// 		case CODE.SUCCESS: {
					// 			var responseData = response.data;

					// 			$scope.testGradeLabels = [];
					// 			$scope.testGradeData = [[]];

					// 			$scope.testGradeLabels = [];
					// 			for (var i = 0; i < responseData.length; i++) {
					// 				// $scope.testGradeLabels.push('Test' + (i + 1));
					// 				$scope.testGradeLabels.push('Test' + (i + 1));
					// 				$scope.testGradeData[0].push(responseData[i].result);
					// 			}

					// 			// console.log($scope.testGradeData);
					// 		}

					// 		default: {
					// 			//handling other case
					// 		}
					// 	}
					// });
					break;
				}

				default: {
					//handling other case
				}
			}
		});

		ChartService.getExamMarkStats(10).then(function(response) {
			// console.log(response);
			switch (response.code) {
				case CODE.SUCCESS: {
					var responseData = response.data;

					$scope.testGradeLabels = [];
					$scope.testGradeData = [[]];

					$scope.testGradeLabels = [];
					for (var i = 0; i < responseData.length; i++) {
						// $scope.testGradeLabels.push('Test' + (i + 1));
						$scope.testGradeLabels.push('Test' + (i + 1));
						$scope.testGradeData[0].push(responseData[i].result);
					}

					// console.log($scope.testGradeData);
				}

				default: {
					//handling other case
				}
			}
		});

		//get marks of test based on level
		ChartService.getLevelStats().then(function(response) {
			console.log(response);
			switch (response.code) {
				case CODE.SUCCESS: {
					// var data = response.data;
					// var length = response.data.length;
					var responseData = response.data;
					$scope.levelData = [[]];

					// $scope.testGradeLabels = [];
					for (var i = 0; i < responseData.length; i++) {
						// $scope.testGradeLabels.push('Test' + (i + 1));
						// $scope.testGradeLabels.push('Test' + (i + 1));
						$scope.levelData[0].push(responseData[i].avgResult);
					}

					// console.log($scope.levelData);
					break;

					// $scope.skillData = [response.data];
				}

				default: {
					//handling other case
				}
			}
		}, function(reject) {

		});
	};

	userStatisticsCtrl.$inject = ['$scope', '$filter', 'veazyConfig', 'ChartService'];
	angular.module('veazyControllers').controller('userStatisticsCtrl', userStatisticsCtrl);
})();