;(function() {
	'use strict';
	angular.module('veazyDirectives').directive('scrollReveal', ['$timeout', '$window', function($timeout, $window) {
		return {
			restrict: 'A',
			link: function(scope, element, attrs) {
				$timeout(function() {
					$window.sr = ScrollReveal();

					$window.sr.reveal('.sr-icon', {
						duration: 600,
						scale: 0.3,
						distance: '0px'
					}, 200);
					$window.sr.reveal('.sr-button', {
						duration: 1000,
						delay: 200
					});
					$window.sr.reveal('.sr-contact', {
						duration: 600,
						scale: 0.3,
						distance: '0px'
					}, 300);
				}, 0);
			}
		}
	}]);
})();