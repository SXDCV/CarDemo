
$(function() {
	// 分页
	getAll();
	//新增会员充值
	getAdd();
	
	
	
	
	
});



/**********新增会员充值***********/
function getAdd() {
	$("#add").click(function() {
		layer.open({
			type:2,
			title:'新增会员充值',
			area:['860px', '100%'],
			shadeClose:true,
			skin:'layui-layer-lan',
			maxmin:true,
			content:"chong.do?method=toadd",
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
						url :'chong.do?method=getByPages', // 请求后台的URL（*）
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
						uniqueId : "oid", // 每一行的唯一标识，一般为主键列
						showToggle : true, // 是否显示详细视图和列表视图的切换按钮
						cardView : false, // 是否显示详细视图
						detailView : false, // 是否显示父子表
						columns : [
								
								{
									field : 'oid',
									align : 'center',
									title : '编号'
								},
								{
									field : 'rcard',
									align : 'center',
									title : '会员卡号'
								},
								{
									field : 'money',
									align : 'center',
									title : '原始充值金额（元）'
								},{
									field : 'ytitle',
									align : 'center',
									title : '优惠内容'
								},{
									field : 'smoney',
									align : 'center',
									title : '赠送金额（元）'
								},{
									field : 'lastmoney',
									align : 'center',
									title : '优惠后的金额（元）'
								},{
									field : 'otime',
									align : 'center',
									title : '充值时间'
								},{
									field : 'uname',
									align : 'center',
									title : '经办人'
								},{
									field : 'oremark',
									align : 'center',
									title : '备注'
								}]
					});
}


