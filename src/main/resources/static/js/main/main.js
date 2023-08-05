$(function() {
		var cid = $('#nav_list> li>.submenu');
		cid.each(function(i) {
			$(this).attr('id', "Sort_link_" + i);
		})
	})
	jQuery(document)
			.ready(
					function() {
						$.each($(".submenu"), function() {
							var $aobjs = $(this).children("li");
							var rowCount = $aobjs.size();
							var divHeigth = $(this).height();
							$aobjs.height(divHeigth / rowCount);
						});
						//初始化宽度、高度    
						$("#main-container").height($(window).height() - 76);
						$("#iframe").height($(window).height() - 140);

						$(".sidebar").height($(window).height() - 99);
						var thisHeight = $("#nav_list").height(
								$(window).outerHeight() - 173);
						$(".submenu").height();
						$("#nav_list").children(".submenu").css("height",
								thisHeight);

						
						$(".iframeurl")
								.click(
										function() {
											var cid = $(this).attr("name");
											var cname = $(this).attr("title");
											
											$("#iframe").attr("src", cid)
													.ready();
											$("#Bcrumbs").attr("href", cid)
													.ready();
											$(".Current_page a").attr('href',
													cid).ready();
											$(".Current_page")
													.attr('name', cid);
											$(".Current_page").html(cname).css(
													{
														"color" : "#333333",
														"cursor" : "default"
													}).ready();
											$("#parentIframe")
													.html(
															'<span class="parentIframe iframeurl"> </span>')
													.css("display", "none")
													.ready();
											$("#parentIfour").html('').css(
													"display", "none").ready();
										});

					});

	/*********************点击事件*********************/
	$(document).ready(
			function() {
				$('#nav_list').find('li.home').click(function() {
					$('#nav_list').find('li.home').removeClass('active');
					$(this).addClass('active');
				});

				//时间设置
				function currentTime() {
					var d = new Date(), str = '';
					str += d.getFullYear() + '年';
					str += d.getMonth() + 1 + '月';
					str += d.getDate() + '日';
					str += d.getHours() + '时';
					str += d.getMinutes() + '分';
					str += d.getSeconds() + '秒';
					return str;
				}
				setInterval(function() {
					$('#time').html(currentTime)
				}, 1000);
				//修改密码
   })