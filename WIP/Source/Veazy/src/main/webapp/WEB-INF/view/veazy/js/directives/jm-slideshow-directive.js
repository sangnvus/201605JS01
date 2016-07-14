;(function() {
	'use strict';
	angular.module('veazyDirectives').directive('jmSlideshow', [function() {
		return {
			restrict: 'A',
			link: function(scope, element, attrs) {
				var jmpressOpts = {
					animation: {	
						transitionDuration : '1s' 
					}
				};

				var sliderOpts = {
					autoplay: true,
					bgColorSpeed: '1s',
					arrows: false
				};

				$(element).jmslideshow($.extend(true, {
					jmpressOpts : jmpressOpts 
				}, sliderOpts));

				scope.$on('$destroy', function() {
					$(element).find('.jms-wrapper').trigger('touchend.jmslideshow');
				});
			}
		};
	}]);
})();