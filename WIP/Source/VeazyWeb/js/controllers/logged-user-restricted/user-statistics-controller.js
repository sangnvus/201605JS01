;(function() {
	'use strict';
	var userStatisticsCtrl = function($scope, $filter, veazyConfig, ChartService) {
		var CODE = veazyConfig.CODE;
		
		//input of skill chart
		$scope.skillLabels = [
			$filter('translate')('VOCABULARY_SKILL'),
			$filter('translate')('GRAMMAR_SKILL'),
			$filter('translate')('LISTENING_SKILL'),
			$filter('translate')('READING_SKILL'),
			$filter('translate')('WRITING_SKILL')
		];
		$scope.skillData = [];

		//input of test marks
		$scope.testGradeLabels = [];
		$scope.testGradeData = [];

		//input of level chart
		$scope.levelLabels = [
			$filter('translate')('BEGINNER_LEVEL'), 
			$filter('translate')('UPPER_BEGINNER_LEVEL'),
			$filter('translate')('INTERMEDIATE_LEVEL'),
			$filter('translate')('UPPER_INTERMEDIATE_LEVEL'),
			$filter('translate')('ADVANCED_LEVEL'),
			$filter('translate')('MASTER_LEVEL')
		];
		$scope.levelData = [];

		//get stats of skills
		ChartService.getSkillStats().then(function(response) {
			switch (response.code) {
				case CODE.SUCCESS: {
					var data = response.data;
					$scope.skillData[0] = [data.vocabulary, data.grammar, data.listening, data.reading, 0];
					break;
				}

				default: {
					//handling other case
				}
			}
		});

		//get marks of 10 recently exams
		ChartService.getExamMarkStats(10).then(function(response) {
			switch (response.code) {
				case CODE.SUCCESS: {
					var data = response.data;
					var temp;
					data.dates.forEach(function(date) {
						temp = $filter('dateFormat')(date);
						$scope.testGradeLabels.push(temp);
					});
					$scope.testGradeData = [data.results];
				}

				default: {
					//handling other case
				}
			}
		});

		//get marks of test based on level
		ChartService.getLevelStats().then(function(response) {
			switch (response.code) {
				case CODE.SUCCESS: {
					$scope.levelData[0] = response.data;
					break;
				}

				default: {
					//handling other case
				}
			}
		});
	};

	userStatisticsCtrl.$inject = ['$scope', '$filter', 'veazyConfig', 'ChartService'];
	angular.module('veazyControllers').controller('userStatisticsCtrl', userStatisticsCtrl);
})();