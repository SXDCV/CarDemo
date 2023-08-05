var zu=/^[\u0391-\uFFE5A-Za-z]+$/;
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
$(function() {
	// 分页
	getAll();
	//新增优惠活动
	getAdd();
	
});


/**********新增优惠活动***********/
function getAdd() {
	$("#add").click(function() {
		layer.open({
			type:2,
			title:'新增优惠活动',
			area:['40%','85%'],
			shadeClose:true,
			skin:'layui-layer-lan',
			maxmin:true,
			content:"youhui.do?method=toadd",
			end:function(){
				$("#tab").bootstrapTable('refresh');
			}
		});
	});
	
}



/** *******分页查询********** */
function getAll() {
	$('#tab').bootstrapTable(
					{
						url :'youhui.do?method=getByPages', // 请求后台的URL（*）
						method : 'get', // 请求方式（*）
						dataType : 'json',
						toolbar : '#toolbar', // 工具按钮用哪个容器
						striped : true, // 是否显示行间隔色
						pagination : true, // 是否显示分页（*）

						queryParams : function(params) {
							var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
								pages : params.limit, // 页面大小(每一页要显示多少条)
								begin : params.offset
							// 从数据库第几条记录开始
							};
							return temp;
						},// 传递参数（*）

						sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
						pageNumber : 1, // 初始化加载第一页，默认第一页
						pageSize : 5, // 每页的记录行数（*）
						pageList : [ 5, 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
						showColumns : true, // 是否显示所有的列
						showRefresh : true, // 是否显示刷新按钮
						minimumCountColumns : 1, // 最少允许的列数
						clickToSelect : true, // 是否启用点击选中行
						uniqueId : "yid", // 每一行的唯一标识，一般为主键列
						showToggle : true, // 是否显示详细视图和列表视图的切换按钮
						cardView : false, // 是否显示详细视图
						detailView : false, // 是否显示父子表
						columns : [
								{
									field : 'yid',
									align : 'center',
									title : '等级编号'
								},
								{
									field : 'ytitle',
									align : 'center',
									title : '活动名称',
									formatter : function(value, row, index) {
										var e = "<span  class='ytitle'>"
												+ value + "</span>";
										return e;
									}
								},
								{
									field : 'ybegintime',
									align : 'center',
									title : '开始时间',
									formatter : function(value, row, index) {
										var e = "<span  class='ybegintime'>"
												+ value + "</span>";
										return e;
									}
								},
								{
									field : 'yendtime',
									align : 'center',
									title : '结束时间',
									formatter : function(value, row, index) {
										var e = "<span  class='yendtime'>"
												+ value + "</span>";
										return e;
									}
								},
								{
									field : 'money',
									align : 'center',
									title : '优惠金额',
									formatter : function(value, row, index) {
										var e = "<span  class='ymoney'>" + value
												+ "</span>";
										return e;
									}
								},
								{
									field : 'lessmoney',
									align : 'center',
									title : '起始金额',
									formatter : function(value, row, index) {
										var e = "<span  class='ylessmoney'>" + value
												+ "</span>";
										return e;
									}
								},
								{
									title : '操作',
									field : 'yid',
									align : 'center',
									formatter : function(value, row, index) {

									var e = '<a href="#" class="btn btn-info btn-xs" onclick="edit(\'' + value
										+ '\')"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;编辑</a> ';
									return e;
									}
								} ]
					});
}
//修改
function edit(yid){
	layer.open({
		type:2,
		title:'修改优惠活动',
		area:['40%','80%'],
		shadeClose:true,
		skin:'layui-layer-lan',
		maxmin:true,
		content:"youhui.do?method=youhuiOne&yid="+yid,
		end:function(){
			$("#tab").bootstrapTable('refresh');
		}
	});
}

