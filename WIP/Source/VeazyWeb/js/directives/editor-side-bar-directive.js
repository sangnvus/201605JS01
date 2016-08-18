;(function() {
	'use strict';
	angular.module('veazyDirectives').directive('editorSideBar', [function() {
		return {
			restrict: 'E',
			templateUrl: 'partials/content-editor-restricted/editor-side-bar.html',
			link: function(scope, element, attrs) {
				$(".menu-toggle").click(function(e) {
					e.preventDefault();
					$(".wrapper").toggleClass("toggled");
				});

				$('.sidebar-nav').find('ul.child-menu').hide();

				$('.side-bar-tab').find('a').on('click', function(ev) {
					var $li = $(this).parent();

					if ($li.is('.active')) {
						$li.removeClass('active active-sm');
						$('ul:first', $li).slideUp();
					} else {
						// prevent closing menu if we are on child menu
						if (!$li.parent().is('.child-menu')) {
							$('.sidebar-nav').find('li').removeClass('active active-sm');
							$('.sidebar-nav').find('li ul').slideUp();
						}
						$li.addClass('active');

						$('ul:first', $li).slideDown();
					}	
				});

				//scroll customization
				if ($.fn.mCustomScrollbar) {
					$('.sidebar-wrapper').mCustomScrollbar({
						autoHideScrollbar: true,
						theme: 'minimal',
						mouseWheel: {
							preventDefault: true 
						}
					});
				}
			},
			controller: 'editorSideBarCtrl'
		};
	}]);
})();