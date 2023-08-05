var fid;
$(function() {
	fid=$("#fid").val();
	// 分页
	getAll();
	getAdd();
	getblur();
});

function getblur() {
	$("#btn_return").click(function() {
		window.location.href="getcp.do?method=toall";
	});
	
}
/**********新增入库信息***********/
function getAdd() {
	$("#add").click(function() {
		layer.open({
			type:2,
			title:'新增入库信息',
			area:['45%','60%'],
			shadeClose:true,
			skin:'layui-layer-lan',
			maxmin:true,
			content:"getcp.do?method=add&fid="+fid,
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
						url :'getcp.do?method=getByPages', // 请求后台的URL（*）
						method : 'get', // 请求方式（*）
						dataType : 'json',
						toolbar : '#toolbar', // 工具按钮用哪个容器
						striped : true, // 是否显示行间隔色
						pagination : true, // 是否显示分页（*）

						queryParams : function(params) {
							var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
								pages : params.limit, // 页面大小(每一页要显示多少条)
								begin : params.offset,
								fid:fid
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
						uniqueId : "fid", // 每一行的唯一标识，一般为主键列
						showToggle : true, // 是否显示详细视图和列表视图的切换按钮
						cardView : false, // 是否显示详细视图
						detailView : false, // 是否显示父子表
						columns : [
								
								{
									field : 'gid',
									title : '编号',
									align : 'center'
								},
								{
									field : 'fname',
									title : '产品名称',
									align : 'center',
								},
								{
									field : 'cname',
									title : '产品类型',
									align : 'center',
								},
								{
									field : 'gcount',
									title : '入库数量',
									align : 'center'
								},
								{
									field : 'gtime',
									title : '入库时间',
									align : 'center'
								},
								{
									field : 'uname',
									title : '经办人',
									align : 'center'
								}]
					});
}