;(function() {
	'use strict';
	var lessonDetailCtrl = function($rootScope, $scope, $location, $anchorScroll, ngDialog, veazyConfig) {
		$scope.lesson = {
			title: '第１課　アルファベット',
			createdDate: '２０１６年６月１４日',
			author: 'Crimson_Death',
			course: '初級',
			content: {
				vocab: '<p>A cluster balloonist who became the first person to fly the English Channel has Intrepid Jonathan Trappe, 38, took off just like the 78-year-old character Carl Frederickson in the hit movie.</p>',
				grammar: '<p>The present continuous is used to talk about present situations which we see as short-term or temporary. We use the present simple to talk about present situations which we see as long-term or permanent.</p><p>In these examples, the action is taking place at the time of speaking.</p><ul><li>It\'s raining.</li><li>Who is Kate talking to on the phone?</li><li>Look, somebody is trying to steal that man\'s wallet.</li><li>I\'m not looking. My eyes are closed tightly.</li><li>In these examples, the action is true at the present time but we don\'t think it will be true in the long term.</p></li></ul><ul><li>I\'m looking for a new apartment.</li><li>He\'s thinking about leaving his job.</li><li>They\'re considering making an appeal against the judgment.</li><li>Are you getting enough sleep?</li><li>In these examples, the action is at a definite point in the future and it has already been arranged.</li></ul><ul><li>I\'m meeting her at 6.30.</li><li>They aren\'t arriving until Tuesday.</li><li>We are having a special dinner at a top restaurant for all the senior managers.</li><li>Isn\'t he coming to the dinner?</li></ul>',
				conversation: '<p>Person A: "Hi Jack.  What are you doing?"</p><p>Person B: "Hi Mary. I\'m filling out a job application."</p><p>Person A: "Are you finished with school already?"</p><p>Person B: "No.  I have one more semester, but it would be great to have a job lined up."</p><p>Person A: "How is your day going?"</p><p>Person B: "Quite busy.  I\'m preparing for my presentation tomorrow on our marketing strategy.  I\'m not even half done yet."</p><p>Person A: "You must feel stressed out now."</p><p>Person B: "That\'s an understatement."</p><p>Person A: "What are you doing now?"</p><p>Person B: "I\'m playing pool with my friends at a pool hall."</p><p>Person A: "I didn\'t know you play pool.  Are you having fun?"</p><p>Person B:  "I\'m having a great time.  How about you?  What are you doing?"</p><p>Person A: "I\'m taking a break from my homework.  There seems to be no end to the amount of work I have to do."</p><p>Person B: "I\'m glad I\'m not in your shoes."</p>',
				listening: '',
				practice: ''
			}
		};

		$scope.lessonSections = veazyConfig.lessonSections;

		$scope.showCourseListPage = function() {
			$location.path('/courselist');
		};

		$scope.searchLesson = function() {
			$rootScope.lesson = {
				id: 1,
				name: 'Alphabet'
			};

			ngDialog.open({
				template: 'partials/search-lesson-dialog.html',
				className: 'ngdialog-theme-default search-lesson-dialog',
				// plain: true,
				// className: 'search-lesson-dialog',
				showClose: true,
				closeByDocument: false,
				width: 700,
				controller: 'searchLessonCtrl',
				data: {
					id: 1,
					name: 'Alphabet'
				}
			});
		};

		// $scope.scrollTo = function(id) {
		// 	$location.hash(id);
		// 	$anchorScroll();
		// 	console.log('here');
		// 	$scope.setTab(3);
		// };
	};

	lessonDetailCtrl.$inject = ['$rootScope', '$scope', '$location', '$anchorScroll', 'ngDialog', 'veazyConfig'];

	angular.module('veazyControllers').controller('lessonDetailCtrl', lessonDetailCtrl);
})();