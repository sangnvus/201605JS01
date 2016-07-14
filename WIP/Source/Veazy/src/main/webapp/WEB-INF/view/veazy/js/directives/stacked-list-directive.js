;(function() {
	angular.module('veazyDirectives').directive('stackedList', ['$timeout', function($timeout) {
		return {
			restrict: 'A',
			link: function(scope, element, attrs) {
				$timeout(function() {
					$(element).find('ul').hide();

					$(element).find('li a').on('click', function() {
						$(element).find('li').removeClass('active');
						$(this).closest('li').addClass('active');

						var checkElement = $(this).next();
						if (checkElement.is('ul')) {
							if (checkElement.is(':visible')) {
								checkElement.slideUp();
							} else {
								checkElement.slideDown();
							}
						}
					});
				}, 0);
			}
		};
	}]);
})();