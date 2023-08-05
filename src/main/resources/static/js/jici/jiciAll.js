
$(function() {
	// 分页
	getAll();
	//新增购买商品信息
	getAdd();
	
});


/**********新增购买商品信息***********/
function getAdd() {
	$("#add").click(function() {
		layer.open({
			type:2,
			title:'新增购买商品信息',
			area:['50%','80%'],
			shadeClose:true,
			skin:'layui-layer-lan',
			maxmin:true,
			content:"jici.do?method=toadd",
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
						url :'jici.do?method=getByPages', // 请求后台的URL（*）
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
						uniqueId : "wid", // 每一行的唯一标识，一般为主键列
						showToggle : true, // 是否显示详细视图和列表视图的切换按钮
						cardView : false, // 是否显示详细视图
						detailView : false, // 是否显示父子表
						columns : [
								
								{
									field : 'jid',
									title : '编号',
									align : 'center'
								},
								{
									field : 'rcard',
									title : '会员卡号',
									align : 'center'
								},
								{
									field : 'rname',
									title : '会员名称',
									align : 'center',
								},
								
								{
									field : 'jtime',
									title : '消费日期',
									align : 'center'
								},
								{
									field : 'sname',
									title : '消费类型',
									align : 'center'
								},
								{
									field : 'money',
									title : '消费金额(元)',
									align : 'center'
								},
								{
									field : 'uname',
									title : '经办人',
									align : 'center'
								}]
					});
}


