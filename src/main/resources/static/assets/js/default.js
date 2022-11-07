/* default */
var $winW = $(window).width();
var $winH = $(window).height();
var scrollTop = $(window).scrollTop();
var _headerTop = $('.w_header').innerHeight();
/* 20210713 search_top 삭제 */
var _headerHeight = $('#header').innerHeight();
var _footerHeight = $('#footer').innerHeight();
var _mheaderHeight = $('.m_header').innerHeight();
var _fixHeaderY = _headerTop;
var isFix = false;
if (scrollTop > _fixHeaderY + 100) {
	isFix = true;
	$("#wrap").addClass('fixed');
}

//헤더서브스크롤고정
$(function () {
	var rollHeader = 100;
	$(window).scroll(function () {
		var scroll = getCurrentScroll();
		if (scroll >= rollHeader) {
			$('.header_sub .inner').addClass('roll');
		} else {
			$('.header_sub .inner').removeClass('roll');
		}
	});

	function getCurrentScroll() {
		return window.pageYOffset || document.documentElement.scrollTop;
	}
});

//모달레이어팝업
function lock_touch(e) {
	e.preventDefault();
}

function modalPopup(target) {
	$('.modal-content').css("marginTop", 0);
	var $modalContent = $(target).find($('.modal-content'));
	$(target).css({
		'overflow': 'auto'
	}).show().addClass('open');
	$(target).focus();
	var $modalContentH = $(target).find($('.modal-content')).outerHeight();
	var $conPos = ($winH / 2) - ($modalContentH / 2);
	$('html').css({
		'overflow': 'hidden',
		'height': $winH
	});
	if ($winH > $modalContentH) {
		$modalContent.css({
			marginTop: $conPos
		});
	} else {
		$modalContent.css({
			marginTop: 0
		});
	}
	$("<div class='overlay'></div>").appendTo('#wrap');
	return false;
};

//모달레이어팝업닫기
function modalPopupClose(target) {
	//document.removeEventListener('touchmove', lock_touch);
	if (document.removeEventListener) {
		document.removeEventListener('touchmove', lock_touch);
	} else {
		document.detachEvent('touchmove', lock_touch);
	}
	$(target).find($('.modal-content')).css('margin-top', 0);
	$(target).hide().removeClass('open');
	$(".overlay").remove();
	$('html').css({
		'overflow': 'auto',
		'height': 'auto'
	});
};

//하단팝업
function lock_touch(e) {
	e.preventDefault();
}

function botPop(target) {
	$(target).addClass('open');
	$(target).focus();
	$('html').css({
		'overflow': 'hidden',
		'height': $winH
	});
	$("<div class='overlay'></div>").appendTo('#wrap');
	if ($(target).hasClass('open')) {
		$('.overlay').on('click', function () {
			$(target).hide().removeClass('open');
			$(".overlay").remove();
			$('html').css({
				'overflow': 'auto',
				'height': 'auto'
			});
		})
	};
	return false;
};

//하단팝업닫기
function botPopClose(target) {
	//document.removeEventListener('touchmove', lock_touch);
	if (document.removeEventListener) {
		document.removeEventListener('touchmove', lock_touch);
	} else {
		document.detachEvent('touchmove', lock_touch);
	}
	$(target).hide().removeClass('open');
	$(".overlay").remove();
	$('html').css({
		'overflow': 'auto',
		'height': 'auto'
	});
};

// 하단팝업2
$(function () {
	$('.bottomBtn_area .bottomBtn').on('click', function () {
		$('.bottomPop').toggleClass('open');
		if ($('.bottomPop').hasClass('open')) {
			$("<div class='overlay'></div>").appendTo('#wrap');
			$('html').css({
				'overflow': 'hidden',
				'height': $winH
			});
			$('.overlay').on('click', function () {
				$('.bottomPop').removeClass('open');
				$(".overlay").remove();
				$('html').css({
					'overflow': 'auto',
					'height': 'auto'
				});
			})
		} else {
			$(".overlay").remove();
			$('html').css({
				'overflow': 'auto',
				'height': 'auto'
			});
		};

	});
	$('.popCloseBtn').on('click', function () {
		$('.bottomPop').removeClass('open');
		$(".overlay").remove();
		$('html').css({
			'overflow': 'auto',
			'height': 'auto'
		});
	})
})

//*맞춤상품비교 고정*//
$(document).ready(function () {
	var jbOffset = $('.loan_column').offset();
	$(window).scroll(function () {
		if ($(document).scrollTop() > jbOffset.top) {
			$('.loan_column').addClass('loan_column_Fixed ');
		} else {
			$('.loan_column').removeClass('loan_column_Fixed ');
		}
	});
});