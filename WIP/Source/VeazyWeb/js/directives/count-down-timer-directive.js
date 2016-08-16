;(function() {
	var veazyDirectives = angular.module('veazyDirectives');

	var countDownTimerDirective = function() {
		return {
			restrict: 'A',
			// template: '<h2 id="hihi" style="position: absolute; right: 0; top: 0;">{{duration}}</h2>',
			link: function(scope, element, attrs) {
				$(element).css('position', 'absolute');
				$(element).css('top', '0');
				$(element).css('right', '10px');

				//document scroll top
				var lastScrollTop = 0;

				//element scroll top
				var elemTopPx = $(element).css('top');
				var elemTop = parseInt(elemTopPx.replace(/[^-\d\.]/g, ''));

				$(document).scroll(function() {
					var scrollTop = $(this).scrollTop();
					var scrollDistance = Math.abs(scrollTop - lastScrollTop);
					
					//diff scroll distance
					if (scrollTop > lastScrollTop) {
						elemTop += scrollDistance;
					} else {
						elemTop -= scrollDistance;
					}

					//readjust top position of element
					$(element).css({
						top: elemTop + 'px'
					});
					lastScrollTop = scrollTop;
				});

				//destroy even when leaving
				scope.$on('$destroy', function() {
					$(document).unbind('scroll');
				});
			}
		}
	};

	veazyDirectives.directive('countDownTimer', countDownTimerDirective);
})();